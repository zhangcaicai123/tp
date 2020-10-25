package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleTest {

    @Test
    public void testText() {

        Module testModule = new Module("MA1511", "Mon 12:00", "Fri 10:00");
        assertEquals("12:00 MA1511 lecture", testModule.lecText());
        assertEquals("10:00 MA1511 tutorial", testModule.tutText());
    }

}
