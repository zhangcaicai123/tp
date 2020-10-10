package seedu.duke;

import java.util.regex.Pattern;

public class Parser {

    boolean isExit = false;

    void parse(String userCommand) {

        boolean isAddModCommand =
                Pattern.matches("^module[\\s]+mod/[\\S\\s]+lec/[\\s\\S]+tut/[\\s\\S]+", userCommand);

        boolean isExitCommand = userCommand.equals("exit");


        if(isAddModCommand){
            addModule(userCommand);
        }else if(isExitCommand){
            isExit = true;
        }


    }

    public void addModule(String command) {

        String modName;
        String lecSlot;
        String tutSlot;
        String labSlot;

        boolean isLabExit = command.contains("lab/");

        modName = command.substring(command.indexOf("mod/"),command.indexOf("lec/"));
        modName = modName.substring(4).trim();

        lecSlot = command.substring(command.indexOf("lec/"),command.indexOf("tut/"));
        lecSlot = lecSlot.substring(4).trim();


        if(isLabExit){
            tutSlot = command.substring(command.indexOf("tut/"),command.indexOf("lab/"));
            tutSlot = tutSlot.substring(4).trim();

            labSlot = command.substring(command.indexOf("lab/")).substring(4).trim();

            Module mod = new Module(modName,lecSlot,tutSlot,labSlot);

            System.out.println("Module: "+mod.modName);
            System.out.println("Lecture Slot: "+mod.lecSlot);
            System.out.println("Tutorial Slot: "+mod.tutSlot);
            System.out.println("Lab Slot: "+mod.labSlot);

        }else {
            tutSlot = command.substring(command.indexOf("tut/")).substring(4).trim();

            Module mod = new Module(modName,lecSlot,tutSlot);

            System.out.println("Module: "+mod.modName);
            System.out.println("Lecture Slot: "+mod.lecSlot);
            System.out.println("Tutorial Slot: "+mod.tutSlot);
        }

    }


}
