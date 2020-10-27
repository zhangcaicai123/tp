package seedu.duke;

public class Event extends Task {

    protected String at;

    public Event(String mod, String description, String at) {
        super(mod,description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
