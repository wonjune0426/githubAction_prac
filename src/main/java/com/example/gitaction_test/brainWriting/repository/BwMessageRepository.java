package com.example.gitaction_test.brainWriting.repository;


import com.example.gitaction_test.brainWriting.domain.BwChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BwMessageRepository extends JpaRepository<BwChatMessage, Long> {
}

