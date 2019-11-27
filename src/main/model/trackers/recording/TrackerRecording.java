package model.trackers.recording;

import model.TimeStamp;

import java.io.Serializable;

public abstract class TrackerRecording implements Serializable {
    protected TimeStamp calendar;


    public TimeStamp getDate() {
        return new TimeStamp(calendar);
    }

}
