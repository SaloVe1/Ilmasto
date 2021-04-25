package com.example.text_editor_nav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Font_fragement extends Fragment {

    Button muutakoko;
    TextView kokoilmoitus;
    SeekBar seekBar;
    private Integer koko;

@Nullable
@Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
{


        View f = inflater.inflate(R.layout.fragment_fonttikoko, container, false);

        muutakoko = (Button) f.findViewById(R.id.muutakoko);
        kokoilmoitus = (TextView) f.findViewById(R.id.kokoilmoitus);
        seekBar = (SeekBar) f.findViewById(R.id.seekBar);
        koko = 0;

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

            kokoilmoitus.setText("koko " + String.valueOf(progress) );
            koko = Integer.valueOf(progress);
            MainActivity ml = (MainActivity)getActivity();
            ml.fontInt(koko);

        }


        @Override
        public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

        }
    });







        return f;



}







}
