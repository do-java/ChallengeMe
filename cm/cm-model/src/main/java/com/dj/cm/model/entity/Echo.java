package com.dj.cm.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Echo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    public Echo() {
    }

    public Echo(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Echo(Echo source) {
        this(source.getId(), source.getValue());
    }

    public Echo(String echo) {
        this.value = echo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Echo{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

}
