package com.example.gitaction_test.brainWriting.service;


import com.example.gitaction_test.brainWriting.domain.BwChatMessage;
import com.example.gitaction_test.brainWriting.domain.BwRoom;
import com.example.gitaction_test.brainWriting.dto.BwMessageResponseDto;
import com.example.gitaction_test.brainWriting.repository.BwMessageRepository;
import com.example.gitaction_test.brainWriting.repository.BwRoomRepository;
import com.example.gitaction_test.user.User;
import com.example.gitaction_test.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BwMessageService {

    private final BwMessageRepository bwMessageRepository;
    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final UserService userService;
    private final BwRoomRepository bwRoomRepository;

    public BwMessageService(BwMessageRepository bwMessageRepository, ChannelTopic channelTopic, RedisTemplate redisTemplate, UserService userService, BwRoomRepository bwRoomRepository) {
        this.bwMessageRepository = bwMessageRepository;
        this.channelTopic = channelTopic;
        this.redisTemplate = redisTemplate;
        this.userService = userService;
        this.bwRoomRepository = bwRoomRepository;
    }

    public void SendBwChatMessage(BwMessageResponseDto bwMessageResponseDto) {
        log.info("SendBwChatMessage 시작");
        redisTemplate.convertAndSend(channelTopic.getTopic(), bwMessageResponseDto);
    }

    public void save(BwMessageResponseDto bwMessageResponseDto) {

        User user = userService.findById(bwMessageResponseDto.getSenderId());

        BwRoom bwRoom = bwRoomRepository.findById(Long.valueOf(bwMessageResponseDto.getRoomId())).orElseThrow(
                ()-> new NullPointerException()
        );

        BwChatMessage message = new BwChatMessage();

        message.setType(bwMessageResponseDto.getType());
        message.setMessage(bwMessageResponseDto.getMessage());
        message.setCreatedAt(bwMessageResponseDto.getCreatedAt());
        message.setUser(user);
        message.setRoom(bwRoom);
        message.setCreatedAt(bwMessageResponseDto.getCreatedAt());

        bwMessageRepository.save(message);
    }
}
