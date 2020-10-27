package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.Collections;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;

public class TimeTable {
    private static final ArrayList<Module> modules = new ArrayList<>();
    static String lineCutOff = "_______________________________________________________";

    public static void printModuleWithLab(Module module) {
        System.out.println("Module: " + module.modName);
        System.out.println("Lecture Slot: " + module.lecSlot);
        System.out.println("Tutorial Slot: " + module.tutSlot);
        System.out.println("Lab Slot: " + module.labSlot);
    }

    public static void printModuleWithoutLab(Module module) {
        System.out.println("Module: " + module.modName);
        System.out.println("Lecture Slot: " + module.lecSlot);
        System.out.println("Tutorial Slot: " + module.tutSlot);
    }

    public static void addModule(Module module) {
        modules.add(module);
        int moduleIndex = checkInsertion(module);
        if (moduleIndex != -1) {

            checkModuleKeep(module, moduleIndex);

        } else {

            if (module.labSlot == null) {

                printModuleWithoutLab(module);

            } else {

                printModuleWithLab(module);

            }
        }
    }

    public static int checkInsertion(Module module) {
        int i;
        if (modules.size() > 1) {
            if (module.labSlot == null) {

                for (i = 0; i < modules.size(); i++) {

                    if (module.lecSlot.equals(modules.get(i).lecSlot)
                            || module.lecSlot.equals(modules.get(i).tutSlot)) {

                        return i;

                    } else if (module.tutSlot.equals(modules.get(i).lecSlot)
                            || module.tutSlot.equals(modules.get(i).tutSlot)) {

                        return i;

                    }
                }
            } else {

                for (i = 0; i < modules.size(); i++) {

                    if (module.lecSlot.equals(modules.get(i).lecSlot)
                            || module.lecSlot.equals(modules.get(i).tutSlot)
                            || module.lecSlot.equals(modules.get(i).labSlot)) {

                        return i;

                    } else if (module.tutSlot.equals(modules.get(i).lecSlot)
                            || module.tutSlot.equals(modules.get(i).tutSlot)
                            || module.tutSlot.equals(modules.get(i).labSlot)) {

                        return i;

                    }
                }
            }
        }
        return -1;
    }

    public static void checkModuleKeep(Module module, int moduleIndex) {
        System.out.println(lineCutOff);
        System.out.println("OOPS!!! There is a time conflict.");
        System.out.println(lineCutOff);
        System.out.println("Which module do you want to keep? Please enter the module name.");
        if (modules.get(moduleIndex).labSlot == null) {

            printModuleWithoutLab(modules.get(moduleIndex));

        } else {
            printModuleWithLab(modules.get(moduleIndex));
        }
        if (module.labSlot == null) {
            printModuleWithoutLab(module);
        } else {
            printModuleWithLab(module);
        }
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        if (userCommand.equals(module.modName)) {
            modules.set(moduleIndex, module);
        }

        System.out.println("Got it! I have add " + userCommand + " to timetable.");
    }

    public static void deleteModule(String line) {
        try {
            if (line.equals("delete")) {
                throw new DukeException();
            }
            String details = line.substring(line.indexOf('/') + 1);
            if (modules.size() == 0) {
                throw new DukeException();
            } else {
                for (int i = 0; i < modules.size(); i++) {
                    if (modules.get(i).modName.contains(details)) {
                        modules.remove(i);
                        System.out.println("Noted. I've removed this module");
                        break;
                    }
                    if (i == modules.size() - 1) {
                        throw new DukeException();
                    }
                }
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("OOPS!!! There is no such module.");
            System.out.println(lineCutOff);
        }
    }

    public static void printTodayTimetable() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        int todayMonth = (calendar.get(Calendar.MONTH) + 1);
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
        int todayYear = calendar.get(Calendar.YEAR);
        printDailyTimetable(todayMonth, todayDay, todayYear);
    }

    public static void printDailyTimetable(int month, int day, int year) {
        System.out.println(lineCutOff);
        String date = String.format("%4d-%2d-%2d", year, month, day);
        System.out.println(date);
        System.out.println(lineCutOff);
        ArrayList<String> todayList = todayList(date);
        for (String event : todayList) {
            System.out.println(event);
        }

    }

    public static void printTodayDeadline(TaskList taskList) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        int todayMonth = (calendar.get(Calendar.MONTH) + 1);
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
        int todayYear = calendar.get(Calendar.YEAR);
        printDailyDeadline(todayMonth, todayDay, todayYear, taskList);
    }

    public static void printDailyDeadline(int month, int day, int year, TaskList taskList) {
        String date = String.format("%4d-%2d-%2d", year, month, day);
        System.out.println(lineCutOff);
        System.out.println("Today's Deadline (haven't done):");
        System.out.println(lineCutOff);
        ArrayList<String> deadlineList = todayDeadline(date, taskList);
        for (String ddl : deadlineList) {
            System.out.println(ddl);
        }
    }

    public static void printWeeklyDeadline(TaskList taskList) {
        int week = 7;
        int day = 0;
        Calendar calendar = Calendar.getInstance();

        while (day < week) {
            printDailyDeadline(calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.YEAR), taskList);
            calendar.add(Calendar.DATE, 1);
            day++;
        }
    }

    public static void printWeeklyTimetable() {
        int week = 7;
        int day = 0;
        Calendar calendar = Calendar.getInstance();

        while (day < week) {
            printDailyTimetable(calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.YEAR));
            calendar.add(Calendar.DATE, 1);
            day++;
        }
    }

    public static ArrayList<String> todayList(String date) {
        ArrayList<String> todayList = new ArrayList<>();
        LocalDate todayDate = LocalDate.parse(date);
        int weekDay = todayDate.getDayOfWeek().getValue();
        for (Module module : modules) {
            if (module.lecDay == weekDay) {
                todayList.add(module.lecText());
            } else if (module.tutDay == weekDay) {
                todayList.add(module.tutText());
            } else if (module.labDay == weekDay) {
                todayList.add(module.labText());
            }
        }
        Collections.sort(todayList);
        return todayList;
    }

    public static ArrayList<String> todayDeadline(String date, TaskList taskList) {
        ArrayList<String> todayDeadline = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            //deadline task
            if (task.text().startsWith("D") && task.getStatusIcon().equals("F")) {
                if (date.equals("date")) {
                    todayDeadline.add(task.text());
                }
            }
        }
        return todayDeadline;
    }

}
