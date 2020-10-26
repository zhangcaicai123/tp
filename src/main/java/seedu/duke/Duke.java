package seedu.duke;

import java.util.Scanner;

public class Duke {

    Scanner in = new Scanner(System.in);
    Parser parser = new Parser();
    Ui ui = new Ui();
    String userCommand;

    void run() throws DukeException {
        while (!parser.isExit) {
            userCommand = in.nextLine();
            parser.parse(userCommand);
        }
    }

    public static void main(String[] args) throws DukeException {
        Ui.printWelcomeMessage();
        new Duke().run();
        Ui.bye();
    }
}
