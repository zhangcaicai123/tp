package seedu.duke.project;

import seedu.duke.ModDataBase;
import seedu.duke.TimeTable;
import seedu.duke.task.ProjectTask;
import seedu.duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManager {
    static String lineCutOff = "_______________________________________________________";
    public static ArrayList<ProjectTask> projectTasks = new ArrayList<>();

    /**
     * Adds project task to project tasks arraylist.
     *
     * @param command User input.
     */
    public static ProjectTask addProjectTask(String command) {

        String modName;
        String description;

        modName = command.substring(command.indexOf("mod/"), command.indexOf("ptask/"));
        modName = modName.substring(4).trim();

        description = command.substring(command.indexOf("ptask/"), command.indexOf("by/"));
        description = description.substring(6).trim();

        String by;
        by = command.substring(command.indexOf("by/")).substring(3).trim();

        boolean isModuleCode = ModDataBase.modules.containsKey(modName);
        boolean isModuleAdded = TimeTable.isModuleAdded(modName);
        boolean isAddModule = false;
        Scanner in = new Scanner(System.in);
        if (!isModuleCode) {
            System.out.println("This is not a valid module code");
        } else if (description.isBlank() || by.isBlank()) {
            System.out.println("The task description or the deadline is empty.");
        } else if (!isModuleAdded) {
            System.out.println("Are you sure you want to add this project subtask?\n"
                    + "It seems that you did not add this module. Y/N");
            String isAdd = in.nextLine();
            if (isAdd.equalsIgnoreCase("Y")) {
                isAddModule = true;
            } else if (isAdd.equalsIgnoreCase("N")) {
                isAddModule = false;
            } else {
                System.out.println("Sorry, I cannot identify this!");
            }
        }
        if (isAddModule) {
            System.out.println(lineCutOff);
            System.out.println("Is there any material you need for this project task?");
            System.out.println("Enter your materials or NA");


            String material = in.nextLine();
            ProjectTask projectTask = new ProjectTask(modName, description, by, material);

            System.out.println("Module: " + projectTask.getModName());
            System.out.println("ProjectTask: " + projectTask.getDescription());
            System.out.println("By: " + projectTask.getBy());
            System.out.println("Materials: " + projectTask.getMaterial());
            System.out.println(lineCutOff);

            projectTasks.add(projectTask);
            return projectTask;
        }
        return null;
    }

    /**
     * Converts task to string for printing.
     *
     * @param command User input.
     */
    public static void printProjectTaskList(String command) throws StringIndexOutOfBoundsException {
        String modName;
        int numOfTask = 0;
        modName = command.substring(command.indexOf("mod/"), command.indexOf("project"));
        modName = modName.substring(4).trim();

        boolean isModuleCode = ModDataBase.modules.containsKey(modName);

        if (isModuleCode) {
            System.out.println(modName + "\n");
            for (int i = 0; i < projectTasks.size(); i++) {
                if (projectTasks.get(i).getModName().equals(modName)) {
                    System.out.println(i + 1 + ". " + projectTasks.get(i).toString() + "\n");
                    numOfTask++;
                }
            }
            if (numOfTask == 0) {
                System.out.println("There are no project tasks of this module.");
            }
        } else {
            System.out.println("This is not a valid module code.");
        }
    }

    /**
     * Prints progress of project.
     *
     * @param command User input.
     */
    public static void printProgress(String command) {
        String modName;
        int numDone = 0;
        int numTotal = 0;
        modName = command.substring(command.indexOf("mod/"), command.indexOf("project"));
        modName = modName.substring(4).trim();

        boolean isModuleCode = ModDataBase.modules.containsKey(modName);

        if (isModuleCode) {
            System.out.println(modName);
            for (ProjectTask projectTask : projectTasks) {
                if (projectTask.getModName().equals(modName)) {
                    numTotal++;
                    if (projectTask.getStatusIcon().equals("T")) {
                        numDone++;
                    }
                }
            }
            if (numTotal == 0) {
                System.out.println("There are no project tasks of this module.");
            } else {
                float progress = (float) numDone / numTotal * 100;
                System.out.println("You have done " + numDone + "/" + numTotal
                        + " (" + String.format("%.2f", progress) + "%).");
            }
        } else {
            System.out.println("This is not a valid module code.");
        }
    }
}
