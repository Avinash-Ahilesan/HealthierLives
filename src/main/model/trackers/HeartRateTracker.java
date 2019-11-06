package model.trackers;

import model.TimeStamp;
import model.trackers.recording.HeartRateRecording;
import model.trackers.recording.TrackerRecording;

import java.io.*;
import java.util.ArrayList;

public class HeartRateTracker implements Tracker, Serializable {

    private ArrayList<HeartRateRecording> heartRateRecords = new ArrayList<>();

    public void addHeartRate(TimeStamp date, int bpm) {
        heartRateRecords.add(new HeartRateRecording(date, bpm));
    }

    public void addRecording(TrackerRecording recording) {
        heartRateRecords.add((HeartRateRecording) recording);
    }

    @Override
    public void generateTable() {
        //TODO: Implement method
    }

    @Override
    public HeartRateRecording getLastRecording() {

        return heartRateRecords.get(heartRateRecords.size() - 1);
    }


    @Override
    public String toString() {
        return heartRateRecords.toString();
    }


}
