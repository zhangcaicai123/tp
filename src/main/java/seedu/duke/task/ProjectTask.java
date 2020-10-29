package seedu.duke.task;


public class ProjectTask extends Task {

    protected String by;
    protected String material;
    protected String modName;

    public ProjectTask(String mod, String description, String by, String material) {
        super(description);
        this.material = material;
        this.by = by;
        this.modName = mod;
    }

    public String getModName() {
        return this.modName;
    }

    public String getBy() {
        return this.by;
    }

    public String getMaterial() {
        return this.material;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + " (by:" + by + ") " + "material: " + material;
    }

    @Override
    public String text() {
        return "P " + super.text() + " | " + modName + " | " + by + " | " + material;

    }

}