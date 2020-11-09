package seedu.duke;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Locale;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;

public class TimeTable {
    public static ArrayList<Module> modules = new ArrayList<>();
    public static String lineCutOff = "_______________________________________________________";

    public TimeTable() {
    }

    /**
     * Constructs Timetable class object.
     *
     * @param loadedList Loaded timetable arraylist.
     */
    public TimeTable(ArrayList<Module> loadedList) {
        this.modules = loadedList;
    }

    /**
     * Prints module's code, lecture, tutorial and lab (if applicable) details.
     *
     * @param module Module selected.
     */
    public static void printModule(Module module) {
        System.out.println("Module: " + module.moduleCode);
        System.out.println("Lecture Slot: " + module.lecSlot);
        System.out.println("Tutorial Slot: " + module.tutSlot);
        System.out.println("Lab Slot: " + module.labSlot);
        System.out.println(lineCutOff);
    }


    public static boolean isModuleAdded(String moduleCode) {
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).moduleCode.equals(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    public static void printModuleDetails(String moduleCode) {
        System.out.println(lineCutOff);
        System.out.println("Module code: " + ModDataBase.modules.get(moduleCode).moduleCode);
        System.out.println("Title: " + ModDataBase.modules.get(moduleCode).title);
        System.out.println("Description: " + ModDataBase.modules.get(moduleCode).description);
        System.out.println(lineCutOff);
    }

    public static void printAddTaskInformation() {
        Scanner in = new Scanner(System.in);
        System.out.println("Is there any task you want to add for this module? Y/N");
        String isHaveTask = in.nextLine();
        while (!isHaveTask.equalsIgnoreCase("Y")
                && !isHaveTask.equalsIgnoreCase("N")) {
            System.out.println("Is there any task you want to add for this module? Y/N");
            isHaveTask = in.nextLine();
        }
        if (isHaveTask.equalsIgnoreCase("Y")) {
            System.out.println("Please enter T for todo, D for deadline, E for event, "
                    + "P for project subtask.");
            String taskType = in.nextLine();
            if (taskType.equalsIgnoreCase("T")) {
                System.out.println("Add a task to do: todo <DESCRIPTION>");
            } else if (taskType.equalsIgnoreCase("D")) {
                System.out.println("Add a deadline: deadline <DESCRIPTION> /by <YYYY-MM-DD HH:mm>");
            } else if (taskType.equalsIgnoreCase("E")) {
                System.out.println("Add an event: event <DESCRIPTION> /at <YYYY-MM-DD HH:mm>");
            } else if (taskType.equalsIgnoreCase("P")) {
                System.out.println("Add a project subtask: mod/<MODULE_CODE> "
                        + "ptask/<DESCRIPTION> by/<DEADLINE>");
            }
        } else {
            System.out.println(lineCutOff);
        }
    }

    public static void addModuleToStorage(Module module) throws IOException {
        String moduleToAdd;
        moduleToAdd = module.toString();
        Storage.appendToFileModule(moduleToAdd + System.lineSeparator());
    }

    /**
     * Creates and adds module to data file.
     *
     * @param command Command of user input.
     * @throws IOException If there is no matching module.
     */
    public static void addModule(String command) throws IOException {
        String moduleCode = command.substring(command.indexOf("/") + 1).toUpperCase();
        boolean isModuleAdded = isModuleAdded(moduleCode);
        if (!isModuleAdded) {
            boolean isModuleExit = ModDataBase.modules.containsKey(moduleCode);
            Scanner in = new Scanner(System.in);
            Module module = new Module();
            try {
                if (isModuleExit) {
                    printModuleDetails(moduleCode);
                    module = ModDataBase.modules.get(moduleCode);
                    System.out.println("Please enter your time slots for lectures, tutorials, "
                            + "and labs (optional) for this module.");
                    System.out.println("The format of the time slots is: Day HH:mm-HH:mm (Eg. Thur 12:00-13:00)");
                    System.out.print("Lecture slot: ");
                    module.lecSlot = checkSlotsFormat(in.nextLine());
                    System.out.println("Does this module have another lecture slot?"
                            + "([Y] for yes,type any other character for no)");
                    String isHaveAnotherLec = in.nextLine();
                    if (isHaveAnotherLec.equalsIgnoreCase("Y")) {
                        System.out.print("Another lecture slot: ");
                        module.lecSlot2 = checkSlotsFormat(in.nextLine());
                    }
                    System.out.print("Tutorial slot: ");
                    module.tutSlot = checkSlotsFormat(in.nextLine());
                    System.out.println("Does this module have lab? ([Y] for yes,type any other character for no)");
                    String isHaveLab = in.nextLine();
                    if (isHaveLab.equalsIgnoreCase("Y")) {
                        System.out.print("Lab slot: ");
                        module.labSlot = checkSlotsFormat(in.nextLine());
                    }
                    module.setSlot();
                    if (module.isSetSlotSuccess) {
                        int moduleIndex = checkInsertion(module);
                        if (moduleIndex != -1) {
                            checkModule(module, moduleIndex);
                        } else {
                            modules.add(module);
                            addModuleToStorage(module);
                            System.out.println("Noted! I have added this module.");
                            printAddTaskInformation();
                        }
                    }
                } else {
                    throw new NoSuchElementException();
                }
            } catch (NoSuchElementException e) {
                System.out.println(lineCutOff);
                System.out.println("There is no such module.");
                System.out.println(lineCutOff);
            }
        } else {
            System.out.println("You have already add this module.\n"
                    + "Please enter another module code.");
        }
    }

    /**
     * Returns the index of the module added.
     * Returns -1 if there is a conflict in the module with another module in timetable.
     *
     * @param module Newly added module.
     * @return i index of module, unless there is a conflict, then -1.
     */
    public static int checkInsertion(Module module) {
        int i;
        if (modules.size() > 1) {

            for (i = 0; i < modules.size(); i++) {

                if (checkTimeDayConflict(module.lecBegin, module.lecEnd,
                        modules.get(i).lecBegin, modules.get(i).lecEnd,
                        module.lecDay, modules.get(i).lecDay)) {

                    return i;

                } else if (checkTimeDayConflict(module.lecBegin, module.lecEnd,
                        modules.get(i).tutBegin, modules.get(i).tutEnd,
                        module.lecDay, modules.get(i).tutDay)) {

                    return i;

                } else if (checkTimeDayConflict(module.tutBegin, module.tutEnd,
                        modules.get(i).lecBegin, modules.get(i).lecEnd,
                        module.tutDay, modules.get(i).lecDay)) {
                    return i;
                } else if (checkTimeDayConflict(module.tutBegin, module.tutEnd,
                        modules.get(i).tutBegin, modules.get(i).tutEnd,
                        module.tutDay, modules.get(i).tutDay)) {
                    return i;
                }
                if (module.labSlot != null) {
                    if (checkTimeDayConflict(module.labBegin, module.labEnd,
                            modules.get(i).lecBegin, modules.get(i).lecEnd, module.labDay, modules.get(i).lecDay)) {

                        return i;

                    } else if (checkTimeDayConflict(module.labBegin, module.labEnd,
                            modules.get(i).tutBegin, modules.get(i).tutEnd, module.labDay, modules.get(i).tutDay)) {

                        return i;

                    }
                }
                if (modules.get(i).labSlot != null) {
                    if (checkTimeDayConflict(module.lecBegin, module.lecEnd,
                            modules.get(i).labBegin, modules.get(i).labEnd, module.lecDay, modules.get(i).labDay)) {

                        return i;

                    } else if (checkTimeDayConflict(module.tutBegin, module.tutEnd,
                            modules.get(i).labBegin, modules.get(i).labEnd, module.tutDay, modules.get(i).labDay)) {

                        return i;

                    }
                }
                if (modules.get(i).labSlot != null && module.labSlot != null) {
                    if (checkTimeDayConflict(module.labBegin, module.labEnd,
                            modules.get(i).labBegin, modules.get(i).labEnd, module.labDay, modules.get(i).labDay)) {
                        return i;
                    }
                }
                if (modules.get(i).lecSlot2 != null) {
                    if (checkTimeDayConflict(module.lecBegin, module.lecEnd,
                            modules.get(i).lecBegin2, modules.get(i).lecEnd2, module.lecDay, modules.get(i).lecDay2)) {

                        return i;

                    } else if (checkTimeDayConflict(module.tutBegin, module.tutEnd,
                            modules.get(i).lecBegin2, modules.get(i).lecEnd2, module.tutDay, modules.get(i).lecDay2)) {

                        return i;

                    } else if (module.labSlot != null) {
                        if (checkTimeDayConflict(module.labBegin, module.labEnd,
                                modules.get(i).lecBegin2, modules.get(i).lecEnd2,
                                module.labDay, modules.get(i).lecDay2)) {
                            return i;
                        }
                    } else if (module.lecSlot2 != null) {
                        if (checkTimeDayConflict(module.lecBegin2, module.lecEnd2,
                                modules.get(i).lecBegin2, modules.get(i).lecEnd2,
                                module.lecDay2, modules.get(i).lecDay2)) {
                            return i;
                        }
                    }
                } else if (module.lecSlot2 != null) {
                    if (checkTimeDayConflict(module.lecBegin2, module.lecEnd2,
                            modules.get(i).lecBegin, modules.get(i).lecEnd, module.lecDay2, modules.get(i).lecDay)) {

                        return i;

                    } else if (checkTimeDayConflict(module.lecBegin2, module.lecEnd2,
                            modules.get(i).tutBegin, modules.get(i).tutEnd, module.lecDay2, modules.get(i).tutDay)) {

                        return i;

                    } else if (module.labSlot != null) {
                        if (checkTimeDayConflict(module.lecBegin2, module.lecEnd2,
                                modules.get(i).labBegin, modules.get(i).labEnd,
                                module.lecDay2, modules.get(i).labDay)) {
                            return i;
                        }
                    }
                }
            }

        }
        return -1;
    }

    /**
     * Adds the chosen module should there be a conflict.
     *
     * @param module      Module.
     * @param moduleIndex Index of module.
     * @throws IOException If invalid input.
     */
    public static void checkModule(Module module, int moduleIndex) {
        System.out.println("OOPS!!! There is a time conflict.");
        System.out.println(lineCutOff);
        System.out.println("Which module do you want to keep? Please enter the module name.");
        printModule(modules.get(moduleIndex));
        printModule(module);
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        if (userCommand.equals(module.moduleCode)) {
            modules.set(moduleIndex, module);
            Storage.updateModuleToFile(modules);
        }

        System.out.println("Got it! I have add " + userCommand + " to timetable.");
    }

    /**
     * Deletes a module.
     * Prints statement of error if there is invalid module input.
     *
     * @param command User input.
     */
    public static void deleteModule(String command) {
        try {
            String modCode = command.substring(command.indexOf('/') + 1).toUpperCase();
            if (modules.size() == 0) {
                throw new NoSuchElementException();
            } else {
                for (int i = 0; i < modules.size(); i++) {
                    if (modules.get(i).moduleCode.contains(modCode)) {
                        modules.remove(i);
                        Storage.updateModuleToFile(modules);
                        System.out.println("Noted. I've removed this module");
                        break;
                    }
                    if (i == modules.size() - 1) {
                        throw new NoSuchElementException();
                    }
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(lineCutOff);
            System.out.println("OOPS!!! There is no such module.");
            System.out.println(lineCutOff);
        }
    }

    /**
     * Fetches timetable.
     *
     * @param taskList Task list.
     * @throws ParseException If unable to parse taskList content.
     */
    public static void printTodayTimetable(TaskList taskList) throws ParseException {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        int todayMonth = (calendar.get(Calendar.MONTH) + 1);
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
        int todayYear = calendar.get(Calendar.YEAR);
        printDailyTimetable(todayMonth, todayDay, todayYear, taskList);
    }

    /**
     * Prints daily timetable.
     *
     * @param month Month.
     * @param day   Day.
     * @param year  Year
     * @throws ParseException If unable to parse input.
     */
    public static void printDailyTimetable(int month, int day, int year, TaskList taskList) throws ParseException {
        System.out.println(lineCutOff);
        String date = String.format("%4d-%02d-%02d", year, month, day);
        System.out.println(date);
        System.out.println(lineCutOff);
        ArrayList<String> todayList = todayList(date, taskList);
        for (String event : todayList) {
            System.out.println(event);
        }

    }

    /**
     * Fetches today's deadlines.
     *
     * @param taskList Task list.
     */
    public static void printTodayDeadline(TaskList taskList) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        int todayMonth = (calendar.get(Calendar.MONTH) + 1);
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
        int todayYear = calendar.get(Calendar.YEAR);
        printDailyDeadline(todayMonth, todayDay, todayYear, taskList);
    }

    /**
     * Prints today's deadlines.
     *
     * @param day   Day.
     * @param month Month.
     * @param year  Year.
     */
    public static void printDailyDeadline(int month, int day, int year, TaskList taskList) {
        String date = String.format("%4d-%02d-%02d", year, month, day);
        System.out.println(lineCutOff);
        System.out.println(date + " Deadline (haven't done):");
        System.out.println(lineCutOff);
        ArrayList<String> deadlineList = todayDeadline(date, taskList);
        for (String ddl : deadlineList) {
            System.out.println(ddl);
        }
    }

    /**
     * Fetches this week's deadlines.
     *
     * @param taskList Task list of deadlines.
     */
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

    /**
     * Prints this week's timetable.
     *
     * @param taskList Task list.
     * @throws ParseException If unable to parse input.
     */
    public static void printWeeklyTimetable(TaskList taskList) throws ParseException {
        int week = 7;
        int day = 0;
        Calendar calendar = Calendar.getInstance();

        while (day < week) {
            printDailyTimetable(calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.YEAR), taskList);
            calendar.add(Calendar.DATE, 1);
            day++;
        }
    }

    /**
     * Returns arraylist of today's events.
     *
     * @param date     Date of event.
     * @param taskList Task list.
     * @return todayList Today's events.
     * @throws ParseException If unable to parse input.
     */
    public static ArrayList<String> todayList(String date, TaskList taskList) throws ParseException {
        ArrayList<String> todayList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate todayDate = LocalDate.parse(date, dtf);
        int weekDay = todayDate.getDayOfWeek().getValue();
        for (Module module : modules) {
            if (module.lecDay == weekDay) {
                todayList.add(module.lecText());
            } else if (module.tutDay == weekDay) {
                todayList.add(module.tutText());
            } else if (module.labDay == weekDay) {
                todayList.add(module.labText());
            } else if (module.lecDay2 == weekDay) {
                todayList.add(module.lecText2());
            }
        }
        for (Task task : taskList.getTaskList()) {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStatusIcon().equals("F") && event.beginTime.toLocalDate().equals(todayDate)) {
                    todayList.add(event.calendarFormat());
                }
            }
        }
        Collections.sort(todayList);
        return todayList;
    }

    /**
     * Returns arraylist of today's deadlines.
     *
     * @param date     Date of deadlines.
     * @param taskList Task list.
     * @return todayDeadline Today's deadlines.
     */
    public static ArrayList<String> todayDeadline(String date, TaskList taskList) {
        ArrayList<String> todayDeadline = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            //deadline task
            if (task.text().startsWith("D")) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().substring(0,deadline.getBy().indexOf(" ")).trim().equals(date)) {
                    deadlines.add(deadline);
                }
            }
        }
        Collections.sort(deadlines, (deadline1, deadline2) -> {

            if (deadline1.remainingTime() < deadline2.remainingTime()) {
                return 1;
            } else if (deadline1.remainingTime() == deadline1.remainingTime()) {
                return 0;
            } else {
                return -1;
            }
        });
        for (Deadline deadline: deadlines) {
            todayDeadline.add(deadline.toString());
        }

        return todayDeadline;
    }

    /**
     * Returns existing event if there is an conflict.
     * If there is no conflict, null is returned.
     *
     * @param insertEvent New event.
     * @param taskList    Task list of current events.
     * @return existEvent current event, if existing.
     */
    public static Event checkEventConflict(Event insertEvent, TaskList taskList) {
        for (Task task : taskList.getTaskList()) {
            if (task instanceof Event) {
                Event existEvent = (Event) task;
                if (existEvent.getStatusIcon().equals("F")) {
                    if (checkDateTimeConflict(existEvent.beginTime, existEvent.endTime,
                            insertEvent.beginTime, insertEvent.endTime)) {
                        return existEvent;
                    }

                }
            }
        }
        return null;
    }

    /**
     * Returns boolean if there is datetime conflict.
     *
     * @param beginA Start of event A.
     * @param beginB Start of event B.
     * @param endA   End of event A.
     * @param endB   End of event B.
     * @return boolean.
     */
    public static boolean checkDateTimeConflict(LocalDateTime beginA, LocalDateTime endA,
                                                LocalDateTime beginB, LocalDateTime endB) {
        if (beginA.isAfter(beginB) && beginA.isBefore(endB)) {
            return true;
        } else if (beginB.isAfter(beginA) && beginB.isBefore(endA)) {
            return true;
        } else if (beginA.isEqual(beginB)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns boolean if there is time conflict.
     *
     * @param beginA Start of todo A.
     * @param beginB Start of todo B.
     * @param endA   End of todo A.
     * @param endB   End of todo B.
     * @return boolean.
     */
    public static boolean checkTimeConflict(LocalTime beginA, LocalTime endA, LocalTime beginB, LocalTime endB) {
        if (beginA.isAfter(beginB) && beginA.isBefore(endB)) {
            return true;
        } else if (beginB.isAfter(beginA) && beginB.isBefore(endA)) {
            return true;
        } else if (beginA.equals(beginB)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns boolean if there is module with event conflict.
     *
     * @param insertEvent newEvent.
     * @return boolean.
     */
    public static boolean checkEventModuleConflict(Event insertEvent) {
        int weekday = insertEvent.beginTime.getDayOfWeek().getValue();
        for (Module module : modules) {
            if (weekday == module.lecDay) {
                if (checkTimeConflict(insertEvent.beginTime.toLocalTime(),
                        insertEvent.endTime.toLocalTime(), module.lecBegin, module.lecEnd)) {
                    return true;
                }
            } else if (weekday == module.tutDay) {
                if (checkTimeConflict(insertEvent.beginTime.toLocalTime(),
                        insertEvent.endTime.toLocalTime(), module.tutBegin, module.tutEnd)) {
                    return true;
                }
            } else if (module.labSlot != null) {
                if (weekday == module.labDay) {
                    if (checkTimeConflict(insertEvent.beginTime.toLocalTime(),
                            insertEvent.endTime.toLocalTime(), module.labBegin, module.labEnd)) {
                        return true;
                    }
                }
            } else if (module.lecSlot2 != null) {
                if (weekday == module.lecDay2) {
                    if (checkTimeConflict(insertEvent.beginTime.toLocalTime(),
                            insertEvent.endTime.toLocalTime(), module.lecBegin2, module.lecEnd2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns boolean if there is time-day conflict.
     *
     * @param beginA Start of event A.
     * @param beginB Start of event B.
     * @param endA   End of event A.
     * @param endB   End of event B.
     * @return boolean.
     */
    public static boolean checkTimeDayConflict(LocalTime beginA, LocalTime endA,
                                               LocalTime beginB, LocalTime endB,
                                               int weekDayA, int weekDayB) {
        if (weekDayA == weekDayB) {
            return checkTimeConflict(beginA, endA, beginB, endB);
        } else {
            return false;
        }
    }

    /**
     * Get correct formats slots.
     * @param slots input slots
     * @return slots with correct format
     */
    public static String checkSlotsFormat(String slots) {
        String pattern = "(^Mon|Monday|Tue|Wuesday|Wed|Wednesday|Thursday|Thur|Thu|Fri|Friday)"
                + "( \\d\\d:\\d\\d)(-)(\\d\\d:\\d\\d)";
        //DDD HH:MM-HH:MM format
        Pattern r = Pattern.compile(pattern);
        while (true) {
            Matcher m = r.matcher(slots);
            if (m.find()) {
                LocalTime endTime = LocalTime.parse(m.group(4));
                LocalTime beginTime = LocalTime.parse(m.group(2).trim());
                if (beginTime.isBefore(endTime)) {
                    return slots;
                } else {
                    System.out.println("The begin time cannot be after the end time.");
                    System.out.println("Please enter correct slots with DDD HH:mm-HH:mm format");
                    Scanner in = new Scanner(System.in);
                    slots = in.nextLine();
                }
            } else {
                System.out.println("Please enter the slot again with DDD HH:mm-HH:mm format: (e.g.Fri 11:00-12:00)");
                Scanner in = new Scanner(System.in);
                slots = in.nextLine();
            }
        }
    }

}