package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class StatusChange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;
    private Long timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    private Status lastStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    private Status newStatus;

    public StatusChange(Profile profile, Long timestamp, Status lastStatus, Status newStatus) {
        this.profile = profile;
        this.timestamp = timestamp;
        this.lastStatus = lastStatus;
        this.newStatus = newStatus;
    }

    public StatusChange() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Status getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Status lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }
}
