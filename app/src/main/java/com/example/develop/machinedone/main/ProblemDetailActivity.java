package com.example.develop.machinedone.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.adapter.LogListViewAdapter;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.LogListViewItem;
import com.example.develop.machinedone.bean.ProblemBean;
import com.example.develop.machinedone.model.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.PendingIntent.getActivity;

public class ProblemDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow operate_popupWindow;
    private Button operate_button;
    private TextView logbtn;
    private boolean x = true;
    private ListView log_listview;
    private List<LogListViewItem.MenuitemBean> menuitemBeans = new ArrayList<>();
    private LogListViewAdapter logListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarImg1.setImageResource(R.mipmap.ic_title_back);
        username.setText("User");
        toolbarImg.setOnClickListener(this);
        toolbarImg1.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        logbtn = findViewById(R.id.log_btn);
        logbtn.setOnClickListener(this);
        log_listview = findViewById(R.id.log_listview);
        toolbarTitle.setText(R.string.question_detail);
        logListViewAdapter = new LogListViewAdapter(this, menuitemBeans);
//添加数据到listview中
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        final Call<LogListViewItem> list = apiService.getList_three();
        list.enqueue(new Callback<LogListViewItem>() {
            @Override
            public void onResponse(Call<LogListViewItem> call, Response<LogListViewItem> response) {
                menuitemBeans.addAll(response.body().getMenuitem());
                log_listview.setAdapter(logListViewAdapter);
            }

            @Override
            public void onFailure(Call<LogListViewItem> call, Throwable t) {

            }
        });

        //点击操作按钮上拉列表
        operate_button = findViewById(R.id.operate_button);
        operate_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                //关闭当前页面
                finish();
                break;
            case R.id.toolbar_img:
                Toast.makeText(this, "头像点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_btn:
                if (log_listview.getVisibility() == view.VISIBLE) {
                    log_listview.setVisibility(view.GONE);
                } else {
                    log_listview.setVisibility(view.VISIBLE);
                }
                break;
            case R.id.operate_button:
                    showPopupWindow();
                break;
            case R.id.operate_btn:
                    operate_popupWindow.dismiss();
                break;
        }
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.operate_list, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = contentView.getMeasuredHeight();
//这里就可自定义在上方和下方了 ，这种方式是为了确定在某个位置，某个控件的左边，右边，上边，下边都可以
        operate_popupWindow = new PopupWindow(contentView, 0, 0);
        operate_popupWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        operate_popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        operate_popupWindow.setFocusable(true);
// 获得目标控件位置
        int[] location = new int[2];
        operate_button.getLocationOnScreen(location);
        operate_popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        operate_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        operate_popupWindow.showAtLocation(operate_button, Gravity.NO_GRAVITY, 0, location[1]);
        WindowManager windowManager = ProblemDetailActivity.this.getWindowManager();
        WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha=0.7f;
        ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ProblemDetailActivity.this.getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        operate_popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
                params.alpha=1f;
                ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ProblemDetailActivity.this.getWindow().setAttributes(params);
            }
        });
        operate_popupWindow.setAnimationStyle(R.style.AnimationFade);
        operate_popupWindow.update();

        Button operate_btn = contentView.findViewById(R.id.operate_btn);
        operate_btn.setOnClickListener(this);
    }

}
