package seedu.duke.list;

import seedu.duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();
    public TaskList(){}
    public ArrayList<Task> getTaskList(){return taskList;}
    /**
     * Add new task to the task list
     *
     * @param taskToAdd the task that needs to be added to the list
     */
    public void addTask(Task taskToAdd) {
        this.taskList.add(taskToAdd);
        printAddMessage(taskToAdd);
        printNumOfTasksInList();
    }

    /**
     * Delete task from the task list with index of task
     *
     * @param taskIndex the index of task which needs to be deleted
     */

    public void deleteTask(int taskIndex) {
        printDeleteMessage(taskIndex);
        taskList.remove(taskIndex);
    }
    /**
     * Get the task with index
     *
     * @param index the index of task
     * @return task
     */

    public Task get(int index) {
        return this.taskList.get(index);
    }


    private void showLine() {
        System.out.println("    ____________________________________________________________");
    }
    public int size() {
        return this.taskList.size();
    }
    /**
     * Print the whole list
     */
    public void printList() {
        showLine();
        if (this.size() > 0) {
            System.out.println("     Here are the tasks in your list:");
            for (Task task : this.taskList) {
                System.out.println("      " + (this.taskList.indexOf(task) + 1) + "." + task);
            }
        } else {
            System.out.println("	 You don't have any task in your list.");
        }
        showLine();
    }
    /**
     * Print the message of adding new task
     */
    private void printAddMessage(Task task) {
        showLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
    }

    /**
     * Print the number of tasks in the list
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
     * Print the message of deleting a task
     *
     * @param index the index of task that needs to be deleted
     */
    public void printDeleteMessage(int index) {
        showLine();
        System.out.println("	 Noted. I've removed this task:");
        System.out.printf("\t   %s%n", this.taskList.get(index));
    }


    /**
     * Find all the tasks which contain the keyword
     *
     * @param keyword keyword to find task
     * @return the list of tasks which contain the keyword
     */

    private ArrayList<Task> find(String keyword) {
        ArrayList<Task> findList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) findList.add(task);
        }
        return findList;
    }

    /**
     * Print the list of searching keyword in task list
     *
     * @param keyword keyword to find task
     */

    public void printSearchResult(String keyword) {
        ArrayList<Task> results = find(keyword);
        showLine();
        if (this.size() > 0) {
            System.out.println("     Here are the matching tasks in your list:");
            for (Task task : results) {
                System.out.println("      " + (results.indexOf(task) + 1) + "." + task);
            }
        } else {
            System.out.println("	 You don't have any matching task in your list.");
        }
        showLine();
    }

}
