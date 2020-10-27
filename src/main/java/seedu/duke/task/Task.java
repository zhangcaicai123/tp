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

    public String getStatusIcon() {
        return (isDone ? "T" : "F"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

    public String type(){return "todo";}

    public String by(){return null;}

    public String at(){return null;}
}
