package placeholder.model;

import model.TimeStamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeStampTest {

    TimeStamp testTimeStamp;
    @BeforeEach
    public void setup() {
        testTimeStamp = new TimeStamp(2019 ,2, 24);
    }

    @Test
    public void testEqualsAndCloneAndConstructors() {
        TimeStamp testTimeStamp2 = new TimeStamp(2019, 2, 24);
        TimeStamp cloned = new TimeStamp(testTimeStamp2);
        assertEquals(testTimeStamp2, testTimeStamp);
        assertEquals(cloned, testTimeStamp2);
    }

    @Test
    public void testToString() {
        assertEquals("24/2/2019", testTimeStamp.toString());
    }
}
