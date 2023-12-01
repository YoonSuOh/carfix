package com.carfix.common;

public class CalcDistance {
    // 거리 계산 후 반환
    public static double calcDistance(double lat1, double lat2, double lng1, double lng2){
        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;
        dist = Math.round(dist);

        dist = dist/1000;
        dist = Math.round(dist * 10.0) / 10.0;

        return dist; //단위 meter
    }

    //10진수를 radian(라디안)으로 변환
    public static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    public static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
