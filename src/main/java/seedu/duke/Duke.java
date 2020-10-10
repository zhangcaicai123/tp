package seedu.duke;

import java.util.Scanner;

public class Duke {

    Scanner in = new Scanner(System.in);
    Parser parser = new Parser();

    void run() {

        while (!parser.isExit) {
            String userCommand = in.nextLine();
            parser.parse(userCommand);
        }

    }


    public static void main(String[] args) {

        System.out.println("Hello, this is CEGMods! What can I do for you?");
        new Duke().run();

    }
}
