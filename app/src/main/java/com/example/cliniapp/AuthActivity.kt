package com.example.cliniapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*
import java.security.Provider


class AuthActivity : AppCompatActivity() {

    private val GOOGLE_SING_IN = 100
    private val CallbackManager = com.facebook.CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)//Muestra el tema que hemos definido

        Thread.sleep(1000)//Duerme por un segundos

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //SepUp;

        setUp()
        session()


    }

    private fun session() {
        //No vamos a editar solo a recuperar ;
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        //Validacion de que se inicio sesion en la app;
        if (email != null && provider != null) {

            ShowHome(email, ProviderType.valueOf(provider))
        }
    }


    private fun setUp() {

        title = "Autentication"

        //Recogiendo el btnIrRegistrar;
        btnIrRegistrar.setOnClickListener {

            //Comprobar que todos los datos aqui son correctos;
            if (InputEmailRegistrar.text.isNotEmpty() && InputPasswordRegistrar.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    InputEmailRegistrar.text.toString()
                    , InputPasswordRegistrar.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        //El correo siempre sera necesario nunca null
                        ShowHome(it.result?.user?.email ?: "", ProviderType.BASIC)

                    } else {
                        ShowAlert()
                    }

                }


            }


        }


        //Logica para logearnos;
        btnLogin.setOnClickListener {

            //Comprobar que todos los datos aqui son correctos;
            if (InputEmailRegistrar.text.isNotEmpty() && InputPasswordRegistrar.text.isNotEmpty()) {

                //Loguearnos con Email y Contraseña de correo cualquiera(outlook,hotmail,etc)
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    InputEmailRegistrar.text.toString()
                    , InputPasswordRegistrar.text.toString()
                ).addOnCompleteListener {

                    //Si tod0 sale bien manda a la siguiente pantalla;
                    if (it.isSuccessful) {
                        //El correo siempre sera necesario nunca null
                        ShowHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        ShowAlert() //Si no muestra un mensaje de alerta;
                    }
                }

            }

        }

        //Progrmacion en el boton de google;
        GoogleButton.setOnClickListener {

            //creamos una variable llamada googleCof y le pasamos un builder y usamos la identificacion por defecto;

            val googleCof =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
                    getString(R.string.default_web_client_id)
                )
                    .requestEmail() //Requiere un email
                    .build()        //Usamos el metodo built()

            val googleClient = GoogleSignIn.getClient(this, googleCof)
            googleClient.signOut()


            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)

        }


        //Progrmacion en el boton de facebook;
        FacebookButton.setOnClickListener {

            //Solicitamos el email para poder continuar
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(CallbackManager,
                object : FacebookCallback<LoginResult> {

                    //Cuando t0d0 va bien
                    override fun onSuccess(result: LoginResult?) {

                        result?.let {

                            val token = it.accessToken

                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener {

                                    if (it.isSuccessful) {
                                        //El correo siempre sera necesario nunca null
                                        ShowHome(
                                            it.result?.user?.email ?: "",
                                            ProviderType.FACEBOOK
                                        )

                                    } else {
                                        ShowAlert()
                                    }
                                }

                        }
                    }

                    //Cuando t0d0 se cancela
                    override fun onCancel() {

                    }

                    //Cuando t0d0 va mal
                    override fun onError(error: FacebookException?) {
                        ShowAlert()
                    }
                })
        }


    }

    //Creando un mensaje de alerta para el usuario;
    private fun ShowAlert() {
        val builder = AlertDialog.Builder(this) //creando un builder tipo alerta
        builder.setTitle("Error")   //Asiganamos un titulo
        builder.setMessage("Se ha producido un error autenticando al usuario") //Asignamos y mandamos un mensaje
        builder.setPositiveButton("Aceptar", null)//To esta bien
        val dialog: AlertDialog = builder.create() //Alertamos al usuario
        dialog.show()//Mostramos el mensaje;
    }

    private fun ShowHome(email: String, provider: ProviderType) {
        //Vamos a cambiar de pantalla hacia HomeActivity y le pasaremos los datos del usuario;
        val homeIntent = Intent(this, HomeActivty::class.java).apply {

            putExtra("email", email)
            putExtra("provider", provider.name)
        }

        startActivity(homeIntent) //Iniciando la otra actividad con los parametros pasados...

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        CallbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SING_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {

                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {

                            if (it.isSuccessful) {
                                //El correo siempre sera necesario nunca null
                                ShowHome(account.email ?: "", ProviderType.GOOGLE)

                            } else {
                                ShowAlert()
                            }
                        }

                }
            } catch (e: ApiException) {
                ShowAlert()
            }


        }
    }


}