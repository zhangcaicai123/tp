package seedu.duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.exception.*;
import seedu.duke.taskList.TaskList;
import seedu.duke.project.ProjectManager;
import seedu.duke.storage.Storage;
import seedu.duke.task.*;

public class Parser {

    static boolean isExit = false;

    public static void parse(String userCommand, TaskList taskList, Storage storage) {

        boolean isPrintHelpCommand = userCommand.toLowerCase().contains("help");
        boolean isAddModCommand =
                Pattern.matches("^module[\\s]+mod/[\\S\\s]+lec/[\\s\\S]+tut/[\\s\\S]+",
                        userCommand);
        boolean isAddProjectTaskCommand = Pattern.matches("^mod/[\\S\\s]+ptask/[\\s\\S]+by/[\\s\\S]+", userCommand);
        boolean isExitCommand = userCommand.equals("exit");
        boolean isDeleteModule = userCommand.contains("delete m/");
        boolean isDeleteTask = userCommand.contains("delete t/");
        boolean isPrintWeeklyTimetable = userCommand.equals("this week timetable");
        boolean isPrintTodayTimeTable = userCommand.equals("today timetable");
        boolean isPrintProjectTaskList = userCommand.contains("project task list");
        boolean isPrintProgress = userCommand.contains("progress");
        boolean isPrintTodayDeadline = userCommand.equals("today deadline");
        boolean isPrintWeeklyDeadline = userCommand.equals("this week deadline");
        boolean isAddTask = Pattern.matches("^(todo|deadline|event).*",
                userCommand);
        boolean isMarkAsDone = Pattern.matches("^done.*", userCommand);
        boolean isFind = Pattern.matches("^find.*", userCommand);
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

                deleteTask(taskList, storage, userCommand);

            } else if (isAddTask) {
                String type = getTaskType(userCommand);
                if (type.equals("todo")) {
                    addToDo(taskList, storage, userCommand);
                } else if (type.equals("deadline")) {
                    addDeadline(taskList, storage, userCommand);
                } else if (type.equals("event")) {
                    addEvent(taskList, storage, userCommand);
                }
            } else if (isAddProjectTaskCommand) {
                addProjectTask(userCommand, taskList, storage);
            } else if (isPrintProjectTaskList) {

                ProjectManager.printProjectTaskList(userCommand);

            } else if (isPrintProgress) {

                ProjectManager.printProgress(userCommand);

            } else if (isPrintTodayDeadline) {
                TimeTable.printTodayDeadline(taskList);
            } else if (isPrintWeeklyDeadline) {
                TimeTable.printWeeklyDeadline(taskList);
            } else if (isMarkAsDone) {
                done(taskList, storage, userCommand);
            } else if (isFind) {
                find(taskList, userCommand);
            } else {

                throw new DukeException();

            }
        } catch (DukeException e) {

            Ui.dealWithException(userCommand);

        }


    }

    public static void addModule(String command) {

        String modName;
        String lecSlot;
        String tutSlot;
        String labSlot;


        modName = command.substring(command.indexOf("mod/"), command.indexOf("lec/"));
        modName = modName.substring(4).trim();

        lecSlot = command.substring(command.indexOf("lec/"), command.indexOf("tut/"));
        lecSlot = lecSlot.substring(4).trim();

        boolean isLabExit = command.contains("lab/");

        if (isLabExit) {

            tutSlot = command.substring(command.indexOf("tut/"), command.indexOf("lab/"));
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

    public static void deleteModule(String command) {
        TimeTable.deleteModule(command);
    }

    /**
     * Find task in the task list with keyword
     *
     * @param taskList the list of all tasks input
     */
    private static void find(TaskList taskList, String command) {
        try {
            String keyword = Parser.getFind(command);
            taskList.printSearchResult(keyword);
        } catch (EmptyFindException e) {
            exception.printEmptyKeywordMessage();
        }
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

    public static String getTaskType(String command) {
        String pattern = "^(todo|deadline|event).*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        m.find();
        return m.group(1);

    }

    /**
     * @return the description of user's input todo task
     * @throws EmptyDescriptionException If description is null
     */
    public static String getTodo(String command) throws EmptyDescriptionException {
        String todo_pattern = "todo (.*)";
        Pattern r = Pattern.compile(todo_pattern);
        Matcher m = r.matcher(command);
        if (Pattern.matches("todo *", command)) {
            throw new EmptyDescriptionException();
        } else {
            m.find();
            return m.group(1).trim();
        }
    }

    /**
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
     * Add todo task to the task list
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     */
    private static void addToDo(TaskList taskList, Storage storage, String command) {
        try {
            Todo taskToAdd = new Todo(getTodo(command));
            taskList.addTask(taskToAdd);
            storage.appendToFile(taskToAdd.text() + System.lineSeparator());
        } catch (EmptyDescriptionException e) {
            exception.printEmptyDescriptionExceptionMessage("todo");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Add deadline task to the task list
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     */
    private static void addDeadline(TaskList taskList, Storage storage, String command) {
        try {
            String by = Parser.getTime(command);
            Deadline taskToAdd = new Deadline(Parser.getTask(command));
            taskToAdd.setBy(by);
            taskList.addTask(taskToAdd);
            storage.appendToFile(taskToAdd.text() + System.lineSeparator());
        } catch (EmptyDescriptionException e) {
            exception.printEmptyDescriptionExceptionMessage("deadline");
        } catch (EmptyTimeException e) {
            exception.printEmptyTimeExceptionMessage("deadline");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Add event to the task list
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     */
    private static void addEvent(TaskList taskList, Storage storage, String command) {
        try {
            String at = Parser.getTime(command);
            Event taskToAdd = new Event(Parser.getTask(command));
            taskToAdd.setAt(at);
            taskList.addTask(taskToAdd);
            storage.appendToFile(taskToAdd.text() + System.lineSeparator());
        } catch (EmptyDescriptionException e) {
            exception.printEmptyDescriptionExceptionMessage("event");
        } catch (EmptyTimeException e) {
            exception.printEmptyTimeExceptionMessage("event");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    /**
     * Returns the time for event or deadline.
     * Accept dates in yyyy-mm-dd format (e.g., 2019-10-15)
     * and print in a different format such as MMM dd yyyy e.g., (Oct 15 2019)
     *
     * @return time for event or deadline task
     * @throws EmptyTimeException If no String for time information is found
     */
    public static String getTime(String command) throws EmptyTimeException {
        String pattern = "(event|deadline)( .* )(/at|/by)(.*)";
        String time;
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        if (m.find()) {
            time = m.group(4).trim();
        } else throw new EmptyTimeException();
        String time_pattern = "\\d\\d\\d\\d-\\d\\d-\\d\\d";//yyyy-mm-dd format
        boolean isDate = Pattern.matches(time_pattern, time);
        if (isDate) {
            LocalDate Date = LocalDate.parse(time);
            return Date.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        } else return time;
    }

    /**
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
     * @param taskList the list of all tasks input
     * @return index the index of task that user wants to delete or mark as done
     * @throws OutOfIndexBound     If the index > size of list
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


    /**
     * Execute done command
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     */
    private static void done(TaskList taskList, Storage storage, String command) {
        try {
            int index = Parser.getIndex(taskList, command) - 1;
            Task taskToMark = taskList.get(index);
            taskToMark.markAsDone();
            Ui.printMarkMessage(taskToMark);
            storage.updateDoneToFile(index, taskList);
        } catch (OutOfIndexBound e) {
            exception.printOutOfIndexBoundMessage();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (EmptyIndexException e) {
            exception.printEmptyIndexExceptionMessage();
        }
    }

    /**
     * Delete task from task list
     *
     * @param taskList the list of all tasks input
     * @param storage  the file stores all tasks in the list
     */
    private static void deleteTask(TaskList taskList, Storage storage, String command) {
        try {
            int index = Parser.getIndex(taskList, command) - 1;
            taskList.deleteTask(index);
            storage.deleteTaskFromFile(index);
            taskList.printNumOfTasksInList();
        } catch (OutOfIndexBound e) {
            exception.printOutOfIndexBoundMessage();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (EmptyIndexException e) {
            exception.printEmptyIndexExceptionMessage();
        }
    }

}
