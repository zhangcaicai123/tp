package seedu.duke.task;


public class Deadline extends Task {

    protected String by;

    /**
     * Constructs deadline object.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Assigns deadline of object.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline of task.
     * @return deadline date time
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Converts deadline to string for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /**
     * Converts deadline to string for storing.
     */
    @Override
    public String text() {
        return "D " + super.text() + " | " + by;
    }
}
