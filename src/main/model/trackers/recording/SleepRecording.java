package model.trackers.recording;

import model.TimeStamp;

import java.util.Calendar;

public class SleepRecording extends TrackerRecording {
    TimeStamp date;

    //TODO: implement class
    public SleepRecording(TimeStamp date, int hoursSlept) {
        this.date = new TimeStamp(date);
    }

}
