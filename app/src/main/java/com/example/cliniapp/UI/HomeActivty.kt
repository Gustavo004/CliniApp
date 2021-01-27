package com.example.cliniapp.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cliniapp.Models.HistoriaMedica
import com.example.cliniapp.R
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
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



         //Mandando a llamar a la otra actividad Historia Medica ;
        btnListarHistoriaMedica.setOnClickListener {
            val intento1 = Intent(this, HMedicaActivity::class.java)
            startActivity(intento1)
        }




        //Mandando a llamar a la otra actividad MapaClinica ;
        btnMapaClinicas.setOnClickListener()
        {

            val irMapaClinica = Intent(this, ClinicaMapasActivity::class.java)
            startActivity(irMapaClinica)
        }


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