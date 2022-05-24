package appointment;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    int id;
    Date date;
    Time time;

    public Appointment(int id, Date date, Time time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
