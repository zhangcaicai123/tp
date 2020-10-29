package seedu.duke.command;

import com.sun.jdi.IntegerValue;
import seedu.duke.TimeTable;
import seedu.duke.Ui;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyIndexException;
import seedu.duke.exception.ExceptionMessage;
import seedu.duke.exception.OutOfIndexBound;
import seedu.duke.exception.EmptyTimeException;
import seedu.duke.exception.EmptyFindException;
import seedu.duke.project.ProjectManager;
import seedu.duke.storage.Storage;

import seedu.duke.task.Deadline;
import seedu.duke.task.Todo;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ProjectTask;
import seedu.duke.tasklist.TaskList;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Command {

    public static void addModule(String userCommand) {
        TimeTable.addModule(userCommand);
    }

    public static void printHelpMessage() {
        Ui.printHelpMessage();
    }

    public static void printByeMessage() {
        Ui.printByeMessage();
    }

    public static void deleteModule(String userCommand) {
        TimeTable.deleteModule(userCommand);
    }

    public static void printWeeklyTimetable(TaskList taskList) throws ParseException {
        TimeTable.printWeeklyTimetable(taskList);
    }

    public static void printTodayTimetable(TaskList taskList) throws ParseException {
        TimeTable.printTodayTimetable(taskList);
    }

    /**
     * Add todo task to the task list.
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     * @param command  user input command
     */
    public static void addToDo(TaskList taskList, Storage storage, String command) {
        try {
            Todo taskToAdd = new Todo(getTodo(command));
            taskList.addTask(taskToAdd);
            storage.appendToFile(taskToAdd.text() + System.lineSeparator());
        } catch (EmptyDescriptionException e) {
            ExceptionMessage.printEmptyDescriptionExceptionMessage("todo");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Add deadline task to the task list.
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     * @param command  user input command
     */
    public static void addDeadline(TaskList taskList, Storage storage, String command) {
        try {
            String by = getTime(command);
            Deadline taskToAdd = new Deadline(getTask(command));
            taskToAdd.setBy(by);
            taskList.addTask(taskToAdd);
            storage.appendToFile(taskToAdd.text() + System.lineSeparator());
        } catch (EmptyDescriptionException e) {
            ExceptionMessage.printEmptyDescriptionExceptionMessage("deadline");
        } catch (EmptyTimeException e) {
            ExceptionMessage.printEmptyTimeExceptionMessage("deadline");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Add event to the task list.
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     * @param command  user input command
     */
    public static void addEvent(TaskList taskList, Storage storage, String command) {
        try {
            Scanner in = new Scanner(System.in);
            String at = getTime(command);
            System.out.println("Please type the duration of the event in hours:(e.g. 1, 0.5)");
            long duration = Long.parseLong(in.nextLine());
            Event taskToAdd = new Event(getTask(command));
            taskToAdd.setAt(at);
            taskToAdd.setDuration(duration);
            Event conflictEvent = TimeTable.checkEventConflict(taskToAdd, taskList);
            if (conflictEvent == null && !TimeTable.checkEventModuleConflict(taskToAdd)) {
                taskToAdd.setAt(at);
                taskList.addTask(taskToAdd);
                storage.appendToFile(taskToAdd.text() + System.lineSeparator());
            } else if (conflictEvent != null) {
                while (conflictEvent != null) {
                    System.out.println("Oops!There is a time conflict with your previous event.");
                    System.out.println("Which one do you want to keep?");
                    System.out.println("Type 1 for " + conflictEvent.getDescription()
                            + " 2 for " + taskToAdd.getDescription());
                    int index = in.nextInt();
                    System.out.println("Do you want to re-allocate the other one?[Y/N]");
                    String isChange = in.nextLine();
                    if (isChange.equals("Y")) {
                        System.out.println("Please enter a new time for this event:");
                        String time = in.next();
                        if (index == 1) {
                            taskToAdd.setAt(time);
                        } else {
                            conflictEvent.setAt(time);
                        }
                    } else {
                        if (index == 2) {
                            taskList.deleteTask(taskList.getTaskList().indexOf(conflictEvent));
                            storage.deleteTaskFromFile(index);
                            taskList.printNumOfTasksInList();
                        }
                    }
                    conflictEvent = TimeTable.checkEventConflict(taskToAdd, taskList);
                }
            } else {
                //conflict with module
                while (TimeTable.checkEventModuleConflict(taskToAdd)) {
                    System.out.println("Oops!There is a time conflict with your module.");
                    System.out.println("Please enter a new time for your event :");
                    String newAt = in.nextLine();
                    taskToAdd.setAt(newAt);
                }
            }
        } catch (EmptyDescriptionException e) {
            ExceptionMessage.printEmptyDescriptionExceptionMessage("event");
        } catch (EmptyTimeException e) {
            ExceptionMessage.printEmptyTimeExceptionMessage("event");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Execute done command.
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     * @param command  user input command
     * @throws EmptyIndexException if no index is input
     */
    public static void done(TaskList taskList, Storage storage, String command) {
        try {
            int index = getIndex(taskList, command) - 1;
            Task taskToMark = taskList.get(index);
            taskToMark.markAsDone();
            Ui.printMarkMessage(taskToMark);
            storage.updateDoneToFile(index, taskList);
        } catch (OutOfIndexBound e) {
            ExceptionMessage.printOutOfIndexBoundMessage();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (EmptyIndexException e) {
            ExceptionMessage.printEmptyIndexExceptionMessage();
        }
    }

    /**
     * Delete task from task list.
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     * @param command  user input command
     */
    public static void deleteTask(TaskList taskList, Storage storage, String command) {
        try {
            int index = getIndex(taskList, command) - 1;
            taskList.deleteTask(index);
            storage.deleteTaskFromFile(index);
            taskList.printNumOfTasksInList();
        } catch (OutOfIndexBound e) {
            ExceptionMessage.printOutOfIndexBoundMessage();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (EmptyIndexException e) {
            ExceptionMessage.printEmptyIndexExceptionMessage();
        }
    }

    /**
     * Get the description of todo task.
     *
     * @param command user input
     * @return the description of user's input todo task
     * @throws EmptyDescriptionException If description is null
     */
    public static String getTodo(String command) throws EmptyDescriptionException {
        String todoPattern = "^todo (.*)";
        Pattern r = Pattern.compile(todoPattern);
        Matcher m = r.matcher(command);
        if (Pattern.matches("^todo *", command)) {
            throw new EmptyDescriptionException();
        } else {
            m.find();
            return m.group(1).trim();
        }
    }

    /**
     * Get the task description for deadline and event.
     *
     * @param command user command
     * @return the task description of user's input deadline or event
     * @throws EmptyDescriptionException If description is null
     */
    public static String getTask(String command) throws EmptyDescriptionException {
        String pattern = "(event|deadline)( .* )(/at|/by)( .*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        if (Pattern.matches("event|deadline *", command)) {
            throw new EmptyDescriptionException();
        }
        m.find();
        return m.group(2).trim();
    }

    /**
     * Returns the time for event or deadline.
     * Accept dates in yyyy-mm-dd format (e.g., 2019-10-15)
     * and print in a different format such as MMM dd yyyy e.g., (Oct 15 2019)
     *
     * @param command user input command
     * @return time for event or deadline task
     * @throws EmptyTimeException If no String for time information is found
     */
    public static String getTime(String command) throws EmptyTimeException {
        String pattern = "(event|deadline)( .* )(/at|/by)( \\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d)";
        //yyyy-mm-dd HH:MM format
        String time;
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        if (m.find()) {
            time = m.group(4).trim();
        } else {
            throw new EmptyTimeException();
        }
        return time;
    }

    public static void addProjectTask(String command, TaskList taskList, Storage storage) {
        ProjectTask projectTask = ProjectManager.addProjectTask(command);
        try {
            taskList.addTask(projectTask);
            storage.appendToFile(projectTask.text() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void printProjectTaskList(String userCommand) {
        ProjectManager.printProjectTaskList(userCommand);
    }

    public static void printProgress(String userCommand) {
        ProjectManager.printProgress(userCommand);
    }

    public static void printTodayDeadline(TaskList taskList) {
        TimeTable.printTodayDeadline(taskList);
    }

    public static void printWeeklyDeadline(TaskList taskList) {
        TimeTable.printWeeklyDeadline(taskList);
    }

    /**
     * Find task in the task list with keyword.
     *
     * @param command  user input
     * @param taskList the list of all tasks input
     * @throws EmptyFindException if no keyword is input
     */
    public static void find(TaskList taskList, String command) {
        try {
            String keyword = getFind(command);
            taskList.printSearchResult(keyword);
        } catch (EmptyFindException e) {
            ExceptionMessage.printEmptyKeywordMessage();
        }
    }

    /**
     * Get the keyword of finding task command.
     *
     * @param command user input command
     * @return the keyword that user wants to search in the task list
     * @throws EmptyFindException If no keyword is found
     */
    public static String getFind(String command) throws EmptyFindException {
        String pattern = "find *";
        if (Pattern.matches(pattern, command)) {
            throw new EmptyFindException();
        } else {
            return command.substring(command.indexOf(" ") + 1);
        }
    }

    /**
     * Get index of task that need to be deleted or mark as done.
     *
     * @param taskList the list of all tasks input
     * @param command  user input command
     * @return index the index of task that user wants to delete or mark as done
     * @throws OutOfIndexBound     If the index is larger than size of list
     * @throws EmptyIndexException If user does not input any integer
     */
    public static int getIndex(TaskList taskList, String command) throws OutOfIndexBound, EmptyIndexException {
        String pattern = "(done|delete)( )(\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        int index;
        if (Pattern.matches("done|delete *", command)) {
            throw new EmptyIndexException();
        } else {
            m.find();
            index = Integer.parseInt(m.group(3));
        }
        if (taskList.size() < index || index < 1) {
            throw new OutOfIndexBound();
        }
        return index;
    }

    public static String getTaskType(String command) {
        String pattern = "^(todo|deadline|event).*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        m.find();
        return m.group(1);
    }

}
