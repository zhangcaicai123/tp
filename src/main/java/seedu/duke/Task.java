package seedu.duke;

public class Task extends Module {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        super();
        this.description = description;
        this.isDone = false;
    }

    public void taskDone() {
        this.isDone = true;
    }


}
