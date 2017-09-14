package com.example.develop.machinedone.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.develop.machinedone.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

;

public class AnalysisFragment extends Fragment {
    private View inflate;
    private BarChart barChart;
    private XAxis xAxis;
    private PieChart mpieChart;
    private String[] mLabels = new String[] { "Company A", "Company B", "Company C", "Company D", "Company E", "Company F" };
//    private String[] mXVals = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inflate = inflater.inflate(R.layout.activity_analysis_fragment, container, false);
        init();//初始化
    //test();
        return inflate;
    }

   private void init()
    {

        mpieChart = (PieChart) inflate.findViewById(R.id.analysis_pie_chart);
        final PieData pieData = getPieData(5, 100);
        mpieChart.setUsePercentValues(true);
        mpieChart.post(new Runnable() {
            @Override
            public void run() {
                showChart(mpieChart, pieData);

            }
        });
        //1、基本设置
        barChart=  inflate.findViewById(R.id.barChart);
        xAxis=barChart.getXAxis();//获取x轴
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.setTouchEnabled(true); // 设置是否可以触摸
        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放
        //2、y轴和比例尺
        barChart.getDescription().setEnabled(false);

        barChart.setMaxVisibleValueCount(60);
        barChart.setPinchZoom(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false); // 是否显示表格颜色

        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();//隐藏比例尺
        legend.setEnabled(false);
        /*legend.setForm(Legend.LegendForm.CIRCLE);// 样式
        legend.setFormSize(6f);// 字体
        legend.setTextColor(Color.BLACK);// 颜色*/

        //3、x轴数据,和显示位置

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//数据位于底部


        //4、y轴数据
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        //new BarEntry(20, 0)前面代表数据，后面代码柱状图的位置；

        //5、设置显示的数字为整形

        //6、设置柱状图的颜色

        //7、显示，柱状图的宽度和动画效果



           // barDataSet.setBarSpacePercent(40f);//值越大，柱状图就越宽度越小；

        barChart.animateY(1000);
        barChart.setData(generateBarData(1, 100, 4)); //



    }
    protected BarData generateBarData(int dataSets, float range, int count) {

        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();

        for(int i = 0; i < dataSets; i++) {

            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");

            for(int j = 0; j < count; j++) {
                entries.add(new BarEntry(j, (float) (Math.random() * range) + range / 4));
            }

            BarDataSet ds = new BarDataSet(entries, getLabel(i));
            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            sets.add(ds);
        }

        BarData d = new BarData(sets);
        return d;
    }

    private String getLabel(int i) {
        return mLabels[i];
    }


    public PieData getPieData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<>();//xValues是饼块内容的容器
        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));
        }
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        yValues.add(new PieEntry(1));
        yValues.add(new PieEntry(2));
        yValues.add(new PieEntry(3));
        yValues.add(new PieEntry(4));
        yValues.add(new PieEntry(5));
        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, " ");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        ArrayList<Integer> colors = new ArrayList<Integer>();
        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(66, 123, 222));

        pieDataSet.setColors(colors);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度
        PieData data = new PieData(pieDataSet);
        return data;
    }

    private void showChart(PieChart pieChart, PieData pieData) {
        //pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        //pieChart.setDescription();

        // mChart.setDrawYValues(true);
//        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字
//        pieChart.setCenterText("f分拣效率");  //饼状图中间的文字


        pieChart.setDrawHoleEnabled(false);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        pieChart.getDescription().setEnabled(false);  //设置pieChart图表的描述
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);


        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();
        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setEnabled(false);
//        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
//        mLegend.setXEntrySpace(7f);
//        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }



}
