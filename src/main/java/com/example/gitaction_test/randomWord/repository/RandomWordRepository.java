package com.example.gitaction_test.randomWord.repository;


import com.example.gitaction_test.randomWord.model.RandomWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RandomWordRepository extends JpaRepository<RandomWord,Long> {
    Optional<RandomWord> findByUuId(String uuId);
}
