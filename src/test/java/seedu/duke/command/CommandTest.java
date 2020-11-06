package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class CommandTest {

    @Test
    void getTodo_descriptionCannotBeEmpty_exceptionThrown() {
        Command command = new Command();
        String userCommand = "todo";
        try {
            command.getTodo(userCommand);
        } catch (IllegalStateException e) {
            assertEquals("\t  OOPS!!! The description of a todo cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    void getTodo_returnDescription() {
        Command command = new Command();
        String userCommand = "todo read book";
        assertEquals("read book", command.getTodo(userCommand));
    }
}