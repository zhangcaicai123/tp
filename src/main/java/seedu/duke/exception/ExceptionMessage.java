package seedu.duke.exception;

//@@author JinYixuan-Au
public class ExceptionMessage extends Exception {
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void printEmptyDescriptionExceptionMessage(String option) {
        printLine();
        System.out.printf("\t  OOPS!!! The description of a %s cannot be empty.%n", option);
        printLine();
    }

    public static void printEmptyTimeExceptionMessage(String option) {
        printLine();
        System.out.printf("\t  OOPS!!! You haven't set a time for the %s or the time format is incorrect.%n", option);
        printLine();
    }

    public static void printEmptyKeywordMessage() {
        printLine();
        System.out.printf("\t  OOPS!!! You did not type the keyword.%n");
        printLine();
    }
}
