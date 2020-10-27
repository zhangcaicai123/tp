package seedu.duke;

import java.util.Scanner;
import java.util.regex.Pattern;
import seedu.duke.TaskList;

public class Parser {
//
//
//
      boolean isExit = false;
//
    void parse(String userCommand) {

        boolean isAddModCommand = Pattern.matches("add[\\s]+module/[\\S\\s]+", userCommand);

        boolean isExitCommand = userCommand.equals("exit");
        boolean isDeleteModule = userCommand.contains("delete m/");
        boolean isAddTaskCommand = userCommand.contains("task");
        boolean isDeleteTask = userCommand.contains("delete t/");
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



        } else if (isPrintTodayTimeTable) {



        } else if (isDeleteTask) {

            deleteTask(userCommand);

        }


    }

    public void addModule(String command) {

        String moduleCode = command.substring(command.indexOf("/")+1).trim();
        boolean isModuleExit = ModDataBase.modules.containsKey(moduleCode);

        if (isModuleExit) {
//            Scanner in = new Scanner(System.in);
//            System.out.println("lecSlot: ");
//            ModDataBase.modules.get(moduleCode).lecSlot = in.nextLine();
//            System.out.println("tutSlot: ");
//            ModDataBase.modules.get(moduleCode).tutSlot = in.nextLine();
//            System.out.println("labSlot: ");
//            ModDataBase.modules.get(moduleCode).labSlot = in.nextLine();
            System.out.println(ModDataBase.modules.get(moduleCode).description);
            System.out.println(ModDataBase.modules.get(moduleCode).moduleCredit);
        }else {
            System.out.println("hahaha");
        }




    }

    public void deleteModule(String command) {

    }

    public void addTask(String command) {
        String modName;
        String description;

        modName = command.substring(command.indexOf("mod/"),command.indexOf("task/"));
        modName = modName.substring(4).trim();

        description = command.substring(command.indexOf("task/")).substring(5).trim();
//        Task mod = new Task(modName,description);
//        System.out.println("Task: " + mod.description);
//
//        TaskList.addTaskToList(mod);

    }

    public void deleteTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf("t/")).substring(2).trim());
        TaskList.deleteTaskFromList(taskIndex);
    }

}
