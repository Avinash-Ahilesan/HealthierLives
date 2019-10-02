package model;

import java.util.Calendar;

public class HeartRateRecording extends TrackerRecording {
    private int bpm;

    public HeartRateRecording(Calendar date, int bpm) {
        super.calendar = date;
        this.bpm = bpm;
    }
}
