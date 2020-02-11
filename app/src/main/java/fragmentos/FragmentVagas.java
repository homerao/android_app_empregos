package fragmentos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import androidx.fragment.app.Fragment;

import br.com.empregos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVagas extends Fragment {
    private AdView mAdview;


    public FragmentVagas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vagas, container, false);
        MobileAds.initialize(view.getContext(), getResources().getString(R.string.ad_mob_appid));

        mAdview = view.findViewById(R.id.adPrincipalView);
        AdRequest request = new AdRequest.Builder().build();
        mAdview.loadAd(request);

        return  view;
    }




}
