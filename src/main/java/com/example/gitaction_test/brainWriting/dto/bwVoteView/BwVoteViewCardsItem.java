package com.example.gitaction_test.brainWriting.dto.bwVoteView;

import com.example.gitaction_test.brainWriting.domain.BwComments;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BwVoteViewCardsItem {

    private Long ideaId;

    private String idea;

    private List<BwComments> commentsList;
}
