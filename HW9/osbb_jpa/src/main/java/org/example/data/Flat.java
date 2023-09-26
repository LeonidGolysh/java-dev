package org.example.data;

import javax.persistence.*;

@Entity
@Table(name = "flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "level")
    private  int level;

    @Column(name = "square")
    private double square;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(final double square) {
        this.square = square;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(final Building building) {
        this.building = building;
    }
}
