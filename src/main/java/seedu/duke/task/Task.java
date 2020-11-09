package seedu.duke.task;

//@@author JinYixuan-Au
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs Task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns status icons of task.
     */
    public String getStatusIcon() {
        return (isDone ? "T" : "F");
    }

    /**
     * Converts task to string for printing.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

    /**
     * Converts task to string for storing.
     */
    public String text() {
        if (this.isDone) {
            return "| 1 | " + description;
        } else {
            return "| 0 | " + description;
        }
    }
}
