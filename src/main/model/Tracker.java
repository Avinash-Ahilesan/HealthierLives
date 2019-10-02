package model;

interface Tracker {

    void generateTable();

    void saveData();

    TrackerRecording getLastRecording();

}
