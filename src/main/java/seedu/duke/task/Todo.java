package seedu.duke.task;


public class Todo extends Task {
    /**
     * Constructs ToDo object.
     *
     * @param description Description of ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts ToDo task to string for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts ToDo task to string for storing.
     */
    @Override
    public String text() {
        return "T " + super.text();
    }

}
