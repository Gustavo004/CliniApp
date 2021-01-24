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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.maps.model.MapStyleOptions


class ClinicaMapasActivity : AppCompatActivity(), OnMapReadyCallback {

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

        locationRequest?.interval=10000 //10 segundos para actualizacion constante;

        locationRequest?.fastestInterval=5000 //el tope mas alto de 5 segundos,lo maximo que se va actualizar va ser de 5 segundos;

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



        //validar que el mapa y cordenadas hayan sido cargados;L
        if(validarPermisosUbicacion() ) {

            obtenerUbicacion()
        }else
        {
            pedirPermisos()
        }


        // Add a marker in Sydney and move the camera

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