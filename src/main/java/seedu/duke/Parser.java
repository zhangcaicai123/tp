package seedu.duke;

import java.util.regex.Pattern;

public class Parser {

    boolean isExit = false;

    void parse(String userCommand) {

        boolean isAddModCommand =
                Pattern.matches("^module[\\s]+mod/[\\S\\s]+lec/[\\s\\S]+tut/[\\s\\S]+", userCommand);

        boolean isExitCommand = userCommand.equals("exit");
        boolean isDeleteModule = userCommand.contains("delete m/");
        boolean isAddTaskCommand = userCommand.contains("task");
        boolean isPrintWeeklyTimetable = userCommand.equals("weekly timetable");
        boolean isPrintTodayTimeTable = userCommand.equals("today timetable");
        if (isAddModCommand) {

            addModule(userCommand);

        } else if (isExitCommand) {

            isExit = true;

        } else if (isDeleteModule) {
            deleteModule(userCommand);
        } else if (isAddTaskCommand) {
            addTask(userCommand);
        } else if (isPrintWeeklyTimetable) {
            TimeTable.printWeeklyTimetable();
        } else if (isPrintTodayTimeTable) {
            TimeTable.printTodayTimetable();
        }


    }

    public void addModule(String command) {

        String modName;
        String lecSlot;
        String tutSlot;
        String labSlot;


        modName = command.substring(command.indexOf("mod/"), command.indexOf("lec/"));
        modName = modName.substring(4).trim();

        lecSlot = command.substring(command.indexOf("lec/"), command.indexOf("tut/"));
        lecSlot = lecSlot.substring(4).trim();

        boolean isLabExit = command.contains("lab/");

        if (isLabExit) {

            tutSlot = command.substring(command.indexOf("tut/"), command.indexOf("lab/"));
            tutSlot = tutSlot.substring(4).trim();

            labSlot = command.substring(command.indexOf("lab/")).substring(4).trim();

            Module mod = new Module(modName, lecSlot, tutSlot, labSlot);
            TimeTable.addModule(mod);

            System.out.println("Module: " + mod.modName);
            System.out.println("Lecture Slot: " + mod.lecSlot);
            System.out.println("Tutorial Slot: " + mod.tutSlot);
            System.out.println("Lab Slot: " + mod.labSlot);

        } else {

            tutSlot = command.substring(command.indexOf("tut/")).substring(4).trim();

            Module mod = new Module(modName, lecSlot, tutSlot);
            TimeTable.addModule(mod);

            System.out.println("Module: " + mod.modName);
            System.out.println("Lecture Slot: " + mod.lecSlot);
            System.out.println("Tutorial Slot: " + mod.tutSlot);

        }
    }

    public void deleteModule(String command) {
        TimeTable.deleteModule(command);
    }

    public void addTask(String command) {
        String modName;
        String description;

        modName = command.substring(command.indexOf("mod/"), command.indexOf("task/"));
        modName = modName.substring(4).trim();

        description = command.substring(command.indexOf("task/")).substring(5).trim();
        Task mod = new Task(modName, description);
        System.out.println("Module: " + mod.modName);
        System.out.println("Task: " + mod.description);
    }


}
