package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Planet")
public class Planet {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 500, nullable = false)
    private String name;

    @OneToMany(mappedBy = "fromPlanet", cascade = CascadeType.ALL)
    private List<Ticket> ticketsFrom;

    @OneToMany(mappedBy = "toPlanet", cascade = CascadeType.ALL)
    private List<Ticket> ticketsTo;

    public Planet() {

    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Ticket> getTicketsFrom() {
        return ticketsFrom;
    }

    public void setTicketsFrom(List<Ticket> ticketsFrom) {
        this.ticketsFrom = ticketsFrom;
    }

    public List<Ticket> getTicketsTo() {
        return ticketsTo;
    }

    public void setTicketsTo(List<Ticket> ticketsTo) {
        this.ticketsTo = ticketsTo;
    }

    public Planet(String id) {
        this.id = id;
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
