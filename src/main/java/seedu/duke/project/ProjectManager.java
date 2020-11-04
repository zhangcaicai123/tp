package seedu.duke.project;

import seedu.duke.TimeTable;
import seedu.duke.task.ProjectTask;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManager {
    static String lineCutOff = "_______________________________________________________";
    private static final ArrayList<ProjectTask> projectTasks = new ArrayList<>();

    public static ProjectTask addProjectTask(String command) {

        String modName;
        String description;

        modName = command.substring(command.indexOf("mod/"), command.indexOf("ptask/"));
        modName = modName.substring(4).trim();

        description = command.substring(command.indexOf("ptask/"), command.indexOf("by/"));
        description = description.substring(6).trim();

        String by;
        by = command.substring(command.indexOf("by/")).substring(3).trim();

        boolean isModuleAdded = TimeTable.isModuleAdded(modName);
        boolean isAddModule = false;
        Scanner in = new Scanner(System.in);
        if (!isModuleAdded) {
            System.out.println("Are you sure you want to add this project subtask?\n"
                    + "It seems that you did not add this module. Y/N");
            String isAdd = in.nextLine();
            if (isAdd.equalsIgnoreCase("Y")) {
                isAddModule = true;
            }
        } else {
            isAddModule = true;
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

    public static void printProjectTaskList(String command) {
        String modName;
        modName = command.substring(command.indexOf("mod/"), command.indexOf("project"));
        modName = modName.substring(4).trim();
        System.out.println(modName + "\n");

        for (int i = 0; i < projectTasks.size(); i++) {
            if (projectTasks.get(i).getModName().equals(modName)) {
                System.out.println(i + 1 + ". " + projectTasks.get(i).toString() + "\n");
            }
        }
    }

    public static void printProgress(String command) {
        String modName;
        int numDone = 0;
        int numTotal = 0;
        modName = command.substring(command.indexOf("mod/"), command.indexOf(" "));
        modName = modName.substring(4).trim();

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
            System.out.println("WELL DONE! 100% progress!");
        } else {
            float progress = (float) numDone / numTotal * 100;
            System.out.println("You have done " + numDone + "/" + numTotal
                    + " (" + String.format("%.2f", progress) + "%).");
        }
    }
}
