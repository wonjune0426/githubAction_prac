package com.example.gitaction_test.sixHat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShRoom {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String title;

    @Column
    private Integer headCount;

    @Column
    private Integer shTimer;

    @Column
    private String subject;

    @Column
    private Long hostId;

    @Column
    private Integer currentUsers;


    public ShRoom(String title, Integer headCount, Integer shTimer, Integer currentUsers) {
        this.title = title;
        this.headCount = headCount;
        this.shTimer = shTimer;
        this.currentUsers = currentUsers;
    }
}
