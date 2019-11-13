package placeholder.model;

import network.KanyeQuoteFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KanyeTest {

    public KanyeQuoteFetcher kqf;

    @BeforeEach
    public void setup() {
        kqf = new KanyeQuoteFetcher();
    }

    @Test
    public void testGetQuote() {
        System.out.println(kqf.getQuote());
    }

}
