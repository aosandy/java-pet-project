package client.entity;

import java.util.Date;

public class Journal {

    private Long id;

    private Date timeOut;
    private Date timeIn;

    public Journal() {
    }

    public Journal(Date timeOut, Date timeIn) {
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }
}
