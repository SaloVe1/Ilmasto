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

public class Rivivali_fragement extends Fragment {

    Button muutaspace;
    TextView spaceilmoitus;
    SeekBar seekBar3;
    private Float space;


    @Nullable
@Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstancesState){

    View lev = inflater.inflate(R.layout.fragment_rivivali, container, false);


    muutaspace = (Button) lev.findViewById(R.id.muutaspace);
    spaceilmoitus = (TextView) lev.findViewById(R.id.spaceilmoitus);
    seekBar3 = (SeekBar) lev.findViewById(R.id.seekBar3);
    space = null;

    seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

            space = (float)(progress *0.001);
            spaceilmoitus.setText("spacing" + space);
            MainActivity ml = (MainActivity) getActivity();
            ml.fontSpace(space);

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
