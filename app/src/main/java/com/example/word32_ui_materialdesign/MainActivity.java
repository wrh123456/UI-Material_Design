package com.example.word32_ui_materialdesign;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.deawer_layou);

        //设置导航按钮点击出现滑动框
        ActionBar actionBar=getSupportActionBar();
        if(drawerLayout!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.img_menu);//设置导航按钮图标
        }

        //悬浮按钮
        final FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"FAB clicked",Toast.LENGTH_LONG).show();
                            }
                        }).show();

            }
        });

        //卡片水果
        initData();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

    }
    private  void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();//通知数据发生变化
                        swipeRefreshLayout.setRefreshing(false);//用于表示刷新事件的结束，并隐藏进度条
                    }
                });
            }
        }).start();
    }
    private void initData(){
        fruitList.clear();
        Fruit[] f=new Fruit[]{
                new Fruit("樱桃",R.drawable.yingtao),
                new Fruit("草莓",R.drawable.caomei),
                new Fruit("苹果",R.drawable.pingguo),
                new Fruit("樱桃",R.drawable.yingtao),
                new Fruit("草莓",R.drawable.caomei),
                new Fruit("苹果",R.drawable.pingguo),
                new Fruit("樱桃",R.drawable.yingtao),
                new Fruit("草莓",R.drawable.caomei),
                new Fruit("苹果",R.drawable.pingguo),
                new Fruit("樱桃",R.drawable.yingtao),
                new Fruit("草莓",R.drawable.caomei),
                new Fruit("苹果",R.drawable.pingguo)
        };
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(f.length);
            fruitList.add(f[index]);
            //随机拿到其中一个数
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);//打开侧边栏
                break;
            case R.id.backup:
                Toast.makeText(this,"You clicked Backup",Toast.LENGTH_LONG).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked Delete",Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_LONG).show();
                break;
                default:break;
        }
        return true;
    }
}
