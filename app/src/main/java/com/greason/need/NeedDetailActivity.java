package com.greason.need;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.need.utils.BitmapFromAssets;

import java.io.File;

/**
 * Created by Greason on 15/11/7.
 */
public class NeedDetailActivity extends Activity {

    private String needPath = "need";
    private String needPerFix_backgroudImg = "good";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_detail);

        ImageButton top_pre = (ImageButton) findViewById(R.id.top_pre);
        top_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int position = getIntent().getIntExtra("position", 0);
        String title = getIntent().getStringExtra("title");
        TextView tv = (TextView)findViewById(R.id.need_title);
        tv.setText(title);

        ImageView img = (ImageView)findViewById(R.id.need_backgroudImg);
        img.setImageBitmap(BitmapFromAssets.getBitmap(this, needPath + File.separator + needPerFix_backgroudImg + (position + 1)));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
