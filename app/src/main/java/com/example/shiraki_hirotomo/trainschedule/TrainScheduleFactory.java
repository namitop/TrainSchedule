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
            JSONObject rootObject = new JSONObject(response);
            //JSONArray rootArray = new JSONArray(response);
            Log.d("後JSONObjectに入れたresponse", response);
            // dataオブジェクトの取得
            JSONArray dataArray = rootObject.getJSONArray("weekdays");//odpt:

            for(int i = 0; i < dataArray.length(); i++) {
                TrainSchedule schedule = new TrainSchedule();

                JSONObject data = dataArray.getJSONObject(i);

                schedule.setDepartureTime(null);
                if(data.has("departureTime")) {//odpt:
                    schedule.setDepartureTime(data.getString("departureTime"));//odpt:
                }

                schedule.setDestinationStation(null);
                if(data.has("destinationStation")) {//odpt:
                    schedule.setDestinationStation(data.getString("destinationStation"));//odpt:
                }

                schedule.setTrainType(null);
                if(data.has("trainType")) {//odpt:
                    schedule.setTrainType(data.getString("trainType"));//odpt:
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
