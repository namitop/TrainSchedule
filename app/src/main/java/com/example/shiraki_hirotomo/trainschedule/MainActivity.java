package com.example.shiraki_hirotomo.trainschedule;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements LoaderCallbacks<String>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bundleにはパラメータを保存する（1）
        Bundle bundle = new Bundle();

        // 大阪府の天気予報を返すAPIのURLを格納する（2）
        //bundle.putString("url", "http://www.drk7.jp/weather/json/27.js");
        bundle.putString("url", "https://api.tokyometroapp.jp/api/v2/datapoints?rdf:type=odpt:StationTimetable&odpt:station=odpt.Station:TokyoMetro.Tozai.Otemachi&acl:consumerKey="+Config.ACCESS_TOKEN);

        // LoaderManagerの初期化（3）
        getLoaderManager().initLoader(0, bundle, this);





    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle bundle) {

        // HttpAsyncLoaderの生成（4）
        HttpAsyncLoader loader = new HttpAsyncLoader(this, bundle.getString("url"));

        // Web APIの呼び出し（5）
        loader.forceLoad();
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String body) {

        // 実行結果の書き出し（6）
        if ( loader.getId() == 0 ) {
            if (body != null) {
                Log.d("ボディ", body);
            }
        }
        TextView textView = (TextView) findViewById(R.id.train_schedule_all);
        // テキストビューのテキストを設定します
        textView.setText(body);

        ArrayList<TrainSchedule> schedule_list =  TrainScheduleFactory.create(body);
        if(schedule_list==null) Log.d("null","nullだよ");
        Log.d("到着時刻", schedule_list.get(0).getDepartureTime());
        Log.d("何行き", schedule_list.get(0).getDestinationStation());
        Log.d("鈍行急行？", schedule_list.get(0).getTrainType());
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // 今回は何も処理しない
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
