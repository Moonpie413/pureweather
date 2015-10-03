package space.wxh.pureweather.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import space.wxh.pureweather.R;
import space.wxh.pureweather.model.Province;
import space.wxh.pureweather.model.WeatherDB;
import space.wxh.pureweather.util.HttpCallbackListener;
import space.wxh.pureweather.util.HttpUtil;
import space.wxh.pureweather.util.Utility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        HttpUtil.sendHttpRequest("http://www.weather.com.cn/data/list3/city.xml",
                new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                WeatherDB weatherDB = WeatherDB.getInstance(MainActivity.this);
                Log.d("test", response);
                Utility.handleProvincesResponse(weatherDB, response);
                List<Province> list = weatherDB.loadProvinces();
                for (Province province : list
                     ) {
                    Log.d("test","id" + province.getProvinceCode());
                    Log.d("test","name" + province.getProvinceName());
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
