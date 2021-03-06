package com.example.gitaction_test.sixHat.domain;


import com.example.gitaction_test.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ShChatMessage {

    public enum MessageType {
        ENTER, TALK, DEBATING, QUIT, HAT, SUBJECT
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private MessageType type;

    @Column
    private String hat;

    @Column
    private String message;

    @Column
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shroom_id")
    private ShRoom shRoom;

}
