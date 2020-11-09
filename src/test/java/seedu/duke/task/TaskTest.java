package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.stub.DukeStub;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TaskTest {

    @Test
    void testGetDescription() {
        Task task = new Task(new DukeStub().getDescription());
        assertEquals("Task Description", task.getDescription());
    }

    @Test
    void testMarkAsDone() {
        Task task = new Task(new DukeStub().getDescription());
        task.markAsDone();
        assertEquals("Task Description", task.getDescription());
    }

    @Test
    void testGetStatusIcon() {
        Task task = new Task(new DukeStub().getDescription());
        task.getStatusIcon();
        assertEquals("F", task.getStatusIcon());
    }

    @Test
    void testToString() {
        Task task = new Task(new DukeStub().getDescription());
        assertEquals("[" + "F" + "]" + "Task Description", task.toString());
    }

    @Test
    void testText() {
        Task task = new Task(new DukeStub().getDescription());
        assertEquals("| 0 | " + "Task Description", task.text());
    }



}
