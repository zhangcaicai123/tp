package seedu.duke.task;


public class Event extends Task {

    protected String at;

    public Event(String description ){
        super(description);
    }
    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    @Override
    public String text() {
        return "E " + super.text() + " | " + at;
    }
}
