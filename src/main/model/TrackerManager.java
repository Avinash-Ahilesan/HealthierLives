package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TrackerManager {

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
