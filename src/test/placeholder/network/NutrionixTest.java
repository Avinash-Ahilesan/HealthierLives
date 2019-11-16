package placeholder.network;

import network.Nutrionix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NutrionixTest {

    Nutrionix ix;
    @BeforeEach
    public void beforeEach() {
        ix = new Nutrionix();
    }

    @Test
    public void testSearchFood() {
        System.out.println(ix.getFood("big mac"));
    }
}
