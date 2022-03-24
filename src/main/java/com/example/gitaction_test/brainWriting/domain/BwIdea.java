package com.example.gitaction_test.brainWriting.domain;


import com.example.gitaction_test.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
public class BwIdea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Lob
    private String idea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bwRoom_id")
    private BwRoom bwRoom;

//    @OneToMany(mappedBy = "bwIdea")
//    @JoinColumn(name = "bw_comments")
//    private BwComments bwComments;

    @Column
    private String bwSequence;

    @Column
    private Integer bwIndex;

    @Column
    private Integer numberOfVotes;

    public BwIdea(User user, String bwSequence, BwRoom bwRoom, Integer bwIndex, Integer numberOfVotes) {
        this.user = user;
        this.bwSequence = bwSequence;
        this.bwRoom = bwRoom;
        this.bwIndex = bwIndex;
        this.numberOfVotes = numberOfVotes;
    }
}
