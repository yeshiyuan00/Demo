package com.exp.ysy.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.images.BitmapTest;
import com.images.MyView_Activity;
import com.resources.AlphaImageView_Activity;
import com.resources.AnimTest;
import com.resources.AnimatorTest;
import com.resources.ClipDrawableTest;
import com.resources.EditSelect;
import com.resources.TestForFir;
import com.resources.XmlResTest;

import java.util.ArrayList;
import java.util.List;


public class ImageActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView lv_intent;

    private List<String> list_intent;

    public final static String CRAZYIT_ACTION = "com.ysy.intent.action.CRAZYIT_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        lv_intent = (ListView) findViewById(R.id.lv_intent);

        list_intent = new ArrayList<String>();

        list_intent.add(0, "显示assets目录下的图片");
        list_intent.add(1, "绘制基本的集合图形");


        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list_intent);

        lv_intent.setAdapter(adapter);

        lv_intent.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                /**
                 * component显式启动一个Activity
                 * */
                Intent intent0 = new Intent(ImageActivity.this, BitmapTest.class);
                startActivity(intent0);
                break;

            case 1:

                Intent intent1 = new Intent(ImageActivity.this, MyView_Activity.class);
                startActivity(intent1);
                break;

            case 2:

                Intent intent2 = new Intent(ImageActivity.this, ClipDrawableTest.class);
                startActivity(intent2);

                break;
            case 3:

                Intent intent3 = new Intent(ImageActivity.this, AnimTest.class);
                startActivity(intent3);

                break;
            case 4:

                Intent intent4 = new Intent(ImageActivity.this, AnimatorTest.class);
                startActivity(intent4);

                break;

            case 5:

                Intent intent5 = new Intent(ImageActivity.this, XmlResTest.class);
                startActivity(intent5);

                break;
            case 6:

                Intent intent6 = new Intent(ImageActivity.this, AlphaImageView_Activity.class);
                startActivity(intent6);

                break;


        }
    }
}
