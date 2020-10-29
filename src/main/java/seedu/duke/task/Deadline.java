package seedu.duke.task;


public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String text() {
        return "D " + super.text() + " | " + by;
    }
}
