package seedu.duke;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    Scanner in = new Scanner(System.in);
    Parser parser = new Parser();
    Ui ui = new Ui();
    String userCommand;

    void run() throws IOException, ParseException {
        while (!parser.isExit) {
            userCommand = in.nextLine();
            parser.parse(userCommand);
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        Ui.printWelcomeMessage();
        Ui.printHelpMessage();
        ModDataBase.getModFromFile();
        new Duke().run();
    }
}
