package model;

interface Tracker {

    //EFFECTS: Generates a table filled with the recordings
    void generateTable();


    //EFFECTS: Returns the last inputted recording
    TrackerRecording getLastRecording();

}
