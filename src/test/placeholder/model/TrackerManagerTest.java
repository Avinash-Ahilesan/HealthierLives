package placeholder.model;

import model.TimeStamp;
import model.trackers.recording.HeartRateRecording;
import model.trackers.HeartRateTracker;
import model.trackers.SleepTracker;
import model.trackers.TrackerManager;
import model.trackers.TrackerManager.TrackerNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackerManagerTest {
    TrackerManager tm;
    HeartRateTracker heartRateTracker = new HeartRateTracker();
    SleepTracker sleepTracker = new SleepTracker();
    @BeforeEach
    public void setup() {
        tm = new TrackerManager();
    }

    @Test
    public void testAddTracker() {
        assertEquals(null, tm.getTracker(TrackerNames.HEARTRATE));
        assertEquals(null, tm.getTracker(TrackerNames.HEARTRATE));
        tm.addTracker(TrackerNames.HEARTRATE, heartRateTracker);
        tm.addTracker(TrackerNames.SLEEP, sleepTracker);
        assertEquals(tm.getTracker(TrackerNames.HEARTRATE), heartRateTracker);
        assertEquals(tm.getTracker(TrackerNames.HEARTRATE), heartRateTracker);
    }

    @Test
    public void testAddRecording() {
        TimeStamp date = new TimeStamp(2019, 2,3);
        HeartRateRecording recording = new HeartRateRecording(date, 29);
        tm.addTracker(TrackerNames.HEARTRATE, heartRateTracker);
        tm.addRecording(TrackerNames.HEARTRATE, recording);

        assertEquals(recording ,tm.getTracker(TrackerNames.HEARTRATE).getLastRecording());
    }

}
