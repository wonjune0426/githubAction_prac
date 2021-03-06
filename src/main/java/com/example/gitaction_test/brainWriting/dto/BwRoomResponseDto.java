package com.example.gitaction_test.brainWriting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BwRoomResponseDto {

    private String roomId;

    private Integer headCount;

    private Integer time;

    public BwRoomResponseDto(String roomId,Integer headCount, Integer time) {
        this.roomId = roomId;
        this.headCount = headCount;
        this.time = time;
    }
}
