package seedu.duke;

public class Ui {
    static String lineCutOff = "_______________________________________________________";

    public static void printWelcomeMessage() {
        System.out.println(lineCutOff);
        System.out.println("Hello, this is CEGMods! What can I do for you?\n"
                + "Enter help to get more information.");
        System.out.println(lineCutOff);
    }

    public static void printHelpMessage() {
        System.out.println("1. Add a module: module mod/<MODULE_CODE> "
                + "lec/<LECTURE_DAY> <LECTURE_TIME> tut/<TUTORIAL_DAY> <TUTORIAL_TIME> "
                + "lab/<LAB_DAY> <LAB_TIME> (lab slot is optional)\n"
                + "2. Delete a module: delete m/<MODULE_CODE>\n"
                + "3. View today's timetable: today timetable\n"
                + "4. View weekly timetable: weekly timetable\n"
                + "5. Exit CEGMods: exit"
        );
        System.out.println(lineCutOff);
    }
}
