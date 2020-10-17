package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Collections;

public class TimeTable {
    private static final ArrayList<Module> modules = new ArrayList<>();
    static String lineCutOff = "_______________________________________________________";

    public static void addModule(Module module) {
        modules.add(module);
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
        printToday(todayMonth, todayDay, todayYear);
    }

    public static void printToday(int month, int day, int year) {
        System.out.println(lineCutOff);
        String date = String.format("%4d-%2d-%2d", year, month, day);
        System.out.println(date);
        System.out.println(lineCutOff);
        ArrayList<String> todayList = todayList(date);
        for (String event : todayList) {
            System.out.println(event);
        }
        System.out.println(lineCutOff);
    }

    public static void printWeeklyTimetable() {
        int week = 7;
        int day = 0;
        Calendar calendar = Calendar.getInstance();

        while (day < week) {
            printToday(calendar.get(Calendar.MONTH) + 1,
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


}
