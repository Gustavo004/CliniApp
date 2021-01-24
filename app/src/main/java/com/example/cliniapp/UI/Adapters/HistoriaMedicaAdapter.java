package com.example.cliniapp.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cliniapp.Models.HistoriaMedica;
import com.example.cliniapp.R;

import java.util.ArrayList;
import java.util.List;

public class HistoriaMedicaAdapter extends RecyclerView.Adapter<HistoriaMedicaAdapter.ViewHolder> {

        private List<HistoriaMedica>historiaMedicaList=new ArrayList<>();


    @NonNull
    @Override
    public HistoriaMedicaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Especificando la vista para obtener el diseño;
        return new ViewHolder(LayoutInflater.from(parent.getContext() ).inflate(R.layout.item_historia_medica,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaMedicaAdapter.ViewHolder holder, int position) {

        holder.tvtemperaturaCorporal.setText(historiaMedicaList.get(position).getTemperaturaCorporal()           );
        holder.tvidHistoriaMedica.setText(historiaMedicaList.get(position).getIdHistoriaMedica().toString()      );
        holder.tvidMedicamentos.setText(historiaMedicaList.get(position).getIdMedicamentos().toString()          );
        holder.tvfecha_De_La_Historia_Medica.setText(historiaMedicaList.get(position).getFecha_De_La_Historia_Medica().toString()         );
        holder.tvtipoSangre.setText(historiaMedicaList.get(position).getTipoSangre()    );
        holder.tvpeso.setText(historiaMedicaList.get(position).getPeso().toString()     );
        holder.tvpulso.setText(historiaMedicaList.get(position).getPulso()    );
        holder.tvpresion.setText(historiaMedicaList.get(position).getPresion()    );
        holder.tvidDiagnostico.setText(historiaMedicaList.get(position).getIdDiagnostico().toString()   );
        holder.tvdni.setText(historiaMedicaList.get(position).getDni().toString()   );

    }


    //Metodo agregar historias medicas;
    public void AgregarElementosHistoriaMedica(List<HistoriaMedica>lista)
    {
        this.historiaMedicaList=lista;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return historiaMedicaList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //Pasando los valores a la vista
        private TextView tvidHistoriaMedica,
                tvtipoSangre,tvpulso,tvtemperaturaCorporal,tvpresion,tvpeso,tvdni,tvidMedicamentos,tvidDiagnostico,tvfecha_De_La_Historia_Medica;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            tvtemperaturaCorporal=itemView.findViewById(R.id.tvtemperaturaCorporal);
            tvidHistoriaMedica=itemView.findViewById(R.id.tvidHistoriaMedica);
            tvidMedicamentos=itemView.findViewById(R.id.tvidMedicamentos);
            tvfecha_De_La_Historia_Medica=itemView.findViewById(R.id.tvfecha_De_La_Historia_Medica);
            tvtipoSangre=itemView.findViewById(R.id.tvtipoSangre);
            tvpeso=itemView.findViewById(R.id.tvpeso);
            tvpulso=itemView.findViewById(R.id.tvpulso);
            tvpresion=itemView.findViewById(R.id.tvpresion);
            //
            tvidDiagnostico=itemView.findViewById(R.id.tvidDiagnostico);
            tvdni=itemView.findViewById(R.id.tvdni);
        }
    }

}
