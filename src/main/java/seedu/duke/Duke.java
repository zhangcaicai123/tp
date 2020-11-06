package seedu.duke;

import org.json.simple.parser.ParseException;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    Scanner in = new Scanner(System.in);
    Ui ui = new Ui();
    String userCommand;
    Storage storage = new Storage();
    TaskList tasks;
    TimeTable modules;
    Parser parser = new Parser();

    /**
     * Runs java file.
     */
    void run() {

        try {
            //load tasks in data file to current task list
            tasks = new TaskList(storage.loadTask());
            modules = new TimeTable(storage.loadModule());
            storage.loadJson();
            ModDataBase.getModFromFile();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException | ParseException e) {
            System.out.println("Some errors occurred during the process of fetching data.");
            System.out.println("Please check your Internet Connection.");
        }
        while (!Parser.isExit) {
            try {
                userCommand = in.nextLine();
                parser.parse(userCommand, tasks, storage);
            } catch ( NoSuchElementException e){
            }

        }
    }


    /**
     * Runs main.
     *
     * @param args User input.
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        new Duke().run();
    }
}
