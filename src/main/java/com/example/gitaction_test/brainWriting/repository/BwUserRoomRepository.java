package com.example.gitaction_test.brainWriting.repository;


import com.example.gitaction_test.brainWriting.domain.BwRoom;
import com.example.gitaction_test.brainWriting.domain.BwUserRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BwUserRoomRepository extends JpaRepository<BwUserRoom, Long> {
    List<BwUserRoom> findAllByBwroom(BwRoom bwRoom);
}
