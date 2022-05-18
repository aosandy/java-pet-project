package server.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class AutoPersonnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String patherName;

    @OneToMany(mappedBy = "personnelId")
    private Collection<Auto> autos;

    public AutoPersonnel() {
    }

    public AutoPersonnel(String firstName, String lastName, String patherName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatherName() {
        return patherName;
    }
}
