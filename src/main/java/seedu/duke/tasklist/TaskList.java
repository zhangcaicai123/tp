package seedu.duke.tasklist;

import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;

import java.io.IOException;
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
        int num = 0;
        if (taskList.size() > 0) {
            System.out.println("     Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println("      " + (taskList.indexOf(task) + 1) + "." + task);
                if (task.getStatusIcon().equals("F")) {
                    num++;
                }
            }
            System.out.printf("\tYou  have %d undone task in your list. (%d/%d)%n",num,num,taskList.size());
        } else {
            System.out.printf("\tYou don't have any task in your list.%n");
        }
        showLine();
    }

    /**
     * Print the tasks have not marked as done
     */
    public static void printUndoneList() {
        showLine();
        int num = 0;
        if (taskList.size() > 0) {
            System.out.println("     Here are the undone tasks in your list:");
            for (Task task : taskList) {
                if(task.getStatusIcon().equals("F")) {
                    num++;
                    System.out.println("      " + num + "." + task);
                }
            }
            if (num > 1) {
                System.out.printf("\tYou have %d undone tasks in your list.%n", num);
            } else if (num == 1){
                System.out.printf("\tYou have 1 undone task in your list.%n", num);
            } else {
                System.out.printf("\tYou don't have any undone task in your list.%n", num);
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
    public static void printNumOfTasksInList() {
        if (taskList.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else if (taskList.size() == 0) {
            System.out.println("     You don't have any task in your list.");
        } else {
            System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
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
                System.out.println("\t" + (results.indexOf(task) + 1) + "." + task + " (No." + taskList.indexOf(task) + " in the whole task list) ");
            }
        } else {
            System.out.printf("\tYou don't have any matching task in your list.%n");
        }
        showLine();
    }

    public static void deleteDoneTask() throws IOException {
        for (Task task: taskList) {
            if (task.getStatusIcon().equals("T")) {
            Storage.deleteTaskFromFile(taskList.indexOf(task));
            taskList.remove(task);
            }
        }
        System.out.println("You have deleted all the done tasks from task list.");
        printNumOfTasksInList();
    }

    public static void printTodoList() {
        showLine();
        int num = 0;
        int index = 0;
        if (taskList.size() > 0) {
            System.out.println("     Here are the todo tasks in your list:");
            for (Task task : taskList) {
                if (task.text().startsWith("T")) {
                    index ++;
                    System.out.println("      " + index + "." + task);
                    if (task.getStatusIcon().equals("F")) {
                        num++;
                    }
                }
            }
            if (num > 1) {
                System.out.printf("\tYou have %d undone tasks in your todo list (%d/%d).%n", num,num,taskList.size());
            } else if (num == 1){
                System.out.printf("\tYou have 1 undone task in your todo list (%d/%d).%n", num,num,taskList.size());
            } else {
                System.out.printf("\tYou don't have any undone task in your todo list (%d/%d).%n", num,num,taskList.size());
            }
            if (index == 0) {
                System.out.printf("\tYou don't have any todo task in your list.%n");
            }
        }  else {
            System.out.printf("\tYou don't have any task in your list.%n");
        }
        showLine();
    }

    public static void printEventList() {
        showLine();
        int num = 0;
        int index = 0;
        if (taskList.size() > 0) {
            System.out.println("     Here are the events in your list:");
            for (Task task : taskList) {
                if (task.text().startsWith("E")) {
                    index ++;
                    System.out.println("      " + index + "." + task);
                    if (task.getStatusIcon().equals("F")) {
                        num++;
                    }
                }
            }
            if (num > 1) {
                System.out.printf("\tYou have %d undone events in your list (%d/%d).%n", num,num,taskList.size());
            } else if (num == 1){
                System.out.printf("\tYou have 1 undone event in your list (%d/%d).%n", num,num,taskList.size());
            } else {
                System.out.printf("\tYou don't have any undone event in your list (%d/%d).%n", num,num,taskList.size());
            }
            if (index == 0) {
                System.out.printf("\tYou don't have any event in your list.%n");
            }
        }  else {
            System.out.printf("\tYou don't have any task in your list.%n");
        }
        showLine();
    }

    public static void printDeadlineList() {
        showLine();
        int num = 0;
        int index = 0;
        if (taskList.size() > 0) {
            System.out.println("     Here are the deadlines in your list:");
            for (Task task : taskList) {
                if (task.text().startsWith("D")) {
                    index ++;
                    System.out.println("      " + index + "." + task);
                    if (task.getStatusIcon().equals("F")) {
                        num++;
                    }
                }
            }
            if (num > 1) {
                System.out.printf("\tYou have %d undone deadlines in your list (%d/%d).%n", num,num,taskList.size());
            } else if (num == 1){
                System.out.printf("\tYou have 1 undone deadline in your list (%d/%d).%n", num,num,taskList.size());
            } else {
                System.out.printf("\tYou don't have any undone deadline in your  list (%d/%d).%n", num,num,taskList.size());
            }
            if (index == 0) {
                System.out.printf("\tYou don't have any deadline in your list.%n");
            }
        }  else {
            System.out.printf("\tYou don't have any task in your list.%n");
        }
        showLine();
    }

    public static void clearDeadlines() throws IOException {
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (Task task : taskList) {
            if (task.text().startsWith("D")) {
                Deadline deadline = (Deadline) task;
                if (deadline.remainingTime()<0) {
                    deadlines.add(deadline);
                }
            }
        }
        for (Deadline deadline : deadlines) {
            Storage.deleteTaskFromFile(taskList.indexOf(deadline));
            taskList.remove(deadline);
        }
        System.out.println("You have removed all the past deadlines!");
        printDeadlineList();
    }
}
