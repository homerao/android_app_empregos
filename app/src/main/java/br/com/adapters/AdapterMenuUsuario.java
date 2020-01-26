package br.com.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import br.com.empregos.R;
import br.com.util.MenuResourceUtil;


public class AdapterMenuUsuario extends RecyclerView.Adapter<AdapterMenuUsuario.ViewHolder> {

    private static final String TAG = "ADAPTERMENUUSUARIO";

    private List<String> lstTitulo = new ArrayList<>();
    private Context context;
    private List imgs = new ArrayList();
    private int imgIcon;

    public AdapterMenuUsuario(List<String> lstTitulos,int imgIcone,List imgs ,Context cont) {
        this.lstTitulo = lstTitulos;
        this.context = context;
        this.imgs = imgs;
        this.imgIcon = imgIcone;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        ImageView imgArrow;
        TextView txTitle;
        RelativeLayout parentLayout;
        CardView cardMenu;
        ConstraintLayout layoutInterno;


        public ViewHolder(@NonNull View v){
            super(v);
              imgIcon =  v.findViewById(R.id.imgContato);
              imgArrow = v.findViewById(R.id.imgIcon_arrow);
              txTitle = v.findViewById(R.id.txtTitulo);
              parentLayout = v.findViewById(R.id.menuParentLayout);
              layoutInterno = v.findViewById(R.id.menu_const_interno);
              context = v.getContext();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
     ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("RECYCLERVIEW", "onBindViewHolder: ");
        final int n = i;
        Glide.with(context).asBitmap().load(new MenuResourceUtil.PerfilResource().getPerfilIcons().get(i)).into(viewHolder.imgIcon);
        Glide.with(context).asBitmap().load(R.drawable.ic_keyboard_arrow_right_blue_45dp).into(viewHolder.imgArrow);
        viewHolder.txTitle.setText(new MenuResourceUtil.PerfilResource().getTitulosPerfil().get(i));



    }

    @Override
    public int getItemCount() {
        return lstTitulo.size();
    }


}
