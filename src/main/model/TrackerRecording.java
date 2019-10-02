package model;

import java.io.Serializable;
import java.util.Calendar;

public class TrackerRecording implements Serializable {
    protected Calendar calendar;

    public Calendar getDate() {
        return (Calendar) calendar.clone();
    }

}
