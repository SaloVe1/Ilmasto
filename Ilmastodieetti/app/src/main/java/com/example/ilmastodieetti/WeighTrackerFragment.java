package com.example.ilmastodieetti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class WeighTrackerFragment extends Fragment {


    userbank bank = userbank.getInstance();
    testbank tester = testbank.getInstance();
    CurrentUser current = CurrentUser.getInstance();


    private LineChart chart;





    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View w = inflater.inflate(R.layout.weighttrackergraph_class,container,false);



        chart = (LineChart) w.findViewById(R.id.Line);
        List<Entry> entries = new ArrayList<Entry>();

        int j = Integer.parseInt(current.getCurrentestnumber());
        String[] weights = tester.WeightProgression(current.getUsername());
        for (int i=0; i < j; i++){

        entries.add(new Entry(i+1,Float.parseFloat(weights[i])));
            System.out.println(weights[i]);
            System.out.println(i+1);
        }
        LineDataSet dataSet = new LineDataSet(entries, "Weightprogression KG in Y values");




        LineData LineData = new LineData(dataSet);
        chart.setData(LineData);
        chart.invalidate();







        return w;




    }
}
