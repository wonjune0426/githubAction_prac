package com.example.gitaction_test.brainWriting.dto.bwVote;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BwVoteRequestDto {

    private Long userId;

    private List<Long> votedIdeaList;
}
