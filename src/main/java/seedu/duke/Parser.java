package seedu.duke;

import java.util.Scanner;
import java.util.regex.Pattern;
import seedu.duke.TaskList;

public class Parser {

    boolean isExit = false;

    void parse(String userCommand) {

        boolean isPrintHelpCommand = userCommand.toLowerCase().contains("help");
        boolean isAddModCommand =
                Pattern.matches("^module[\\s]+mod/[\\S\\s]+lec/[\\s\\S]+tut/[\\s\\S]+", userCommand);
        boolean isAddProjectTaskCommand =
                Pattern.matches("^mod/[\\S\\s]+ptask/[\\s\\S]+by/[\\s\\S]+", userCommand);
        boolean isExitCommand = userCommand.equals("exit");
        boolean isDeleteModule = userCommand.contains("delete m/");
        boolean isAddTaskCommand = Pattern.matches("^mod/[\\S\\s]+task/[\\s\\S]+", userCommand);
        boolean isDeleteTask = userCommand.contains("delete t/");
        boolean isPrintWeeklyTimetable = userCommand.equals("weekly timetable");
        boolean isPrintTodayTimeTable = userCommand.equals("today timetable");
        boolean isPrintProjectTaskList = userCommand.contains("project task list");
        boolean isPrintProgress = userCommand.contains("progress");

        try {
            if (isPrintHelpCommand) {

                Ui.printHelpMessage();

            } else if (isAddModCommand) {

                addModule(userCommand);

            } else if (isExitCommand) {

                isExit = true;

            } else if (isDeleteModule) {

                deleteModule(userCommand);

            } else if (isPrintWeeklyTimetable) {

                TimeTable.printWeeklyTimetable();

            } else if (isPrintTodayTimeTable) {

                TimeTable.printTodayTimetable();

            } else if (isDeleteTask) {

                deleteTask(userCommand);

            } else if (isAddProjectTaskCommand) {

                addProjectTask(userCommand);

            } else if (isPrintProjectTaskList) {

                ProjectManager.printProjectTaskList(userCommand);

            } else if (isPrintProgress) {

                ProjectManager.printProgress(userCommand);

            } else {

                throw new DukeException();

            }
        }catch (DukeException e){

            Ui.dealWithException(userCommand);

        }



    }

    public void addModule(String command) {

        String modName;
        String lecSlot;
        String tutSlot;
        String labSlot;


        modName = command.substring(command.indexOf("mod/"),command.indexOf("lec/"));
        modName = modName.substring(4).trim();

        lecSlot = command.substring(command.indexOf("lec/"),command.indexOf("tut/"));
        lecSlot = lecSlot.substring(4).trim();

        boolean isLabExit = command.contains("lab/");

        if (isLabExit) {

            tutSlot = command.substring(command.indexOf("tut/"),command.indexOf("lab/"));
            tutSlot = tutSlot.substring(4).trim();

            labSlot = command.substring(command.indexOf("lab/")).substring(4).trim();

            Module mod = new Module(modName, lecSlot, tutSlot, labSlot);

            TimeTable.addModule(mod);

        } else {

            tutSlot = command.substring(command.indexOf("tut/")).substring(4).trim();

            Module mod = new Module(modName, lecSlot, tutSlot);

            TimeTable.addModule(mod);

        }
    }

    public void deleteModule(String command) {
        TimeTable.deleteModule(command);
    }

    public void addTask(String command) {
        String modName;
        String description;

        modName = command.substring(command.indexOf("mod/"),command.indexOf("task/"));
        modName = modName.substring(4).trim();

        description = command.substring(command.indexOf("task/")).substring(5).trim();
        Task mod = new Task(modName,description);
        System.out.println("Module: " + mod.modName);
        System.out.println("Task: " + mod.description);

        TaskList.addTaskToList(mod);

    }

    public void deleteTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf("t/")).substring(2).trim());
        TaskList.deleteTaskFromList(taskIndex);
    }

    public void addProjectTask(String command) {

        ProjectManager.addProjectTask(command);
    }

}
