package com.example.text_editor_nav;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Displaytext_fragement extends Fragment {

    EditText displayfield;
    private String displaytext;


@Nullable
@Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstancesState){

    View lev = inflater.inflate(R.layout.fragment_displaytext, container, false);

    displayfield = (EditText) lev.findViewById(R.id.Displayfield);
    displaytext = "";

    displayfield.setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                displaytext = displayfield.getText().toString();
                MainActivity ml = (MainActivity) getActivity();
                ml.setDisplaytext(displaytext);



                return true;
            }
            return false;
        }
    });








    return lev;

}



}

