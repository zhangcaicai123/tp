package seedu.duke;

public class Task extends Module {
    protected String description;
    protected boolean isDone;

    public Task(String mod, String description) {
        super(mod);
        this.description = description;
        this.isDone = false;
    }

    public void taskDone() {
        this.isDone = true;
    }


}
