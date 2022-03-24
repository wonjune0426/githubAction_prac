package com.example.gitaction_test.brainWriting.repository;


import com.example.gitaction_test.brainWriting.domain.BwComments;
import com.example.gitaction_test.brainWriting.domain.BwIdea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BwCommentsRepository extends JpaRepository<BwComments, Long> {
    List<BwComments> findAllByBwIdea(BwIdea bwIdea);
}
