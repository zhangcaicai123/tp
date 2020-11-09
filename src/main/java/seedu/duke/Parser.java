package seedu.duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Pattern;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.tasklist.TaskList;
import seedu.duke.storage.Storage;

public class Parser {

    static boolean isExit = false;

    /**
     * Runs parsing on user commands.
     *
     * @param userCommand User Command.
     * @param taskList  Task List.
     * @param storage Storage.
     */
    public void parse(String userCommand, TaskList taskList, Storage storage) {

        boolean isPrintHelpCommand = userCommand.toLowerCase().contains("help");
        boolean isExitCommand = userCommand.toLowerCase().equals("exit");
        boolean isAddModCommand = Pattern.matches("^add[\\s]+mod/[\\S\\s]+", userCommand);
        boolean isAddTask = Pattern.matches("^(todo|deadline|event) [\\S\\s]+",
                userCommand);
        boolean isAddProjectTaskCommand =
                Pattern.matches("^mod/[\\S\\s]+ptask/[\\s\\S]+by/[\\s\\S]+", userCommand);
        boolean isDeleteModule = Pattern.matches("^delete[\\s]+mod/[\\S\\s]+", userCommand);
        boolean isDeleteTask = Pattern.matches("^delete[\\s]+task/[\\S\\s]+", userCommand);
        boolean isMarkAsDone = Pattern.matches("^done[\\s]+task/[\\S\\s]+", userCommand);
        boolean isPrintWeeklyTimetable = Pattern.matches("^this[\\s]+week[\\s]+timetable[\\s]*", userCommand);
        boolean isPrintTodayTimeTable = Pattern.matches("^today[\\s]+timetable[\\s]*", userCommand);
        boolean isPrintProjectTaskList = Pattern.matches("^mod/[\\S\\s]+project[\\s]+task[\\s]+list[\\s]*",
                userCommand);
        boolean isPrintProgress = Pattern.matches("^mod/[\\S\\s]+project[\\s]+progress[\\s]*", userCommand);
        boolean isPrintTodayDeadline = Pattern.matches("^today[\\s]+deadline[\\s]*", userCommand);
        boolean isPrintWeeklyDeadline = Pattern.matches("^this[\\s]+week[\\s]+deadline[\\s]*", userCommand);
        boolean isPrintTaskList = Pattern.matches("^task[\\s]+list[\\s]*", userCommand);
        boolean isFind = Pattern.matches("^find [\\s\\S]+", userCommand);
        boolean isCheckModule = Pattern.matches("^check[\\s]+modules[\\s]*",userCommand);

        try {
            if (isPrintHelpCommand) {

                Command.printHelpMessage();

            } else if (isExitCommand) {

                isExit = true;
                Command.printByeMessage();

            } else if (isAddModCommand) {

                Command.addModule(userCommand);

            } else if (isDeleteModule) {

                Command.deleteModule(userCommand);

            } else if (isPrintWeeklyTimetable) {

                Command.printWeeklyTimetable(taskList);

            } else if (isPrintTodayTimeTable) {

                Command.printTodayTimetable(taskList);

            } else if (isDeleteTask) {

                Command.deleteTask(taskList, storage, userCommand);

            } else if (isAddTask) {

                String type = Command.getTaskType(userCommand);

                switch (type) {
                case "todo":

                    Command.addToDo(taskList, storage, userCommand);

                    break;
                case "deadline":

                    Command.addDeadline(taskList, storage, userCommand);

                    break;
                case "event":

                    Command.addEvent(taskList, storage, userCommand);

                    break;
                default:
                    break;
                }

            } else if (isAddProjectTaskCommand) {

                Command.addProjectTask(userCommand, taskList, storage);

            } else if (isPrintProjectTaskList) {

                Command.printProjectTaskList(userCommand);

            } else if (isPrintProgress) {

                Command.printProgress(userCommand);

            } else if (isPrintTodayDeadline) {

                Command.printTodayDeadline(taskList);

            } else if (isPrintWeeklyDeadline) {

                Command.printWeeklyDeadline(taskList);

            } else if (isMarkAsDone) {

                Command.done(taskList, storage, userCommand);

            } else if (isFind) {

                Command.find(taskList, userCommand);

            } else if (isPrintTaskList) {
                TaskList.printList();
            } else if (isCheckModule) {
                Command.printModuleInfo();
            } else {

                throw new DukeException();

            }
        } catch (DukeException | ParseException | IOException | org.json.simple.parser.ParseException e) {

            Ui.dealWithException(userCommand);

        }

    }

}