package seedu.duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime beginTime;
    public LocalDateTime endTime;
    protected String at;
    protected long duration;

    public Event(String description) {
        super(description);
    }

    public void setDuration(long duration) {
        this.duration = duration;
        this.endTime = this.beginTime.plusHours(duration);
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.at = at;
        this.beginTime = LocalDateTime.parse(at, dtf);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    @Override
    public String text() {
        return "E " + super.text() + " | " + at + "-"
                + endTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String calendarFormat() {
        return beginTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + "-" + endTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + " " + this.description;
    }
}
