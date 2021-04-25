package com.example.text_editor_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Korkeus_fragement extends Fragment {


    Button muutakorkeus;
    TextView korkeusilmoitus;
    SeekBar seekBar2;
    private Float korkeus;


@Nullable
@Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstancesState){

    View lev = inflater.inflate(R.layout.fragment_korkeus, container, false);


    muutakorkeus = (Button) lev.findViewById(R.id.muutakorkeus);
    korkeusilmoitus = (TextView) lev.findViewById(R.id.korkeusilmoitus);
    seekBar2 = (SeekBar) lev.findViewById(R.id.seekBar2);
    korkeus = null;

    seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

            korkeus = (float)(progress *0.001);
            korkeusilmoitus.setText("korkeus" + korkeus);
            MainActivity ml = (MainActivity) getActivity();
            ml.fontHeight(korkeus);

        }


        @Override
        public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

        }
    });


    return lev;

}

}
