package com.example.demo;

import java.io.Serializable;

/**
 * Created by alcava00 on 2017. 7. 14..
 */
public class Person implements Serializable {
    public Person(){}
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private String id;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}