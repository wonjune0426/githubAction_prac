package com.example.gitaction_test.brainWriting.dto;


import com.example.gitaction_test.brainWriting.domain.BwChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BwMessageResponseDto {

    private BwChatMessage.MessageType type;

    private String roomId;

    private Long senderId;

    private String sender;

    private String message;

    private String createdAt;

    @Builder
    public BwMessageResponseDto(BwChatMessage.MessageType type, String roomId, Long senderId, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }
}
