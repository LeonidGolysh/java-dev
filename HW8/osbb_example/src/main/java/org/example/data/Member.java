package org.example.data;

public class Member {
    private int id;
    private String name;
    private int autoAllow;
    private String role;
    private int flatNumber;
    private int flatLevel;
    private int flatSquare;
    private String adress;

    public int getId() {
        return id;
    }

    Member setId(final int newValue) {
        id = newValue;
        return this;
    }

    public String getName() {
        return name;
    }

    Member setName(final String newValue) {
        name = newValue;
        return this;
    }

    public int getAutoAllow() {
        return autoAllow;
    }

    Member setAutoAllow(final int newValue) {
        autoAllow = newValue;
        return this;
    }

    public String getRole() {
        return role;
    }

    Member setRole(final String newValue) {
        role = newValue;
        return this;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    Member setFlatNumber(final int newValue) {
        flatNumber = newValue;
        return this;
    }

    public int getFlatLevel() {
        return flatLevel;
    }

    Member setFlatLevel(final int newValue) {
        flatLevel = newValue;
        return this;
    }

    public int getFlatSquare() {
        return flatSquare;
    }

    Member setFlatSquare(final int newValue) {
        flatSquare = newValue;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    Member setAdress(final String newValue) {
        adress = newValue;
        return this;
    }
}
