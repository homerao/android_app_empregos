package br.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.empregos.R;

public class GridPerfilAdapter extends BaseAdapter {

    Context contexto;
    private final String [] tituloMenus;
    private final int [] imgIcons;
    ImageView image;
    View view;
    LayoutInflater inflater;

    public GridPerfilAdapter(Context context, String[] tituloMenus, int[] imgIcons){
      this.contexto = context;
        this.tituloMenus = tituloMenus;
        this.imgIcons = imgIcons;
    }

    @Override
    public int getCount() {
        return tituloMenus.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
          view = new View(contexto);
          view = inflater.inflate(R.layout.single_item, null);
            image =  view.findViewById(R.id.imgContato);
            TextView texto =  view.findViewById(R.id.txtTitulo);
            image.setImageResource(imgIcons[position]);

            texto.setText(tituloMenus[position]);

        }
        return view;
    }
}
