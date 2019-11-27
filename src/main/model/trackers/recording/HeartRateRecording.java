package model.trackers.recording;

import model.TimeStamp;

import java.util.Calendar;

public class HeartRateRecording extends TrackerRecording {
    private int bpm;

    public HeartRateRecording(TimeStamp date, int bpm) {
        super.calendar = date;
        this.bpm = bpm;
    }

    //EFFECTS: compares two HeartRateRecordings and returns true if
    //         their equal and false otherwise
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        HeartRateRecording recorder = (HeartRateRecording) obj;
        if (recorder.bpm == this.bpm && recorder.getDate().equals(this.getDate())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(bpm) + " " + getDate().toString();
    }
}
