package placeholder.model;

import model.HeartRateRecording;
import model.HeartRateTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class HeartRateTrackerTest {
    HeartRateTracker tracker;
    Calendar day1;
    Calendar day2;
    Calendar day3;

    @BeforeEach
    public void setup() {
        tracker = new HeartRateTracker();
        day1 = new Calendar.Builder().setDate(2017, 3, 18).build();
        day2 = new Calendar.Builder().setDate(2018, 5, 3).build();
        day3 = new Calendar.Builder().setDate(2019, 7, 24).build();
        tracker.addHeartRate(day1, 80);
        tracker.addHeartRate(day2, 75);
        tracker.addHeartRate(day3, 91);
    }

    @Test
    public void testGetLastRecording() {
        assertEquals(tracker.getLastRecording(), new HeartRateRecording(day3, 91));
        Calendar day4 = new Calendar.Builder().setDate(2021, 2, 23).build();
        tracker.addHeartRate(day4, 125);
        assertEquals(new HeartRateRecording(day4, 125), tracker.getLastRecording());
    }

    @Test
    public void testSaveAndLoad() {
        tracker.save(false);

        ArrayList<Object> heartRates = tracker.load();
        assertEquals(1, heartRates.size());
        HeartRateTracker loadedTracker = (HeartRateTracker) heartRates.get(0);
        assertEquals(tracker.getLastRecording(), loadedTracker.getLastRecording());
    }


}
