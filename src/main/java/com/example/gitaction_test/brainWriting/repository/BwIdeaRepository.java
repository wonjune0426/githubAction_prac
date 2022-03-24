package com.example.gitaction_test.brainWriting.repository;


import com.example.gitaction_test.brainWriting.domain.BwIdea;
import com.example.gitaction_test.brainWriting.domain.BwRoom;
import com.example.gitaction_test.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BwIdeaRepository extends JpaRepository<BwIdea, Long> {
        BwIdea findByUser(User user);

        List<BwIdea> findAllByBwRoom(BwRoom bwRoom);

        BwIdea findByBwRoomAndUser(BwRoom bwRoom, User user);


}
