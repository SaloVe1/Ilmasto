package com.example.text_editor_nav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Kirjoitus_fragement extends Fragment {

    Button lukitse;
    Button avaa;

@Nullable
@Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
{
  View k = inflater.inflate(R.layout.fragment_kirjoitus, container, false);

  lukitse = (Button) k.findViewById(R.id.lukitse);
  avaa = (Button) k.findViewById(R.id.avaa);






    return k;

}





}

