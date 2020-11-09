package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.stub.DukeStub;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TodoTest {

    @Test
    void testTodo() {
        Todo todo = new Todo(new DukeStub().getDescription());
        assertTrue(true);
    }

    @Test
    void testToString() {
        Todo todo = new Todo(new DukeStub().getDescription());
        assertEquals("[T]" + "[" + "F" + "]" + "Task Description", todo.toString());
    }

    @Test
    void testText() {
        Todo todo = new Todo(new DukeStub().getDescription());
        assertEquals("T " + "| 0 | " + "Task Description", todo.text());
    }


}