package seedu.duke;

public class ProjectTask extends Task {

    protected String by;
    protected String material;

    public ProjectTask(String mod, String description, String by, String material) {
        super(mod,description);
        this.material = material;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + " (by:" + by + ") " + "material: " + material;
    }
}