package com.linger.piechar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {
    public PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChart= (PieChart) findViewById(R.id.pie_chart);
        //初始化饼状图数据类
        PieData mPieData=getPieData();
        showChart(mChart,mPieData);
    }

    private void showChart(PieChart mChart, PieData pieData) {
        //设置饼状图中心透明
        mChart.setHoleColorTransparent(true);
        //设置半径
        mChart.setHoleRadius(60f);
        //饼状图中间可以添加文字
        mChart.setDrawCenterText(true);
        mChart.setDrawHoleEnabled(true);
        mChart.setCenterText("家庭支出");
        mChart.setCenterTextSize(16f);
        mChart.setCenterTextColor(Color.BLUE);
        //初始选择角度
        mChart.setRotationAngle(90);
        mChart.setRotationEnabled(true);
        mChart.setUsePercentValues(true);
        mChart.setData(pieData);
        //设置比例图
        Legend mLegend=mChart.getLegend();
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        mChart.animateXY(1000,1000);
    }

    private PieData getPieData() {
        //衣食住行以及其它
        ArrayList<String> xValues=new ArrayList<>();
        xValues.add("衣");
        xValues.add("食");
        xValues.add("住");
        xValues.add("行");
        xValues.add("其它");
        ArrayList<Entry> yValues=new ArrayList<>();
        float q1=10;
        float q2=20;
        float q3=25;
        float q4=40;
        float q5=5;
        yValues.add(new Entry(q1,0));
        yValues.add(new Entry(q2,1));
        yValues.add(new Entry(q3,2));
        yValues.add(new Entry(q4,3));
        yValues.add(new Entry(q5,4));
        PieDataSet pieDataSet=new PieDataSet(yValues,"2018年 家庭支出");
        pieDataSet.setSliceSpace(1f);
        ArrayList<Integer> colors=new ArrayList<>();
        //饼图颜色
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(15f);
        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return ""+(int)v+"%";
            }
        });
        DisplayMetrics metrics=getResources().getDisplayMetrics();
        float px=5+(metrics.densityDpi/160f);
        pieDataSet.setSelectionShift(px);
        PieData pieData=new PieData(xValues,pieDataSet);
        return pieData;

    }
}
