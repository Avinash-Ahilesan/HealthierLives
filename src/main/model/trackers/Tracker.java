package model.trackers;

import model.trackers.recording.TrackerRecording;

public interface Tracker {

    //EFFECTS: Generates a table filled with the recordings
    void generateTable();

    void addRecording(TrackerRecording r);

    //EFFECTS: Returns the last inputted recording
    TrackerRecording getLastRecording();

}
