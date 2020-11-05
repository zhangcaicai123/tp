package seedu.duke.task;


public class ProjectTask extends Task {

    protected String by;
    protected String material;
    protected String modName;

    /**
     * Constructs ProjectTask class object.
     *
     * @param mod Module of project.
     * @param description Description of project task.
     * @param material Material of project.
     */
    public ProjectTask(String mod, String description, String by, String material) {
        super(description);
        this.material = material;
        this.by = by;
        this.modName = mod;
    }

    /**
     * Returns module name.
     *
     * @return modName string.
     */
    public String getModName() {
        return this.modName;
    }

    /**
     * Returns deadline of project.
     *
     * @return by string.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns material of project.
     *
     * @return material string.
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * Converts task to string for printing.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + " (by:" + by + ") " + "material: " + material;
    }

    /**
     * Converts task to string for storing.
     */
    @Override
    public String text() {
        return "P " + super.text() + " | " + modName + " | " + by + " | " + material;

    }

}