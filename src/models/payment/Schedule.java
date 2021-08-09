package src.models.payment;

import java.io.Serializable;

public class Schedule implements Serializable {
    private int time_gap;
    private int day;
    private int week_day;

    public Schedule() {
        this.time_gap = -1;
        this.day = -1;
        this.week_day = -1;
    }

    public int getTimeGap() {
        return this.time_gap;
    }

    public boolean setTimeGap(String time_gap) {
        String[] gaps = { "mensal", "semanal" };
        for (int i = 0; i < 2; i++) {
            if (gaps[i].equals(time_gap)) {
                this.time_gap = i;
                return true;
            }
        }
        return false;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeekDay() {
        return this.week_day;
    }

    public boolean setWeekDay(String week_day) {
        String[] days = { "domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sabado" };
        for (int i = 0; i < 7; i++) {
            if (days[i].equals(week_day)) {
                this.week_day = i + 1;
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        switch (this.time_gap) {
            case -1:
                return false;
            case 0:
                if (this.day == -1)
                    return false;
                break;
            case 1:
                if (this.day == -1 || this.week_day == -1)
                    return false;
                break;
        }
        return true;
    }

    public String toString() {
        if (!isValid())
            return "";
        String[] gaps = { "mensal", "semanal" };
        String[] days = { "domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sabado" };
        if (this.time_gap == 0) {
            if (day == 50) {
                return gaps[this.time_gap] + " $";
            } else {
                return gaps[this.time_gap] + " " + this.day;
            }
        }
        return gaps[this.time_gap] + " " + this.day + " " + days[this.week_day - 1];
    }
}
