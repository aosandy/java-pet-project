package server.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)    private String num;
    private String color;
    private String mark;

    @OneToMany(mappedBy = "autoId")
    private Collection<Journal> journals;

    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private AutoPersonnel personnelId;

    public Auto() {
    }

    public Auto(String num, String color, String mark) {
        this.num = num;
        this.color = color;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
