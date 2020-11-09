package seedu.duke;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

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
            if (TimeTable.checkTimeDayConflict(lecBegin,lecEnd,tutBegin,tutEnd,this.lecDay,this.tutDay)
                    || TimeTable.checkTimeDayConflict(lecBegin2,lecEnd2,tutBegin,tutEnd,
                    this.lecDay2,this.tutDay)) {
                isSetSlotSuccess = false;
            }
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
            if (!isSetSlotSuccess) {
                System.out.println(lineCutOff);
                System.out.println("OOPS!!! Cannot add " + this.moduleCode
                        + " because of the time conflict between slots.");
                System.out.println("Please carefully check your module timetable and add the module again.");
                System.out.println(lineCutOff);
            }
        } catch (StringIndexOutOfBoundsException e) {
            isSetSlotSuccess = false;
            System.out.println(lineCutOff);
            System.out.println("OOPS!!! Cannot add " + this.moduleCode + ".");
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
     * Returns second lecture time, module code.
     *
     * @return String of lecture text.
     */
    public String lecText2() {
        return this.lecTime2 + " " + this.moduleCode + " lecture";
    }

    public int weekOfDay(String day) {
        int dayValue = 0;
        switch (day) {
        case "Sun":
        case "Sunday":
            dayValue = 7;
            break;
        case "Mon":
        case "Monday":
            dayValue = 1;
            break;
        case "Tue":
        case "Tuesday":
            dayValue = 2;
            break;
        case "Wed":
        case "Wednesday":
            dayValue = 3;
            break;
        case "Thur":
        case "Thursday":
        case "Thu":
            dayValue = 4;
            break;
        case "Fri":
        case "Friday":
            dayValue = 5;
            break;
        case "Sat":
        case "Saturday":
            dayValue = 6;
            break;
        default:
            System.out.println("Sorry, the day value was not recognized. ");
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
        if (labSlot != null) {
            if (lecSlot2 != null) {
                moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + labSlot + "|" + lecSlot2;
            } else {
                moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + labSlot;
            }
        } else if (lecSlot2 != null) {
            moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot + "|" + null + "|" + lecSlot2;
        } else {
            moduleToAdd = moduleCode + "|" + lecSlot + "|" + tutSlot;
        }
        return moduleToAdd;
    }

}
