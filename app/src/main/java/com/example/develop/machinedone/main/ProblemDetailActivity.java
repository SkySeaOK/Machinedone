package com.example.develop.machinedone.main;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.develop.machinedone.model.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProblemDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow operate_popupWindow;
    private PopupWindow assign_popupWindow;
    private PopupWindow handle_popupWindow;
    private PopupWindow refuse_popupWindow;
    private PopupWindow refuseReason_popupWindow;
    private Button operate_button;
    private Button operate_btn;
    private TextView logbtn;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;
    private ListView log_listview;
    private List<LogListViewItem.MenuitemBean> menuitemBeans = new ArrayList<>();
    private LogListViewAdapter logListViewAdapter;
    private LinearLayout assign_liner;
    private LinearLayout delay_liner;
    private LinearLayout delete_liner;
    private LinearLayout operateList_liner;
    private LinearLayout handle_liner;
    private ImageView delete_img;
    private LinearLayout handle_user;
    private TextView handleUser_text;
    private TextView user_text;
    private TextView assign_person;
    private ImageView refuseDelete_img;
    private LinearLayout refuseHandle_liner;
    private TextView refuseHandle_text;
    private TextView refuse_text;
    private LinearLayout refuse_reason;
    private View v;
    private String[] data = {"不是错误","重复问题","延期解决","设计如此","不能重现","不同意建议","忽略"};

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
                showOperatePopupWindow();
                break;
            case R.id.operate_btn:
                operate_popupWindow.dismiss();
                break;
            case R.id.assign_btn:
                showAssignPopupWindow();
                assign_person.setText("指派新的处理人");
                assign_liner.setBackgroundColor(0xFFBDBDBD);
                delay_liner.setBackgroundColor(0xFFBDBDBD);
                delete_liner.setBackgroundColor(0xFFBDBDBD);
                operate_btn.setBackgroundColor(0xFFBDBDBD);
                operateList_liner.setBackgroundColor(0xFFBBBBBB);
                break;
            case R.id.finish_btn:
                showAssignPopupWindow();
                assign_person.setText("完成");
                assign_liner.setBackgroundColor(0xFFBDBDBD);
                delay_liner.setBackgroundColor(0xFFBDBDBD);
                delete_liner.setBackgroundColor(0xFFBDBDBD);
                operate_btn.setBackgroundColor(0xFFBDBDBD);
                operateList_liner.setBackgroundColor(0xFFBBBBBB);
                break;
            case R.id.delete_img:
                assign_popupWindow.dismiss();
                break;
            case R.id.handle_liner:
                if(x)
                {
                    handle_popupWindow.dismiss();
                    x = false;
                }
                else
                {
                    showHandlePopupWindow();
                    handle_popupWindow.showAtLocation(v,Gravity.CENTER,50, 20);
                    x = true;
                }
                break;
            case R.id.handle_user:
                handleUser_text.setText(user_text.getText());
                handle_popupWindow.dismiss();
                break;
            case R.id.refuse_btn:
                showRefusePopupWindow();
                assign_liner.setBackgroundColor(0xFFBDBDBD);
                delay_liner.setBackgroundColor(0xFFBDBDBD);
                delete_liner.setBackgroundColor(0xFFBDBDBD);
                operate_btn.setBackgroundColor(0xFFBDBDBD);
                operateList_liner.setBackgroundColor(0xFFBBBBBB);
                break;
            case R.id.refuseDelete_img:
                refuse_popupWindow.dismiss();
                break;
            case R.id.refuseHandle_liner:
                if(y)
                {
                    handle_popupWindow.dismiss();
                    y = false;
                }
                else
                {
                    showHandlePopupWindow();
                    handle_popupWindow.showAtLocation(v,Gravity.CENTER,50, 50);
                    y = true;
                }
                break;
            case R.id.refuse_reason:
                if(z)
                {
                    refuseReason_popupWindow.dismiss();
                    z = false;
                }
                else
                {
                    showRefuseReasonPopupWindow();
                    z = true;
                }
                break;
        }
    }

    private void showOperatePopupWindow() {
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
        params.alpha = 0.7f;
        ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ProblemDetailActivity.this.getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        operate_popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
                params.alpha = 1f;
                ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ProblemDetailActivity.this.getWindow().setAttributes(params);
            }
        });
        operate_popupWindow.setAnimationStyle(R.style.AnimationFade);
        operate_popupWindow.update();

        operate_btn = contentView.findViewById(R.id.operate_btn);
        operate_btn.setOnClickListener(this);
        TextView assign_btn = contentView.findViewById(R.id.assign_btn);
        TextView finish_btn = contentView.findViewById(R.id.finish_btn);
        TextView refuse_btn = contentView.findViewById(R.id.refuse_btn);
        assign_btn.setOnClickListener(this);
        finish_btn.setOnClickListener(this);
        refuse_btn.setOnClickListener(this);
        assign_liner = contentView.findViewById(R.id.assign_liner);
        delay_liner = contentView.findViewById(R.id.delay_liner);
        delete_liner = contentView.findViewById(R.id.delete_liner);
        operateList_liner = contentView.findViewById(R.id.operateList_liner);

    }

    private void showAssignPopupWindow() {
        View contentView = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.assign_page, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        assign_popupWindow = new PopupWindow(contentView, 600, 0);
        assign_popupWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        assign_popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        assign_popupWindow.setFocusable(true);
        assign_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        assign_popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha = 0.5f;
        ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ProblemDetailActivity.this.getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复0.7f
        assign_popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
                params.alpha = 0.7f;
                ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ProblemDetailActivity.this.getWindow().setAttributes(params);
                assign_liner.setBackgroundColor(0xFFffffff);
                delay_liner.setBackgroundColor(0xFFffffff);
                delete_liner.setBackgroundColor(0xFFffffff);
                operate_btn.setBackgroundColor(0xFFffffff);
                operateList_liner.setBackgroundColor(0xFFF4F4F4);
            }
        });
        assign_popupWindow.setAnimationStyle(R.style.AnimationFade);
        assign_popupWindow.update();
        delete_img = contentView.findViewById(R.id.delete_img);
        handle_liner = contentView.findViewById(R.id.handle_liner);
        delete_img.setOnClickListener(this);
        handle_liner.setOnClickListener(this);
        handleUser_text = contentView.findViewById(R.id.handleUser_text);
        assign_person = contentView.findViewById(R.id.assign_person);
    }

    private void showRefusePopupWindow() {
        View contentView = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.refuse_page, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        refuse_popupWindow = new PopupWindow(contentView, 600, 0);
        refuse_popupWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        refuse_popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        refuse_popupWindow.setFocusable(true);
        refuse_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        refuse_popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha = 0.5f;
        ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ProblemDetailActivity.this.getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复0.7f
        refuse_popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = ProblemDetailActivity.this.getWindow().getAttributes();
                params.alpha = 0.7f;
                ProblemDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ProblemDetailActivity.this.getWindow().setAttributes(params);
                assign_liner.setBackgroundColor(0xFFffffff);
                delay_liner.setBackgroundColor(0xFFffffff);
                delete_liner.setBackgroundColor(0xFFffffff);
                operate_btn.setBackgroundColor(0xFFffffff);
                operateList_liner.setBackgroundColor(0xFFF4F4F4);
            }
        });
        refuse_popupWindow.setAnimationStyle(R.style.AnimationFade);
        refuse_popupWindow.update();
        refuseDelete_img = contentView.findViewById(R.id.refuseDelete_img);
        refuseHandle_liner = contentView.findViewById(R.id.refuseHandle_liner);
        refuseDelete_img.setOnClickListener(this);
        refuseHandle_liner.setOnClickListener(this);
        refuseHandle_text = contentView.findViewById(R.id.refuseHandle_text);
        refuse_reason = contentView.findViewById(R.id.refuse_reason);
        refuse_reason.setOnClickListener(this);
        refuse_text = contentView.findViewById(R.id.refuse_text);
    }

    private void showHandlePopupWindow() {
        v = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.handle_person, null);
        handle_popupWindow = new PopupWindow(v, 400, 300);
        handle_popupWindow.setOutsideTouchable(true);
        handle_popupWindow.setFocusable(true);
        handle_user = v.findViewById(R.id.handle_user);
        handle_user.setOnClickListener(this);
        user_text = v.findViewById(R.id.user_text);

    }

    private void showRefuseReasonPopupWindow() {
        View contentView = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.refuse_reason, null);
        refuseReason_popupWindow = new PopupWindow(contentView, 400, 0);
        refuseReason_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        refuseReason_popupWindow.setOutsideTouchable(true);
        refuseReason_popupWindow.showAtLocation(contentView,Gravity.CENTER,50, 70);
        refuseReason_popupWindow.setFocusable(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ProblemDetailActivity.this,R.layout.refuse_list_item,data
        );
        ListView listView = contentView.findViewById(R.id.refuse_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = data[i].toString();
                refuse_text.setText(s);
                refuseReason_popupWindow.dismiss();
            }
        });
    }
}
