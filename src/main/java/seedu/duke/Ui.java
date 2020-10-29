package seedu.duke;

import seedu.duke.task.Task;

public class Ui {
    static String lineCutOff = "_______________________________________________________";

    public static void printWelcomeMessage() {
        System.out.println(lineCutOff);
        System.out.println("Hello, this is CEGMods! What can I do for you?\n"
                + "Enter 'help' to get more information.");
        System.out.println(lineCutOff);
    }

    public static void printHelpMessage() {
        System.out.println(lineCutOff);
        System.out.println("1. Add a module: add mod/<MODULE_CODE>\n"
                + "2. Delete a module: delete mod/<MODULE_CODE>\n"
                + "3. Add a task to do: todo <DESCRIPTION>\n"
                + "4. Add a deadline: deadline <DESCRIPTION> /by <YYYY-MM-DD HH-MM>\n"
                + "5. Add an event: event <DESCRIPTION> /at <YYYY-MM-DD HH-MM>\n"
                + "6. View today's timetable: today timetable\n"
                + "7. View weekly timetable: this week timetable\n"
                + "8. Add a project subtask: mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE> \n"
                + "9. View project task list: mod/<MODULE_CODE> project task list\n"
                + "10. View project progress: mod/<MODULE_CODE> progress\n"
                + "11. Exit CEGMods: exit"
        );
        System.out.println(lineCutOff);
    }

    public static void printByeMessage() {
        System.out.println(lineCutOff);
        System.out.println("Bye! Have a nice day with CEG!");
        System.out.println(lineCutOff);
    }

    public static void dealWithException(String command) {
        if (command.contains("delete")) {
            System.out.println(" OOPS!!! The delete index is out of bound.");
        } else {
            System.out.println((" OOPS!!! I'm sorry, but I don't know what that means. "
                    + "Please follow the input format correctly."));
        }
    }

    public void showLoadingError() {
        System.out.printf("\tThere are some errors when loading file.%n");
    }

    public static void printMarkMessage(Task task) {
        System.out.println(lineCutOff);
        System.out.printf(" \tNice! I've marked this task as done:%n");
        System.out.println("\t  " + task);
        System.out.println(lineCutOff);
    }

}
