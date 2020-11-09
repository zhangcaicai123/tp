package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyTimeException;

import static junit.framework.Assert.assertEquals;

class CommandTest {

    @Test
    void getTodo_descriptionCannotBeEmpty_exceptionThrown() {
        Command command = new Command();
        String userCommand = "todo";
        try {
            command.getTodo(userCommand);
        } catch (EmptyDescriptionException e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void getTodo_returnDescription() throws EmptyDescriptionException {
        String userCommand = "todo read book";
        assertEquals("read book", Command.getTodo(userCommand));
    }

    @Test
    void getTask_returnDescription() throws EmptyDescriptionException, EmptyTimeException {
        String userCommand1 = "event team meeting /at 2020-11-09 17:25";
        String userCommand2 = "deadline assignment /by 2020-11-09 23:59";
        assertEquals("team meeting",Command.getTask(userCommand1));
        assertEquals("2020-11-09 17:25",Command.getTime(userCommand1));
        assertEquals("assignment",Command.getTask(userCommand2));
        assertEquals("2020-11-09 23:59",Command.getTime(userCommand2));
    }

}