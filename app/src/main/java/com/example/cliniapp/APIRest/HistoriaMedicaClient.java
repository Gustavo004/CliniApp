package com.example.cliniapp.APIRest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.cliniapp.Models.HistoriaMedica;
import java.util.List;

public class HistoriaMedicaClient {

    private static final String url_base= "http://localhost:8082/WebServicesCliniApp/";
    private HistoriaMedicaInterface historiaMedicaInterface;
    private static HistoriaMedicaClient INSTANCE;


    public HistoriaMedicaClient ()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create() )
                .build();
        historiaMedicaInterface=retrofit.create(HistoriaMedicaInterface.class);
    }

    public static HistoriaMedicaClient getINSTANCE(){

        if (INSTANCE==null)
        {
            INSTANCE = new HistoriaMedicaClient();
        }

        return INSTANCE;
    }

    public Call <List <HistoriaMedica>> getHistoriaMedica()
    {
        return historiaMedicaInterface.getHistoriaMedica();
    }



}
