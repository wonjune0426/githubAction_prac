package com.example.gitaction_test.randomWord.repository;


import com.example.gitaction_test.randomWord.model.RandomWord;
import com.example.gitaction_test.randomWord.model.RwWd;
import com.example.gitaction_test.randomWord.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RwWdRepository extends JpaRepository<RwWd,Long> {
    @Query("select r.word from RwWd r where r.randomWord=:randomWord")
    List<Word> findWordByRandomWord(RandomWord randomWord);
}
