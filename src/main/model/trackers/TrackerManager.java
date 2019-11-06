package model.trackers;

import model.trackers.recording.TrackerRecording;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TrackerManager implements Serializable {

    private Map<TrackerNames, Tracker> trackers;

    public TrackerManager() {
        trackers = new HashMap<>();
    }

    public void addTracker(TrackerNames trackerName, Tracker tracker) {
        trackers.put(trackerName, tracker);
    }


    public Tracker getTracker(TrackerNames tn) {
        return trackers.get(tn);
    }

    public void addRecording(TrackerNames tn, TrackerRecording r) {
        trackers.get(tn).addRecording(r);
    }

    public enum TrackerNames { HEARTRATE, SLEEP }

}
