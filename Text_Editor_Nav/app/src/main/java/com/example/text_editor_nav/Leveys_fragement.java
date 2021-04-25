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

public class Leveys_fragement extends Fragment {

    Button muutaleveys;
    TextView leveyslmoitus;
    SeekBar seekBar1;
    private Float leveys;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState) {

        View lev = inflater.inflate(R.layout.fragment_leveys, container, false);


        muutaleveys = (Button) lev.findViewById(R.id.muutaleveys);
        leveyslmoitus = (TextView) lev.findViewById(R.id.leveysilmoitus);
        seekBar1 = (SeekBar) lev.findViewById(R.id.seekBar1);
        leveys = null;

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

                leveys = (float)(progress *0.001);
                leveyslmoitus.setText("leveys" + leveys);
                MainActivity ml = (MainActivity) getActivity();
                ml.fontWidth(leveys);

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
