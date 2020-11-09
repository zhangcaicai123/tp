package seedu.duke.task;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    /**
     * Constructs deadline object.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Assigns deadline of object.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline of task.
     * @return deadline date time
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Converts deadline to string for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")"
                + " [Remaining time: " + calculateDeadline() + "]";
    }

    /**
     * Converts deadline to string for storing.
     */
    @Override
    public String text() {
        return "D " + super.text() + " | " + by;
    }

    public String calculateDeadline() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = LocalDateTime.parse(by,df);
        Duration duration = Duration.between(now,deadline);
        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours();
        long minutes = duration.minusDays(days).minusHours(hours).toMinutes();
        if (now.isAfter(deadline)) {
            return "0";
        } else {
            return days + " days " + hours + " hours " + minutes + " minutes";
        }
    }

    public long remainingTime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = LocalDateTime.parse(by,df);
        Duration duration = Duration.between(now,deadline);
        long minutes = duration.toMinutes();
        return minutes;
    }
}
