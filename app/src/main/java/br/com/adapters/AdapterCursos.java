package br.com.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import br.com.empregos.R;
import br.com.model.Cursos;


public class AdapterCursos extends RecyclerView.Adapter<AdapterCursos.ViewHolder> {
    //
    private static final String TAG = "ADAPTERCURSOS";

    private List<Cursos> lstCursos = new ArrayList<>();
    private Context context;
    private List imgs = new ArrayList();

    public AdapterCursos(List<Cursos> lstCursos, Context cont) {
        this.lstCursos = lstCursos;
        this.context = context;
        this.imgs = imgs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txTitle;
        TextView txDescrição;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View v){
            super(v);
              imgIcon =  v.findViewById(R.id.imgIcon);
              txTitle = v.findViewById(R.id.txTitulo);
              txDescrição = v.findViewById(R.id.edtDescricaoPequena);
              parentLayout = v.findViewById(R.id.parentLayout);
              context = v.getContext();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cursos_list_item, viewGroup, false);
     ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("RECYCLERVIEW", "onBindViewHolder: ");
        final int n = i;
        Glide.with(context).asBitmap().load(R.drawable.formacao).into(viewHolder.imgIcon);
        viewHolder.txTitle.setText(lstCursos.get(i).getNomeCurso());
        viewHolder.txDescrição.setText(lstCursos.get(i).getDescriçãoCurso());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "você clicou no " + lstCursos.get(n).getNomeCurso(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return lstCursos.size();
    }


}
