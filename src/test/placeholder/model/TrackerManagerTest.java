package placeholder.model;

import model.*;
import model.TrackerManager.TrackerNames;
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
        Calendar date = new Calendar.Builder().setDate(2019, 2,3).build();
        HeartRateRecording recording = new HeartRateRecording(date, 29);
        tm.addTracker(TrackerNames.HEARTRATE, heartRateTracker);
        tm.addRecording(TrackerNames.HEARTRATE, recording);

        assertEquals(recording ,tm.getTracker(TrackerNames.HEARTRATE).getLastRecording());
    }

}
