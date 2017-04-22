package com.gonnaggstudio;

/**
 * Created by hschueh on 2017/4/23.
 */
public class Steed2CruiseControl extends Main.Template {

    private double destPosition;
    private double horseNum;
    Steed2CruiseControl(String fileName) {
        super(fileName);
    }

    @Override
    void solve() {
        double maxContantSpeed = 0;
        double time = 0;
        destPosition = sc.nextDouble();
        horseNum = sc.nextDouble();
        double horseInitPos;
        double horseMaxSpeed;
        for(int i = 0; i < horseNum; ++i) {
            horseInitPos = sc.nextDouble();
            horseMaxSpeed = sc.nextDouble();
            time = Math.max(time, (destPosition - horseInitPos)/horseMaxSpeed);
        }

        maxContantSpeed = destPosition/time;
        System.out.println(String.format("%.6f", maxContantSpeed));
        out.println(String.format("%.6f", maxContantSpeed));
    }
}