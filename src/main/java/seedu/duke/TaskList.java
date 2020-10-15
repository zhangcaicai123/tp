package seedu.duke;

import java.util.ArrayList;

import seedu.duke.Task;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public static void addTaskToList(Task newTask){
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:\n" + newTask.description);
        System.out.format("Now you have %s task%s in the list.\n", taskList.size(),
                ((taskList.size() == 1 ? "" : "s")));

    }

    public static void deleteTaskFromList(int taskIndex){
        System.out.println("Noted. I've removed this task:" + taskList.get(taskIndex));
        taskList.remove(taskIndex);
        System.out.format("Now you have %s task%s in the list.\n", taskList.size(), ((taskList.size() == 1 ? "" : "s")));
    }
}
