package com.example.shiraki_hirotomo.trainschedule;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shiraki-hirotomo on 2014/09/15.
 */
public class TrainScheduleFactory {
    public static ArrayList<TrainSchedule> list=null;

    /*
     * テキストデータの解析
     */
    public static ArrayList<TrainSchedule> create(String response) {

        Log.d("createの引数のresponse", response);
        if(list == null){
            list = new ArrayList<TrainSchedule>();
        }

        try {
            Log.d("前JSONObjectに入れたresponse", response);
            //JSONObject rootObject = new JSONObject(response);
            JSONArray rootArray = new JSONArray(response);
            Log.d("後JSONObjectに入れたresponse", response);
            // dataオブジェクトの取得
            JSONObject firstObject = rootArray.getJSONObject(0);//二つある要素のうち一つ
            Log.d("1", "あ");
            JSONArray dataArray = firstObject.getJSONArray("odpt:weekdays");//odpt:
            Log.d("2", "あ");

            int tmpCount=0;
            for(int i = 0; i < dataArray.length(); i++) {
                if(++tmpCount==dataArray.length()) Log.d(tmpCount+"回", "あ");
                TrainSchedule schedule = new TrainSchedule();
                JSONObject data = dataArray.getJSONObject(i);

                schedule.setDepartureTime(null);
                if(data.has("odpt:departureTime")) {//odpt:
                    schedule.setDepartureTime(data.getString("odpt:departureTime"));//odpt:
                }

                schedule.setDestinationStation(null);
                if(data.has("odpt:destinationStation")) {//odpt:
                    schedule.setDestinationStation(data.getString("odpt:destinationStation"));//odpt:
                }

                schedule.setTrainType(null);
                if(data.has("odpt:trainType")) {//odpt:
                    schedule.setTrainType(data.getString("odpt:trainType"));//odpt:
                }

                list.add(schedule);
            }
        }
        catch(JSONException je) {
            list = null;
            //je.printStackTrace();
            Log.d("例外json", "ここか！");
        }
        return list;
    }

}
