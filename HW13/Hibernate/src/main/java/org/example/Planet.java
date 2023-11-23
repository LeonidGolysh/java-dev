package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "Planet")
public class Planet {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 500, nullable = false)
    private String name;

    public Planet() {

    }

    public Planet(String id) {
        this.id = id;
    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
