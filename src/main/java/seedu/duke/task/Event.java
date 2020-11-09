package seedu.duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime beginTime;
    public LocalDateTime endTime;
    protected String at;
    protected long duration;

    /**
     * Constructs Event object.
     *
     * @param description Description of event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Assigns duration of event.
     *
     * @param duration Duration of event.
     */
    public void setDuration(long duration) {
        this.duration = duration;
        this.endTime = this.beginTime.plusHours(duration);
    }

    /**
     * Returns time of event.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Formats time of event by standard format.
     *
     * @param at Time of event in string.
     */
    public void setAt(String at) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.at = at;
        this.beginTime = LocalDateTime.parse(at, dtf);

    }

    /**
     * Converts event to string for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    /**
     * Converts task to string for storing.
     */
    @Override
    public String text() {
        return "E " + super.text() + " | " + at + " | "
                + duration;
    }

    /**
     * Formats event to fit calendar.
     */
    public String calendarFormat() {
        return beginTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + "-" + endTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + " " + this.description;
    }
}
