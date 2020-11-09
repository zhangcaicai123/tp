package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyDescriptionException;

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
        Command command = new Command();
        String userCommand = "todo read book";
        assertEquals("read book", command.getTodo(userCommand));
    }
}