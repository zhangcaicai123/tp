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
    public String lecSlot2 = null;
    int lecDay;
    int tutDay;
    int labDay;
    int lecDay2;
    String lecTime;
    String tutTime;
    String labTime;
    String lecTime2;
    LocalTime lecBegin;
    LocalTime tutBegin;
    LocalTime labBegin;
    LocalTime lecBegin2;
    LocalTime lecEnd;
    LocalTime tutEnd;
    LocalTime labEnd;
    LocalTime lecEnd2;
    Duration lecDuration;
    Duration tutDuration;
    Duration labDuration;
    Duration lecDuration2;
    boolean isSetSlotSuccess = true;

    public Module() {
    }

    public void setSlot() {
        try {
            String lecDay = this.lecSlot.substring(0, this.lecSlot.indexOf(" "));
            this.lecDay = weekOfDay(lecDay);
            this.lecTime = this.lecSlot.substring(this.lecSlot.indexOf(" ")).trim();
            this.lecBegin = beginTime(this.lecTime);
            this.lecEnd = endTime(this.lecTime);
            this.lecDuration = setInterval(this.lecBegin, this.lecEnd);
            if (lecSlot2 != null) {
                String lecDay2 = this.lecSlot2.substring(0, this.lecSlot2.indexOf(" "));
                this.lecDay2 = weekOfDay(lecDay2);
                this.lecTime2 = this.lecSlot2.substring(this.lecSlot2.indexOf(" ")).trim();
                this.lecBegin2 = beginTime(this.lecTime2);
                this.lecEnd2 = endTime(this.lecTime2);
                this.lecDuration2 = setInterval(this.lecBegin2, this.lecEnd2);
                if (TimeTable.checkTimeDayConflict(lecBegin,lecEnd,lecBegin2,lecEnd2,this.lecDay,this.lecDay2)) {
                    isSetSlotSuccess = false;
                }
            }
            String tutDay = this.tutSlot.substring(0, this.tutSlot.indexOf(" "));
            this.tutDay = weekOfDay(tutDay);
            this.tutTime = this.tutSlot.substring(this.tutSlot.indexOf(" ")).trim();
            this.tutBegin = beginTime(this.tutTime);
            this.tutEnd = endTime(this.tutTime);
            this.tutDuration = setInterval(this.tutBegin, this.tutEnd);
            if (!TimeTable.checkTimeDayConflict(lecBegin,lecEnd,tutBegin,tutEnd,this.lecDay,this.tutDay)
                    && !TimeTable.checkTimeDayConflict(lecBegin2,lecEnd2,tutBegin,tutEnd,
                    this.lecDay2,this.tutDay)) {
                if (this.labSlot != null) {
                    String labDay = this.labSlot.substring(0, this.labSlot.indexOf(" "));
                    this.labDay = weekOfDay(labDay);
                    this.labTime = this.labSlot.substring(this.labSlot.indexOf(" ")).trim();
                    this.labBegin = beginTime(this.labTime);
                    this.labEnd = endTime(this.labTime);
                    this.labDuration = setInterval(this.labBegin, this.labEnd);
                    if (TimeTable.checkTimeDayConflict(lecBegin,lecEnd,labBegin,labEnd,this.lecDay,this.labDay)
                            || TimeTable.checkTimeDayConflict(labBegin,labEnd,tutBegin,tutEnd,
                            this.labDay,this.tutDay)) {
                        isSetSlotSuccess = false;
                    }
                }
            } else {
                isSetSlotSuccess = false;
            }
            if (!isSetSlotSuccess) {
                System.out.println(lineCutOff);
                System.out.println("OOPS!!! Cannot add this module because of the time conflict between slots.");
                System.out.println("Please carefully check your module timetable and add the module again.");
                System.out.println(lineCutOff);
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

    public String lecText2() {
        return this.lecTime2 + " " + this.moduleCode + " lecture";
    }

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
        if (labSlot != null) {
            if (lecSlot2 != null) {
                moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + labSlot + "|" + lecSlot2;
            } else {
                moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + labSlot + "|";
            }
        } else if (lecSlot2 != null) {
            moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + null + "|" + lecSlot2;
        } else {
            moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|";
        }
        return moduleToAdd;
    }

}