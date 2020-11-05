package seedu.duke;

import java.time.Duration;
import java.time.LocalTime;

public class Module {
    static String lineCutOff = "_______________________________________________________";

    public String moduleCode;
    String title;
    String description;
    String moduleCredit;
    public String lecSlot;
    public String tutSlot;
    public String labSlot = null;
    int lecDay;
    int tutDay;
    int labDay;
    String lecTime;
    String tutTime;
    String labTime;
    LocalTime lecBegin;
    LocalTime tutBegin;
    LocalTime labBegin;
    LocalTime lecEnd;
    LocalTime tutEnd;
    LocalTime labEnd;
    Duration lecDuration;
    Duration tutDuration;
    Duration labDuration;
    boolean isSetSlotSuccess = true;

    /**
     * Creates Module class object.
     */
    public Module(){
    }

    /**
     * Adds lecture, tutorial and lab slot timings to module.
     */
    public void setSlot() {
        try {
            String lecDay = this.lecSlot.substring(0, this.lecSlot.indexOf(" "));
            this.lecDay = weekOfDay(lecDay);
            this.lecTime = this.lecSlot.substring(this.lecSlot.indexOf(" ")).trim();
            String tutDay = this.tutSlot.substring(0, this.tutSlot.indexOf(" "));
            this.tutDay = weekOfDay(tutDay);
            this.tutTime = this.tutSlot.substring(this.tutSlot.indexOf(" ")).trim();
            this.lecBegin = beginTime(this.lecTime);
            this.lecEnd = endTime(this.lecTime);
            this.tutBegin = beginTime(this.tutTime);
            this.tutEnd = endTime(this.tutTime);
            this.lecDuration = setInterval(this.lecBegin, this.lecEnd);
            this.tutDuration = setInterval(this.tutBegin, this.tutEnd);
            if (this.labSlot != null) {
                String labDay = this.labSlot.substring(0, this.labSlot.indexOf(" "));
                this.labDay = weekOfDay(labDay);
                this.labTime = this.labSlot.substring(this.labSlot.indexOf(" ")).trim();
                this.labBegin = beginTime(this.labTime);
                this.labEnd = endTime(this.labTime);
                this.labDuration = setInterval(this.labBegin, this.labEnd);
            }
        } catch (StringIndexOutOfBoundsException e) {
            isSetSlotSuccess = false;
            System.out.println(lineCutOff);
            System.out.println("OOPS!!! Cannot add this module.");
            System.out.println("Please carefully follow the time format.");
            System.out.println(lineCutOff);
        }
    }

    /**
     * Returns lecture time, module code.
     *
     * @return String of lecture text.
     */
    public String lecText() {
        return this.lecTime + " " + this.moduleCode + " lecture";
    }

    /**
     * Returns tutorial time, module code.
     *
     * @return String of tutorial text.
     */
    public String tutText() {
        return this.tutTime + " " + this.moduleCode + " tutorial";
    }

    /**
     * Returns lab time, module code.
     *
     * @return String of lab text.
     */
    public String labText() {
        return this.labTime + " " + this.moduleCode + " lab";
    }

    /**
     * Returns int depending on day value entered.
     *
     * @param day Day.
     * @return dayValue Int value representing day.
     */
    public int weekOfDay(String day) {
        int dayValue = 0;
        switch (day) {
        case "Sun":
            dayValue = 7;
            break;
        case "Mon":
            dayValue = 1;
            break;
        case "Tue":
            dayValue = 2;
            break;
        case "Wed":
            dayValue = 3;
            break;
        case "Thur":
            dayValue = 4;
            break;
        case "Fri":
            dayValue = 5;
            break;
        case "Sat":
            dayValue = 6;
            break;
        default:
            System.out.println("Sorry, the day value was not recognized. ");
            System.exit(1);
        }
        return dayValue;

    }

    /**
     * Returns begin time after parsing.
     *
     * @param timeInterval Time interval entered by user.
     * @return LocalTime beginTime.
     */
    public LocalTime beginTime(String timeInterval) {
        return LocalTime.parse(timeInterval.substring(0, timeInterval.indexOf("-")).trim());
    }

    /**
     * Returns end time after parsing.
     *
     * @param timeInterval Time interval entered by user.
     * @return LocalTime endTime.
     */
    public LocalTime endTime(String timeInterval) {
        return LocalTime.parse(timeInterval.substring(timeInterval.indexOf("-") + 1).trim());
    }

    /**
     * Returns interval duration after parsing.
     *
     * @param begin Interval begin time.
     * @param end Interval end time.
     * @return Duration of interval.
     */
    public Duration setInterval(LocalTime begin, LocalTime end) {
        return Duration.between(begin, end);
    }

    /**
     * Converts module details to string for data saving.
     *
     * @return moduleToAdd.
     */
    public String toString() {
        String moduleToAdd;
        if (labSlot == null) {
            moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|";
        } else {
            moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + labSlot;
        }
        return moduleToAdd;
    }
}
