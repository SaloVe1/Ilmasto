package com.example.text_editor_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Blank_fragement extends Fragment {

@Nullable
@Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
{
  return inflater.inflate(R.layout.fragment_blank, container, false);

}

}
