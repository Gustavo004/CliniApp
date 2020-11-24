package com.example.cliniapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*

//Tipo de seguridad que le podemos dar a nuestra app;
enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}


class HomeActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //SepUp y pasando datos a las siguiente pantalla;
        val bundle = intent.extras
        val email: String? = bundle?.getString("email")
        val provaider: String? = bundle?.getString("provaider")

        SetUp(email ?: "", provaider ?: "")

        //Guardado de datos y sesion;

        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provaider", provaider)
        prefs.apply()


    }

    private fun SetUp(email: String, provider: String) {
        title = "Inicio"
        EmailTextViewRegistroUsuario.text = email  //Recuperando datos de la app;
        ProvaiderTextView.text = provider

        //Deslogueo de la app;
        btnLogOut.setOnClickListener {

            //Borrando los datos;
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()//Borra todas las preferencias del usuario y sesiones;
            prefs.apply()//Asegura el borrado de sesion


            if (provider == ProviderType.FACEBOOK.name) {
                LoginManager.getInstance().logOut()
            }


            FirebaseAuth.getInstance().signOut()//Se deslogue la cuenta
            onBackPressed()//Vuelve a la pantalla anterior;
        }

    }


}