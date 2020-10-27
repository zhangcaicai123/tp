package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;

import java.util.Scanner;

public class Duke {

    Scanner in = new Scanner(System.in);
    Ui ui = new Ui();
    String userCommand;
    Storage storage = new Storage();
    TaskList tasks;

    void run() {
        try {
            //load tasks in data file to current task list
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        while (!Parser.isExit) {
            userCommand = in.nextLine();
            Parser.parse(userCommand, tasks, storage);
        }
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        new Duke().run();
        Ui.bye();
    }
}
