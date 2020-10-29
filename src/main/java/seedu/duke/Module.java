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

    public Module(){
    }

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

    public String lecText() {
        return this.lecTime + " " + this.moduleCode + " lecture";
    }

    public String tutText() {
        return this.tutTime + " " + this.moduleCode + " tutorial";
    }

    public String labText() {
        return this.labTime + " " + this.moduleCode + " lab";
    }

    public int weekOfDay(String day) {
        int dayValue = 0;
        switch (day) {
        case "Sun":
            dayValue = 1;
            break;
        case "Mon":
            dayValue = 2;
            break;
        case "Tue":
            dayValue = 3;
            break;
        case "Wed":
            dayValue = 4;
            break;
        case "Thur":
            dayValue = 5;
            break;
        case "Fri":
            dayValue = 6;
            break;
        case "Sat":
            dayValue = 7;
            break;
        default:
            System.out.println("Sorry, the day value was not recognized. ");
            System.exit(1);
        }
        return dayValue;

    }

    public LocalTime beginTime(String timeInterval) {
        return LocalTime.parse(timeInterval.substring(0, timeInterval.indexOf("-")).trim());
    }

    public LocalTime endTime(String timeInterval) {
        return LocalTime.parse(timeInterval.substring(timeInterval.indexOf("-") + 1).trim());
    }

    public Duration setInterval(LocalTime begin, LocalTime end) {
        return Duration.between(begin, end);
    }

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
