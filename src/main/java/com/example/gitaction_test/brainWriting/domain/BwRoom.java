package com.example.gitaction_test.brainWriting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BwRoom {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private Integer headCount;

    @Column
    private Integer timer;

    @Column
    private String subject;

    @Column
    private Long hostId;

    @Column
    private Integer presentVoted;

    @Column
    private Integer insertCount;

    @Column
    private Integer currentUsers;

    public BwRoom(Integer headCount, Integer timer, String subject, Long hostId) {
        this.headCount = headCount;
        this.timer = timer;
        this.subject = subject;
        this.hostId = hostId;
    }

    public  BwRoom(Integer headCount, Integer timer, Integer currentUsers) {
        this.headCount = headCount;
        this.timer = timer;
        this.currentUsers = currentUsers;
    }
}
