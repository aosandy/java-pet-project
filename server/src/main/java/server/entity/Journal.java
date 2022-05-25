package server.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeOut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeIn;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto autoId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route routeId;

    public Journal() {
    }

    public Journal(Date timeOut, Date timeIn) {
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
