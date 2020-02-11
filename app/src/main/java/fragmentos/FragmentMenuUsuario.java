package fragmentos;


import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import br.com.empregos.ContatoViewActivity;
import br.com.empregos.CurriculoViewActivity;
import br.com.empregos.CursoViewActivity;
import br.com.empregos.DadosPessoaisViewActivity;
import br.com.empregos.DocumentoViewActivity;
import br.com.empregos.EnderecoViewActivity;
import br.com.empregos.ExperienciaProfissionalViewActivity;
import br.com.empregos.FormacaoViewActivity;
import br.com.empregos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuUsuario extends Fragment implements View.OnClickListener, ComponentCallbacks2, View.OnCreateContextMenuListener {




    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private CardView cardPerfil;
    private CardView cardEndereco;
    private CardView cardContato;
    private CardView cardDocumentos;
    private CardView cardFormacao;
    private CardView cardCursos;
    private CardView cardCurriculo;
    private CardView cardExpProfissional;



    public FragmentMenuUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_perfil_2, container, false);


        setup(view);

        return view;
    }



    private void setup(View v){
        cardPerfil =  v.findViewById(R.id.cardPerfil);
        cardEndereco =  v.findViewById(R.id.cardEndereco);
        cardContato =  v.findViewById(R.id.cardContato);
        cardCursos =  v.findViewById(R.id.cardCursos);
        cardCurriculo =  v.findViewById(R.id.cardCurriculo);
        cardDocumentos =  v.findViewById(R.id.cardDocumento);
        cardFormacao =  v.findViewById(R.id.cardFormacao);
        cardExpProfissional =  v.findViewById(R.id.cardExpProfissiona);

        cardPerfil.setOnClickListener(this);
        cardEndereco.setOnClickListener(this);
        cardContato.setOnClickListener(this);
        cardCursos.setOnClickListener(this);
        cardCurriculo.setOnClickListener(this);
        cardDocumentos.setOnClickListener(this);
        cardFormacao.setOnClickListener(this);
        cardExpProfissional.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == cardPerfil){
            intent = new Intent(getContext(), DadosPessoaisViewActivity.class);
            startActivity(intent);
        } else if (v == cardEndereco){
            intent = new Intent(getContext(), EnderecoViewActivity.class);
            startActivity(intent);
        }else if(v == cardContato){
            intent = new Intent(getContext(), ContatoViewActivity.class);
            startActivity(intent);
        }else if(v == cardDocumentos){
            intent = new Intent(getContext(), DocumentoViewActivity.class);
            startActivity(intent);
        }else if(v == cardCursos){
            intent = new Intent(getContext(), CursoViewActivity.class);
            startActivity(intent);
        }else if(v == cardCurriculo){
            intent = new Intent(getContext(), CurriculoViewActivity.class);
            startActivity(intent);
        }else if(v == cardFormacao){
            intent = new Intent(getContext(), FormacaoViewActivity.class);
            startActivity(intent);
        } else if (v == cardExpProfissional){
            intent = new Intent(getContext(), ExperienciaProfissionalViewActivity.class);
            startActivity(intent);
        }




    }


    @Override
    public void onTrimMemory(int level) {

    }
}
