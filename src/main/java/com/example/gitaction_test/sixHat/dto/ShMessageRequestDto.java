package com.example.gitaction_test.sixHat.dto;


import com.example.gitaction_test.sixHat.domain.ShChatMessage;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShMessageRequestDto {

    private ShChatMessage.MessageType type;
    private String roomId;
    private Long senderId;
    private String subject;
    private String hat;
    private String sender;
    private String message;
}
