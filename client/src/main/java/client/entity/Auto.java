package client.entity;

import java.util.Collection;

public class Auto {

    private Long id;

    private String num;
    private String color;
    private String mark;

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
