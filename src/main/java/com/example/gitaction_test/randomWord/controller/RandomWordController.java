package com.example.gitaction_test.randomWord.controller;


import com.example.gitaction_test.randomWord.dto.RwRequestDto;
import com.example.gitaction_test.randomWord.dto.RwResponseDto;
import com.example.gitaction_test.randomWord.service.RandomWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RandomWordController {
    private final RandomWordService wordService;


    //런덤한 단어를 받는 요청
    @GetMapping("/randomWord")
    public List<String> getWordList() {
        return wordService.getWord();
    }

    //사용자가 선택한 단어를 저장하는 요청
    @PostMapping("/randomWord")
    public RwResponseDto saveWord(@RequestBody RwRequestDto requestDto) {
        return wordService.saveWord(requestDto);
    }

    //공유 여부를 수정하는 요청(공유가능 -> 공유 불가능)
    @PatchMapping("/randomWord/share/{rwId}")
    public String shareCheck(@PathVariable String rwId) {
        return wordService.shareCheck(rwId);
    }

    //랜덤워드 결과에 관한 상세 조회 요청
    @GetMapping("/gallery/randomWord/{rwId}")
    public RwResponseDto getRwGallery(@PathVariable String rwId) {
        return wordService.getRwGallery(rwId);
    }
}