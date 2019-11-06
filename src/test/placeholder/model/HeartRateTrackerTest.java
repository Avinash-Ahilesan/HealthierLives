package placeholder.model;

import model.LoadAndSave;
import model.TimeStamp;
import model.trackers.recording.HeartRateRecording;
import model.trackers.HeartRateTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import model.TimeStamp;

public class HeartRateTrackerTest {
    HeartRateTracker tracker;
    TimeStamp day1;
    TimeStamp day2;
    TimeStamp day3;

    @BeforeEach
    public void setup() {
        tracker = new HeartRateTracker();
        day1 = new TimeStamp(2017, 3, 18);
        day2 = new TimeStamp(2018, 5, 3);
        day3 = new TimeStamp(2019, 7, 24);
        tracker.addHeartRate(day1, 80);
        tracker.addHeartRate(day2, 75);
        tracker.addHeartRate(day3, 91);
    }

    @Test
    public void testGetLastRecording() {
        assertEquals(tracker.getLastRecording(), new HeartRateRecording(day3, 91));
        TimeStamp day4 = new TimeStamp(2021, 2, 23);
        tracker.addHeartRate(day4, 125);
        assertEquals(new HeartRateRecording(day4, 125), tracker.getLastRecording());
    }

    @Test
    public void testSaveAndLoad() {
        LoadAndSave.save(false, "heartRateRecordingSave.txt", tracker);
        ArrayList<Object> heartRates = LoadAndSave.load("heartRateRecordingSave.txt");
        assertEquals(1, heartRates.size());
        HeartRateTracker loadedTracker = (HeartRateTracker) heartRates.get(0);
        assertEquals(tracker.getLastRecording(), loadedTracker.getLastRecording());
    }


}
