package model;

import java.util.Calendar;

public class SleepRecording {
    Calendar date;

    //TODO: implement class
    public SleepRecording(Calendar date, int hoursSlept) {
        this.date = (Calendar) date.clone();
    }

}
