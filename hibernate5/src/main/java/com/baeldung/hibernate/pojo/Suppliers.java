package com.baeldung.hibernate.pojo;
// Generated Feb 9, 2017 11:31:36 AM by Hibernate Tools 5.1.0.Final

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Suppliers generated by hbm2java
 */
@Entity(name = "Suppliers")
public class Suppliers implements java.io.Serializable {

    @Id
    private Integer id;
    private String name;
    private String country;

    public Suppliers() {
    }

    public Suppliers(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("[").append(id).append(",").append(name).append(",").append(country).append("]").toString();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Suppliers) obj).getName());
    }
}
