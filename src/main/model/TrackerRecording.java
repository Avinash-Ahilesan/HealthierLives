package model;

import java.util.Calendar;

public class TrackerRecording  {
    protected Calendar calendar;

    public Calendar getDate() {
        return (Calendar)calendar.clone();
    }

}
