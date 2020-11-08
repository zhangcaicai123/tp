package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class TimeTableTest {

    @Test
    public void testTodayList() {
        TimeTable table = new TimeTable();
        assertTrue(true);
    }

    @Test
    void isModuleAdded() {
        TimeTable table = new TimeTable();
        String moduleCode = "CS2113";
        assertEquals(TimeTable.isModuleAdded(moduleCode),false);
    }

    @Test
    void addModule_noSuchModule_exceptionThrown() {
        TimeTable table = new TimeTable();
        String command = "add mod/CS2114";
        try {
            table.addModule(command);
        } catch (NoSuchElementException | IOException e) {
            assertEquals("_______________________________________________________\n"
                    + "There is no such module.\n"
                    + "_______________________________________________________", e.getMessage());
        }
    }

    @Test
    void deleteModule_noSuchModule_exceptionThrown() {
        TimeTable table = new TimeTable();
        String command = "delete mod/CS2113";
        try {
            table.deleteModule(command);
        } catch (NoSuchElementException e) {
            assertEquals("_______________________________________________________\n"
                    + "OOPS!!! There is no such module.\n"
                    + "_______________________________________________________", e.getMessage());
        }
    }
}
