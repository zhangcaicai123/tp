package seedu.duke;

import org.json.simple.parser.ParseException;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    Scanner in = new Scanner(System.in);
    Ui ui = new Ui();
    String userCommand;
    Storage storage = new Storage();
    TaskList tasks;
    Parser parser = new Parser();

    void run() {

        try {
            //load tasks in data file to current task list
            tasks = new TaskList(storage.load());

            ModDataBase.getModFromFile();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException | ParseException e) {
            System.out.println("Some errors occurred in module database.");
        }
        while (!Parser.isExit) {
            userCommand = in.nextLine();
            parser.parse(userCommand, tasks, storage);
        }
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        new Duke().run();
    }
}
