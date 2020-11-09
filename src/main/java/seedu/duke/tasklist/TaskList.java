package seedu.duke.tasklist;

import seedu.duke.project.ProjectManager;
import seedu.duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs TaskList object.
     */
    public TaskList() {
    }

    /**
     * Assign the loaded list to this task list.
     *
     * @param loadedList the task list loaded from
     */
    public TaskList(ArrayList<Task> loadedList) {
        this.taskList = loadedList;
    }

    /**
     * Returns taskList arraylist.
     *
     * @return taskList arraylist.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Add new task to the task list.
     *
     * @param taskToAdd the task that needs to be added to the list
     */
    public void addTask(Task taskToAdd) {
        this.taskList.add(taskToAdd);
        printAddMessage(taskToAdd);
        printNumOfTasksInList();
    }

    /**
     * Delete task from the task list with index of task.
     *
     * @param taskIndex the index of task which needs to be deleted
     */
    public void deleteTask(int taskIndex) {
        boolean isExist = false;
        for (int i = 0; i < ProjectManager.projectTasks.size(); i++) {
            if (taskList.get(taskIndex) == ProjectManager.projectTasks.get(i)) {
                ProjectManager.projectTasks.remove(i);
                break;
            }
        }
        for (int i = 0; i < ProjectManager.projectTasks.size(); i++) {
            for (int j = 0; j < taskList.size(); j++) {
                if (taskList.get(j) == ProjectManager.projectTasks.get(i)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                ProjectManager.projectTasks.remove(i);
            }
        }
        Task task = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        printDeleteMessage(task);
    }

    /**
     * Get the task with index.
     *
     * @param index the index of task
     * @return task
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Prints line.
     */
    private static void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Returns size of taskList arraylist.
     *
     * @return size of taskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Print the whole list.
     */
    public static void printList() {
        showLine();
        if (taskList.size() > 0) {
            System.out.println("     Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println("      " + (taskList.indexOf(task) + 1) + "." + task);
            }
        } else {
            System.out.printf("\tYou don't have any task in your list.%n");
        }
        showLine();
    }

    /**
     * Print the message of adding new task.
     * @param task task needs to add
     */
    private void printAddMessage(Task task) {
        showLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
    }

    /**
     * Print the number of tasks in the list.
     */
    public void printNumOfTasksInList() {
        if (this.taskList.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else if (this.taskList.size() == 0) {
            System.out.println("     You don't have any task in your list.");
        } else {
            System.out.println("     Now you have " + this.taskList.size() + " tasks in the list.");
        }
        showLine();
    }

    /**
     * Print the message of deleting a task.
     *
     * @param task the task that needs to be deleted
     */
    public void printDeleteMessage(Task task) {
        showLine();
        System.out.printf("\tNoted. I've removed this task:%n");
        System.out.printf("\t   %s%n", task);
    }


    /**
     * Find all the tasks which contain the keyword.
     *
     * @param keyword keyword to find task
     * @return the list of tasks which contain the keyword
     */
    private ArrayList<Task> find(String keyword) {
        ArrayList<Task> findList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                findList.add(task);
            }
        }
        return findList;
    }

    /**
     * Print the list of searching keyword in task list.
     *
     * @param keyword keyword to find task
     */
    public void printSearchResult(String keyword) {
        ArrayList<Task> results = find(keyword);
        showLine();
        if (results.size() > 0) {
            System.out.printf("\tHere are the matching tasks in your list:%n");
            for (Task task : results) {
                System.out.println("\t" + (results.indexOf(task) + 1) + "." + task);
            }
        } else {
            System.out.printf("\tYou don't have any matching task in your list.%n");
        }
        showLine();
    }

}
