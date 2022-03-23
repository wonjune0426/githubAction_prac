package com.example.gitaction_test.randomWord.repository;


import com.example.gitaction_test.randomWord.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
    Word findWordByWord(String word);
}
