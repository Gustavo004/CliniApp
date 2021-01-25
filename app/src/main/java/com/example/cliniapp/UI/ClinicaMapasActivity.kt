package com.example.cliniapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.cliniapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.maps.model.*


class ClinicaMapasActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    //variable de ubicacion;
    private val permisoFineLocation=android.Manifest.permission.ACCESS_FINE_LOCATION
    private val permisoCoarseLocation=android.Manifest.permission.ACCESS_COARSE_LOCATION

    //Permiso Codigo
    private val CODIGO_SOLICITUD_PERMISO=100

    //OBTENER LA ULTIMA UBICACION;
    var fusedLocationClient:FusedLocationProviderClient?=null

    var locationRequest:LocationRequest?=null

    var callback:LocationCallback?=null

    //Marcadores Mapa;
    //private var marcadorGolden:Marker?=null
    private var marcadorClinicaSantaMaria:Marker?=null
    private var marcadorClinicaGoodHope:Marker?=null
    private var marcadorClinicaInternacional_SedeLima:Marker?=null
    private var marcadorClinicaRicardoPalma:Marker?=null
    private var marcadorClínicaJavierPrado:Marker?=null
    //
    private var marcadorClinicaDelgado:Marker?=null
    private var marcadorClinicaStellaMaris:Marker?=null
    private var marcadorClinicaAngloAmericana:Marker?=null
    private var marcadorClinicaInternacional_SanBorja:Marker?=null
    private var marcadorClinicaCayetanoHeredia:Marker?=null

    //
    private var marcadorClinicaMiraflores:Marker?=null
    private var marcadorClinicaMonteFiori:Marker?=null
    private var marcadorClinicaCentenarioPeruanoJaponesa:Marker?=null
    private var marcadorCentroMedicoJockeySalud:Marker?=null
    private var marcadorSANNA_CLINICA_SAN_BORJA:Marker?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinica_mapas)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //inicalizar la variable
        fusedLocationClient= FusedLocationProviderClient(this)
        InicializarLocationRequest()

        callback=object:LocationCallback()
        {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)

                if(mMap != null ){
                    //obtener la ubicacion mas actualizada cada vez que cambia la ubicacion del telefono;
                    //Ignorar error pide permisos pero los permisos ya estan concedidos,
                    mMap.isMyLocationEnabled = true

                    mMap.uiSettings.isMyLocationButtonEnabled = true


                    for (ubicacion in locationResult?.locations!!){
                        Toast.makeText(applicationContext,ubicacion.latitude.toString()+" , "+ubicacion.longitude.toString() ,Toast.LENGTH_LONG).show()

                        //Crear variable longitud y latitud  donde va contener la ubicacion que voy a mapear;
                        val MiPosicion = LatLng(ubicacion.latitude, ubicacion.longitude)

                        //Añadimos un marcador
                        mMap.addMarker(MarkerOptions().position(MiPosicion).title("Estas aqui"))

                        //Mover la camara zoom y zoomout
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(MiPosicion))

                        //Log.i("Recuperar direccion",ubicacion.latitude.toString()+" , "+ubicacion.longitude.toString() )
                    }
                }

            }
        }
    }
    private fun InicializarLocationRequest() {
        locationRequest = LocationRequest()
        //actulizacion constante en milisegundos de la ubicacion(tracking);

        locationRequest?.interval=120000 //2  minutos  para actualizacion constante;

        locationRequest?.fastestInterval=120000 //el tope mas alto de 2 minutos,lo maximo que se va actualizar va ser de 2 minutos;

        locationRequest?.priority=LocationRequest.PRIORITY_HIGH_ACCURACY //que tanto de aproximadad queremos para darnos la ubicacion
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

        //Obteniendo el valor dl estilo y sobreescribiendo el estilo
        val exitoCambioMapa = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.estilo_mapa))


        if (!exitoCambioMapa)
        {
                //Mencionar que hubo problema al cambiar tipo mapa

        }

        //Ubicacion en lat y longtiud extraido de google maps;
        val CLINICA_SANTA_MARIA = LatLng(-12.102694,-77.026154)
        val CLINICA_GOOD_HOPE=LatLng(-12.125470, -77.034116)
        val CLINICA_INTERNACIONAL_SEDELIMA=LatLng(-12.058432,-77.038120)
        val CLINICA_JAVIER_PRADO=LatLng(-12.091187,-77.028178)
        //
        val CLINICA_DELGADO=LatLng(-12.113036,-77.033386)
        val CLINICA_STELLA_MARIS=LatLng(-12.071219,-77.058828)
        val CLINICA_ANGLO_AMERICANA=LatLng(-12.071219,-77.058828)
        val CLINICA_INTERNACIONAL_SAN_BORJA=LatLng(-12.092565,-77.008890)
        val CLINICA_CAYETANO_HEREDIA=LatLng(-12.023669,-77.055869)
        //
        val CLINICA_MIRAFLORES=LatLng(-12.127378,-77.013491)
        val CLINICA_MONTE_FIORI=LatLng(-12.065428,-76.965998)
        val CLINICA_CENTENARIO_PERUANO_JAPONES=LatLng(-12.073137,-77.059050)
        val CENTRO_MEDICO_JOCKEY_SALUD=LatLng(-12.085069,-76.977377)
        val SANNA_CLINICA_SAN_BORJA=LatLng(-12.092085,-77.008347)



        //Para la clinica Santa Maria;
        marcadorClinicaSantaMaria = mMap.addMarker(MarkerOptions().position(CLINICA_SANTA_MARIA).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicasantamarialogo)).alpha(1F).title("Clínica Santa Maria"))
        marcadorClinicaSantaMaria?.tag=0

        //Para la Clinica Good Hope;
        marcadorClinicaGoodHope = mMap.addMarker(MarkerOptions().position(CLINICA_GOOD_HOPE).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicagoodhopelogo)).alpha(1F).title("Clínica Good Hope") )
        marcadorClinicaGoodHope?.tag=0

            //Para la CLINICA_INTERNACIONAL SEDE LIMA;
            marcadorClinicaInternacional_SedeLima = mMap.addMarker(MarkerOptions().position(CLINICA_INTERNACIONAL_SEDELIMA).
            //icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE) ).title("Clínica Internacional Lima Centro") )
            icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicainternacionallogo) )
            .alpha(1F)
            .title("Clínica Internacional Lima Centro") )
        marcadorClinicaInternacional_SedeLima?.tag=0

        //Para la CLINICA_JAVIER_PRADO;
        marcadorClínicaJavierPrado = mMap.addMarker(MarkerOptions().position(CLINICA_JAVIER_PRADO).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicajavierpradologo)).alpha(1F).title("Clínica Javier Prado") )
        marcadorClínicaJavierPrado?.tag=0


        //Para la CLINICA_DELGADO
        marcadorClinicaDelgado = mMap.addMarker(MarkerOptions().position(CLINICA_DELGADO).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicadelgadologo)).alpha(1F).title("Clínica Delgado") )
        marcadorClinicaDelgado?.tag=0

        //Para la CLINICA_STELLA_MARIS
        marcadorClinicaStellaMaris = mMap.addMarker(MarkerOptions().position(CLINICA_STELLA_MARIS).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicastellamarislogo)).alpha(1F).title("Clínica Stella Maris") )
        marcadorClinicaStellaMaris?.tag=0

        //Para la CLINICA_ANGLO_AMERICANA
        marcadorClinicaAngloAmericana = mMap.addMarker(MarkerOptions().position(CLINICA_ANGLO_AMERICANA).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicaangloamericanalogo)).alpha(1F).title("Clínica Anglo Américana") )
        marcadorClinicaAngloAmericana?.tag=0

        //Para la CLINICA_INTERNACIONAL_SAN_BORJA
        marcadorSANNA_CLINICA_SAN_BORJA = mMap.addMarker(MarkerOptions().position(CLINICA_INTERNACIONAL_SAN_BORJA).
        icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicainternacionallogo) )
            .alpha(1F)
            .title("Clínica Internacional San Borja Norte") )
        marcadorSANNA_CLINICA_SAN_BORJA?.tag=0



        // Para CLINICA_CAYETANO_HEREDIA
        marcadorClinicaCayetanoHeredia = mMap.addMarker(MarkerOptions().position(CLINICA_CAYETANO_HEREDIA).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicacayetanoheredialogo)).alpha(1F).title("Clínica Cayetano Heredia") )
        marcadorClinicaCayetanoHeredia?.tag=0

        //Para la CLINICA_MIRAFLORES
        marcadorClinicaMiraflores = mMap.addMarker(MarkerOptions().position(CLINICA_MIRAFLORES).icon(BitmapDescriptorFactory.fromResource(R.drawable.logoclinicamirafloreslogo)).alpha(1F).title("Clínica de Miraflores") )
        marcadorClinicaMiraflores?.tag=0

        //Para la CLINICA_MONTE_FIORI
        marcadorClinicaMonteFiori = mMap.addMarker(MarkerOptions().position(CLINICA_MONTE_FIORI).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicalogomontefiorilogo)).alpha(1F).title("Clínica Monte Fiori") )
        marcadorClinicaMonteFiori?.tag=0

        //Para la CLINICA_CENTENARIO_PERUANO_JAPONES
        marcadorClinicaCentenarioPeruanoJaponesa = mMap.addMarker(MarkerOptions().position(CLINICA_CENTENARIO_PERUANO_JAPONES).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicaperuanojaponeslogo)).alpha(1F).title("Clínica Centenario Peruano Japonesa") )
        marcadorClinicaCentenarioPeruanoJaponesa?.tag=0

        //Para la CENTRO_MEDICO_JOCKEY_SALUD
        marcadorCentroMedicoJockeySalud = mMap.addMarker(MarkerOptions().position(CENTRO_MEDICO_JOCKEY_SALUD).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicajockeysaludlogo)).alpha(1F).title("Clínica Centro Medico Jockey") )
        marcadorCentroMedicoJockeySalud?.tag=0


        //Para la SANNA_CLINICA_SAN_BORJA
        marcadorSANNA_CLINICA_SAN_BORJA = mMap.addMarker(MarkerOptions().position(SANNA_CLINICA_SAN_BORJA).icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicasannalogosanborja)).alpha(1F).title("Clínica SANNA San Borja") )
        marcadorSANNA_CLINICA_SAN_BORJA?.tag=0


        //Capturando el numero de clicks (Evento Click)
        mMap.setOnMarkerClickListener(this)








        //validar que el mapa y cordenadas hayan sido cargados;L
        if(validarPermisosUbicacion() ) {

            obtenerUbicacion()
        }else
        {
            pedirPermisos()
        }

        // Add a marker in Sydney and move the camera
    }

    override fun onMarkerClick(marcador: Marker?): Boolean {

        var NumeroClicks= marcador?.tag as? Int


        if (NumeroClicks != null)
        {
            NumeroClicks++
            marcador?.tag = NumeroClicks
            Toast.makeText(this,"Se han dado "+" " +NumeroClicks.toString()+"clicks",Toast.LENGTH_LONG).show()
        }



        return false
    }


    //ver si el usuario tiene los permisos o hace falta pedirlo al usuario;
    //Emplea y llama al  manifiesto
    private fun validarPermisosUbicacion():Boolean
    {
        val hayUbicacionPrecisa=ActivityCompat.checkSelfPermission(this,permisoFineLocation)==PackageManager.PERMISSION_GRANTED
        val hayUbicacionOrdinaria=ActivityCompat.checkSelfPermission(this,permisoCoarseLocation)==PackageManager.PERMISSION_GRANTED

        return hayUbicacionPrecisa && hayUbicacionOrdinaria
    }
    //////////////////////////////////////////////////////////////
//Heredar la ultima de ubicacion de otra app;
    @SuppressLint("MissingPermission")
    private fun obtenerUbicacion()
    {

        fusedLocationClient?.requestLocationUpdates(locationRequest,callback,null)
    }
    /////////////////////////////////////////////////////////////////////
//No se ha pedido el permiso al usuario o porque lo nego
    @RequiresApi(Build.VERSION_CODES.M)
    private fun pedirPermisos()
    {
        //Mandar mensaje de porque vamos a solictar su ubicacion;
        val deboProveerContexto= ActivityCompat.shouldShowRequestPermissionRationale(this,permisoFineLocation)

        if(deboProveerContexto)
        {
            //mandar mensaje con explicacion adicional;
            Toast.makeText(this,"Hola",Toast.LENGTH_LONG).show()
            solicitudPermiso()

        }else
        {
            //Solicitud de permiso;
            solicitudPermiso()
        }

    }
    //////////////////////////////////////////////////////////////////////////////////////////
    @RequiresApi(Build.VERSION_CODES.M)
    private fun solicitudPermiso() {
        requestPermissions(arrayOf(permisoFineLocation,permisoCoarseLocation),CODIGO_SOLICITUD_PERMISO)
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Si se mapea;
        when(requestCode)
        {
            CODIGO_SOLICITUD_PERMISO ->{
                if (grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //Obtener la ubicacion
                    obtenerUbicacion()
                }else
                {
                    //No se dio permiso;
                    Toast.makeText(this,"No diste permiso para acceder a la ubicacion",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    private  fun  detenerActualizacionDeUbicacion()
    {
        fusedLocationClient?.removeLocationUpdates(callback)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()

        if(validarPermisosUbicacion() ) {

            obtenerUbicacion()
        }else
        {
            pedirPermisos()
        }
    }

    override fun onPause() {
        super.onPause()
        detenerActualizacionDeUbicacion()
    }




}