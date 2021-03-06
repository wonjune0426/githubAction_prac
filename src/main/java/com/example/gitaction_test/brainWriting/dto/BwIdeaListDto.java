package com.example.gitaction_test.brainWriting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class BwIdeaListDto {

    private Boolean endComment;

    private List<BwIdeaListItem> bwIdeaListItemList;

}
