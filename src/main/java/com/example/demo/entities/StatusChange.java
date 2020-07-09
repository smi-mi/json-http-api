package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
}
