package seedu.duke;

import seedu.duke.task.Task;

public class Ui {
    static String lineCutOff = "_______________________________________________________";

    /**
     * Prints the logo of CEGMods and prompts for command 'help'.
     */
    public static void printWelcomeMessage() {
        String logo =
                  " ██████╗███████╗ ██████╗ ███╗   ███╗ ██████╗ ██████╗ ███████╗\n" +
                "██╔════╝██╔════╝██╔════╝ ████╗ ████║██╔═══██╗██╔══██╗██╔════╝\n" +
                "██║     █████╗  ██║  ███╗██╔████╔██║██║   ██║██║  ██║███████╗\n" +
                "██║     ██╔══╝  ██║   ██║██║╚██╔╝██║██║   ██║██║  ██║╚════██║\n" +
                "╚██████╗███████╗╚██████╔╝██║ ╚═╝ ██║╚██████╔╝██████╔╝███████║\n" +
                " ╚═════╝╚══════╝ ╚═════╝ ╚═╝     ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝\n";


        System.out.println(lineCutOff);
        System.out.println(logo);
        System.out.println("Hello, this is CEGMods! What can I do for you?\n"
                + "Enter 'help' to get more information.");
        System.out.println(lineCutOff);
    }

    /**
     * Prints content of help message.
     */
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
                + "11. View task list: task list\n"
                + "12. Mark a task as done: done task/<TASK_INDEX>\n"
                + "13. Delete task: delete <TASK_INDEX>\n"
                + "14. Mark task as done: done <TASK_INDEX>\n"
                + "15. Exit CEGMods: exit"
        );
        System.out.println(lineCutOff);
    }

    /**
     * Prints content of bye message.
     */
    public static void printByeMessage() {
        System.out.println(lineCutOff);
        System.out.println("Bye! Have a nice day with CEG!");
        System.out.println(lineCutOff);
    }

    /**
     * Prints error message if delete index is out of bound.
     * If it is not a delete exception, prints standard error message.
     *
     * @param command  Command input by user.
     */
    public static void dealWithException(String command) {
        if (command.contains("delete")) {
            System.out.println(" OOPS!!! The delete index is out of bound.");
        } else {
            System.out.println((" OOPS!!! I'm sorry, but I don't know what that means. "
                    + "Please follow the input format correctly."));
        }
    }

    /**
     * Prints statement that there is file loading error.
     */
    public void showLoadingError() {
        System.out.printf("\tThere are some errors when loading file.%n");
    }

    /**
     * Print affirmative statement of task being marked done.
     *
     * @param task Task that is marked done.
     */
    public static void printMarkMessage(Task task) {
        System.out.println(lineCutOff);
        System.out.printf(" \tNice! I've marked this task as done:%n");
        System.out.println("\t  " + task);
        System.out.println(lineCutOff);
    }

}
