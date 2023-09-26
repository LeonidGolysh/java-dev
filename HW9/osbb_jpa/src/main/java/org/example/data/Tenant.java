package org.example.data;

import javax.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "auto_allow")
    private byte autoAllow;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public byte getAutoAllow() {
        return autoAllow;
    }

    public void setAutoAllow(final byte autoAllow) {
        this.autoAllow = autoAllow;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(final Flat flat) {
        this.flat = flat;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

}
