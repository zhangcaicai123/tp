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

    public void parse(String userCommand, TaskList taskList, Storage storage) {

        boolean isPrintHelpCommand = userCommand.toLowerCase().contains("help");
        boolean isExitCommand = userCommand.toLowerCase().equals("exit");
        boolean isAddModCommand = Pattern.matches("^add[\\s]+mod/[\\S\\s]+", userCommand);
        boolean isAddTask = Pattern.matches("^(todo|deadline|event).*",
                userCommand);
        boolean isAddProjectTaskCommand =
                Pattern.matches("^mod/[\\S\\s]+ptask/[\\s\\S]+by/[\\s\\S]+", userCommand);
        boolean isDeleteModule = userCommand.contains("delete mod/");
        boolean isDeleteTask = userCommand.contains("delete task/");
        boolean isPrintWeeklyTimetable = userCommand.equals("this week timetable");
        boolean isPrintTodayTimeTable = userCommand.equals("today timetable");
        boolean isPrintProjectTaskList = userCommand.contains("project task list");
        boolean isPrintProgress = userCommand.toLowerCase().contains("progress");
        boolean isPrintTodayDeadline = userCommand.equals("today deadline");
        boolean isPrintWeeklyDeadline = userCommand.equals("this week deadline");

        boolean isMarkAsDone = Pattern.matches("^done.*", userCommand);
        boolean isFind = Pattern.matches("^find.*", userCommand);

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

            } else {

                throw new DukeException();

            }
        } catch (DukeException | ParseException | IOException | org.json.simple.parser.ParseException e) {

            Ui.dealWithException(userCommand);

        }

    }

}