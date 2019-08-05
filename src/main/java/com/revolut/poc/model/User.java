package com.revolut.poc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

@XmlRootElement(name = "movie")
public class User implements Serializable {

    private static final long serialVersionUID = -512379500912350486L;

    private Integer id;
    private String name;
    private Date dob;

    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public User(Integer id, String name, Date dob) {
        super();
        this.name = name;
        this.dob = dob;
        this.id = id;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", dob = " + dob +
                '}';
    }
}
