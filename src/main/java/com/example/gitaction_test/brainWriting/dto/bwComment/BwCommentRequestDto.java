package com.example.gitaction_test.brainWriting.dto.bwComment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BwCommentRequestDto {

    private Long userId;

    private Long ideaId;

    private String comment;
}
