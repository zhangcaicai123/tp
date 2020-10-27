package seedu.duke.task;

public class Task  {
    protected String description;
    protected boolean isDone;

    public Task( String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "T" : "F"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

    public String text() {
        if (this.isDone)
            return "| 1 | " + description;
        else return "| 0 | " + description;
    }
}
