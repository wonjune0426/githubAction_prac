package com.example.gitaction_test.randomWord.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RwResponseDto {
    String rwId;
    List<String> wordList;
}
