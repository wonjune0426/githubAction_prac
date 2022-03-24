package com.example.gitaction_test.sixHat.repository;


import com.example.gitaction_test.sixHat.domain.ShChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShMessageRepository extends JpaRepository<ShChatMessage, Long> {
}
