package seedu.duke;

public class Module {
    String modName;
    String lecSlot;
    String tutSlot;
    String labSlot;

    public Module(String mod, String lec, String tut, String lab) {
        this.modName = mod;
        this.lecSlot = lec;
        this.tutSlot = tut;
        this.labSlot = lab;
    }

    public Module(String mod, String lec, String tut) {
        this.modName = mod;
        this.lecSlot = lec;
        this.tutSlot = tut;
    }

    public Module(String mod) {
        this.modName = mod;
    }

}
