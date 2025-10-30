package com.cakmak.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "livestreams")
public class Livestream {

    @Id
    private String id = UUID.randomUUID().toString();

    private String url;

    @Column(name = "membership_required")
    private Boolean membershipRequired;

    private Integer price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "event")
    private Event event;

    public Livestream() {}

    public Livestream(String url,
                      Boolean membershipRequired,
                      Integer price,
                      Event event) {
        this.url = url;
        this.membershipRequired = membershipRequired;
        this.price = price;
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getMembershipRequired() {
        return membershipRequired;
    }

    public void setMembershipRequired(Boolean membershipRequired) {
        this.membershipRequired = membershipRequired;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
