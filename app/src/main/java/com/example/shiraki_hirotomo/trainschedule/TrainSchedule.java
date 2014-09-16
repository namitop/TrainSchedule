package com.example.shiraki_hirotomo.trainschedule;

/**
 * Created by shiraki-hirotomo on 2014/09/15.
 */
public class TrainSchedule {
    String departureTime;//public static
    String destinationStation;
    String trainType;

    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    public void setDestinationStation(String destinationStation){
        this.destinationStation = destinationStation;
    }

    public void setTrainType(String trainType){
        this.trainType = trainType;
    }

    public String getDepartureTime(){
        return departureTime;
    }

    public String getDestinationStation(){
        return destinationStation;
    }

    public String getTrainType(){
        return trainType;
    }
}
