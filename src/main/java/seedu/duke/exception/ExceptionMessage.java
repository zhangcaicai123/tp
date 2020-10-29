package seedu.duke.exception;

public class ExceptionMessage extends Exception {
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /*public static void printIllegalCommandExceptionMessage() {
        printLine();
        System.out.printf("\t  OOPS!!! I'm sorry, but I don't know what that means :-(%n");
        printLine();
    }
*/
    public static void printEmptyDescriptionExceptionMessage(String option) {
        printLine();
        System.out.printf("\t  OOPS!!! The description of a %s cannot be empty.%n", option);
        printLine();
    }

    public static void printEmptyTimeExceptionMessage(String option) {
        printLine();
        System.out.printf("\t  OOPS!!! You haven't set a time for the %s.%n", option);
        printLine();
    }

    public static void printEmptyIndexExceptionMessage() {
        printLine();
        System.out.printf("\t  OOPS!!! You did not type the index of the task.%n");
        printLine();
    }

    public static void printEmptyKeywordMessage() {
        printLine();
        System.out.printf("\t  OOPS!!! You did not type the keyword.%n");
        printLine();
    }
}
