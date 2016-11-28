package com.example.juserzhang.gridview.RatingBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.juserzhang.gridview.R;

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar ratingBar = null;
    private TextView mytxt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        this.ratingBar = (RatingBar) super.findViewById(R.id.ratingone);
        this.mytxt = (TextView) super.findViewById(R.id.mytxt);
//设置评分组件的事件
        this.ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListenerImp());
    }

    public class OnRatingBarChangeListenerImp implements RatingBar.OnRatingBarChangeListener {

        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            RatingBarActivity.this.mytxt.append("星级:" + ratingBar.getRating()+"星" + "\n");
        }
    }

}
