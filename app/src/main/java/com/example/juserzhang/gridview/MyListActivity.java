package com.example.juserzhang.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.juserzhang.gridview.Calc.CalcActivity;
import com.example.juserzhang.gridview.Calendar.CalendarActivity;
import com.example.juserzhang.gridview.Clock.ClockActivity;
import com.example.juserzhang.gridview.Music.MusicActivity;
import com.example.juserzhang.gridview.Picture.PictureActivity;
import com.example.juserzhang.gridview.RatingBar.RatingBarActivity;
import com.example.juserzhang.gridview.Record.RecordActivity;
import com.example.juserzhang.gridview.Second.SecondActivity;
import com.example.juserzhang.gridview.Video.VideoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListActivity extends Activity {
    private int[] image = { R.drawable.music, R.drawable.video, R.drawable.picture,
            R.drawable.calendar, R.drawable.second, R.drawable.clock, R.drawable.calculator,
            R.drawable.ratingbar ,R.drawable.record};
    private String[] text = { "音乐", "视频", "图片", "日历", "秒表", "时钟", "计算器", "评分条","录音" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.gridview);
        //显示GridView的界面
        GridView gridview = (GridView) findViewById(R.id.gridview1);
        ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();
        // 使用HashMap将图片添加到一个数组中，注意一定要是HashMap<String,Object>类型的，因为装到map中的图片要是资源ID，而不是图片本身
        // 如果是用findViewById(R.drawable.image)这样把真正的图片取出来了，放到map中是无法正常显示的
        for (int i = 0; i <image.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("text", text[i]);
            imagelist.add(map);
        }
        // 使用simpleAdapter封装数据，将图片显示出来
        // 参数一是当前上下文Context对象
        // 参数二是图片数据列表，要显示数据都在其中
        // 参数三是界面的XML文件，注意，不是整体界面，而是要显示在GridView中的单个Item的界面XML
        // 参数四是动态数组中与map中图片对应的项，也就是map中存储进去的相对应于图片value的key
        // 参数五是单个Item界面XML中的图片ID
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist,
                R.layout.item, new String[] { "image", "text" }, new int[] {
                R.id.image, R.id.title });
        // 设置GridView的适配器为新建的simpleAdapter
        gridview.setAdapter(simpleAdapter);


         gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                 switch(position){
                     case 0://点击图片0跳转
                     {
                        startActivity(new Intent(MyListActivity.this,MusicActivity.class));
                     }
                     break;
                     case 1://点击图片1跳转
                     {
                        startActivity(new Intent(MyListActivity.this,VideoActivity.class));
                     }
                     break;
                     case 2:
                     {
                         startActivity(new Intent(MyListActivity.this, PictureActivity.class));
                     }
                     break;
                     case 3:
                     {
                         startActivity(new Intent(MyListActivity.this, CalendarActivity.class));
                     }
                     break;
                     case 4:
                     {
                         startActivity(new Intent(MyListActivity.this, SecondActivity.class));

                     }
                     break;
                     case 5:
                     {
                         startActivity(new Intent(MyListActivity.this, ClockActivity.class));

                     }
                     break;
                     case 6:
                     {
                         startActivity(new Intent(MyListActivity.this, CalcActivity.class));

                     }
                     break;
                     case 7:
                     {
                         startActivity(new Intent(MyListActivity.this, RatingBarActivity.class));
                     }
                     break;
                     case 8:
                     {
                         startActivity(new Intent(MyListActivity.this, RecordActivity.class));
                     }
                     break;
                 }
             }
         });
    }
}
