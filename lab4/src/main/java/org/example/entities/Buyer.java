package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyer")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "buyerName")
    private String buyerName;

    @Column(name = "buyerSurname")
    private String buyerSurname;

    @Column(name = "buyerEmail")
    private String buyerEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer",cascade = CascadeType.ALL)
    private List<SSD> credit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer",cascade = CascadeType.ALL)
    private List<HDD> exam;

    public Buyer( String buyerName, String buyerSurname, String buyerEmail) {
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.buyerEmail = buyerEmail;
    }

    public double getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerSurname() {
        return buyerSurname;
    }

    public void setBuyerSurname(String buyerSurname) {
        this.buyerSurname = buyerSurname;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", buyerName='" + buyerName + '\'' +
                ", buyerSurname='" + buyerSurname + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                '}';
    }
}
