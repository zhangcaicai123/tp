package seedu.duke;

import java.util.ArrayList;

public class TimeTable {
    private static ArrayList<Module> modules;
    static String lineCutOff = "_______________________";

    public TimeTable() {
        modules = new ArrayList<>();
    }

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
}
