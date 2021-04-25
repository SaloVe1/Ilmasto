package com.example.ilmastodieetti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



//This is the fragment class that displays the Barchart

public class BarClassFragment extends Fragment {



userbank bank = userbank.getInstance();
testbank tester = testbank.getInstance();
CurrentUser current = CurrentUser.getInstance();

private String Dairy;
private String Meat;
private String Plant;
private String Restaurant;
private String Total;
private BarChart chart;





    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View w = inflater.inflate(R.layout.fragment_bar_class,container,false);

        //Finding the test value attributes by currently set username and currently set testnumber

        Dairy = tester.getOldTestResult(tester.getEmissionResultbyTake(current.getUsername(), current.getCurrentestnumber()), "Dairy");
        Meat = tester.getOldTestResult(tester.getEmissionResultbyTake(current.getUsername(), current.getCurrentestnumber()), "Meat");
        Plant = tester.getOldTestResult(tester.getEmissionResultbyTake(current.getUsername(), current.getCurrentestnumber()), "Plant");
        Restaurant = tester.getOldTestResult(tester.getEmissionResultbyTake(current.getUsername(), current.getCurrentestnumber()), "Restaurant");
        Total = tester.getOldTestResult(tester.getEmissionResultbyTake(current.getUsername(), current.getCurrentestnumber()), "Total");


        chart = (BarChart) w.findViewById(R.id.Line);

        //Setting up the y axis values of chart

        ArrayList<BarEntry>barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,Float.parseFloat(Dairy)));
        barEntries.add(new BarEntry(2,Float.parseFloat(Meat)));
        barEntries.add(new BarEntry(3,Float.parseFloat(Plant)));
        barEntries.add(new BarEntry(4,Float.parseFloat(Restaurant)));
        barEntries.add(new BarEntry(5,Float.parseFloat(Total)));
        BarDataSet barDataSet = new BarDataSet(barEntries,"kg's of CO2 per year");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("Consumption");
        chart.setDescription(description);

        ArrayList<String> category = new ArrayList<>();
        category.add("Dairy");
        category.add("Meat");
        category.add("Plant");
        category.add("Restaurant");
        category.add("Total");


        //Setting up the x axis values of chart

        BarData theData = new BarData(barDataSet);
        chart.setData(theData);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(category));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(category.size());
        chart.animateY(2000);
        chart.invalidate();





       return w;




    }

    //public void onViewCreated(View view, Bundle savedInstanceState){
      //  super.onViewCreated(view, savedInstanceState);
    //}



}
