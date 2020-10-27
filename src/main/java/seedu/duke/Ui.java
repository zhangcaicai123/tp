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
        System.out.println(lineCutOff);
        System.out.println("1. Add a module: module mod/<MODULE_CODE> "
                + "lec/<LECTURE_DAY> <LECTURE_TIME> tut/<TUTORIAL_DAY> <TUTORIAL_TIME> "
                + "lab/<LAB_DAY> <LAB_TIME> (lab slot is optional)\n"
                + "2. Delete a module: delete m/<MODULE_CODE>\n"
                + "3. View today's timetable: today timetable\n"
                + "4. View weekly timetable: weekly timetable\n"
                + "5. Add a project subtask: mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE> \n"
                + "6. View project task list: mod/<MODULE_CODE> project task list\n"
                + "7. View project progress: mod/<MODULE_CODE> progress\n"
                + "8. Exit CEGMods: exit"
        );
        System.out.println(lineCutOff);
    }

    public static void bye() {
        System.out.println(lineCutOff);
        System.out.println("Bye! Have a nice day with CEG!");
        System.out.println(lineCutOff);
    }

    public static void dealWithException(String command){
        if(command.equals("todo")){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }else if(command.equals("deadline")){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }else if(command.equals("event")) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        }else if(command.contains("done")) {
            System.out.println("☹ OOPS!!! The done index is out of bound.");
        }else if(command.contains("delete")) {
            System.out.println("☹ OOPS!!! The delete index is out of bound.");
        }else{
            System.out.println(("☹ OOPS!!! I'm sorry, but I don't know what that means. Please follow the input format correctly."));
        }
    }
}
