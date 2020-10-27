package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManager {
    static String lineCutOff = "_______________________________________________________";
    private static final ArrayList<ProjectTask> projectTasks = new ArrayList<>();

    public static void addProjectTask(String command) {

        String modName;
        String description;

        modName = command.substring(command.indexOf("mod/"),command.indexOf("ptask/"));
        modName = modName.substring(4).trim();

        description = command.substring(command.indexOf("ptask/"),command.indexOf("by/"));
        description = description.substring(6).trim();

        String by;
        by = command.substring(command.indexOf("by/")).substring(3).trim();

        System.out.println(lineCutOff);
        System.out.println("Is there any material you need for this project task?");
        System.out.println("Enter your materials or NA");
        Scanner in = new Scanner(System.in);

        String material = in.nextLine();
        ProjectTask projectTask = new ProjectTask(modName,description,by,material);

        System.out.println("Module: " + projectTask.modName);
        System.out.println("ProjectTask: " + projectTask.description);
        System.out.println("By: " + projectTask.by);
        System.out.println("Materials: " + projectTask.material);
        System.out.println(lineCutOff);
        
        projectTasks.add(projectTask);

    }

    public static void printProjectTaskList(String command) {
        String modName;
        modName = command.substring(command.indexOf("mod/"),command.indexOf("project"));
        modName = modName.substring(4).trim();
        System.out.println(modName + "\n");

        for (int i = 0; i < projectTasks.size(); i++) {
            if (projectTasks.get(i).modName.equals(modName)) {
                System.out.println(i + 1 + ". " + projectTasks.get(i).toString() + "\n");
            }
        }
    }

    public static void printProgress(String command) {
        String modName;
        int numDone = 0;
        int numTotal = 0;
        modName = command.substring(command.indexOf("mod/"),command.indexOf(" "));
        modName = modName.substring(4).trim();

        System.out.println(modName);

        for (int i = 0; i < projectTasks.size(); i++) {
            if (projectTasks.get(i).modName.equals(modName)) {
                numTotal++;
                if (projectTasks.get(i).getStatusIcon().equals('T')) {
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
