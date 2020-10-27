package seedu.duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String mod, String description, String by) {
        super(mod,description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
    @Override
    public String type(){return "deadline";}

    @Override public String by(){return this.by;}
}
