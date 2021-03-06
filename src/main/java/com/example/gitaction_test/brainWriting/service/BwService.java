package com.example.gitaction_test.brainWriting.service;


import com.example.gitaction_test.brainWriting.domain.BwComments;
import com.example.gitaction_test.brainWriting.domain.BwIdea;
import com.example.gitaction_test.brainWriting.domain.BwRoom;
import com.example.gitaction_test.brainWriting.domain.BwUserRoom;
import com.example.gitaction_test.brainWriting.dto.*;
import com.example.gitaction_test.brainWriting.dto.bwComment.BwCommentRequestDto;
import com.example.gitaction_test.brainWriting.dto.bwComment.BwCommentResponseDto;
import com.example.gitaction_test.brainWriting.dto.bwIdea.BwIdeaRequestDto;
import com.example.gitaction_test.brainWriting.dto.bwIdea.BwIdeaResponseDto;
import com.example.gitaction_test.brainWriting.dto.bwVote.BwVoteRequestDto;
import com.example.gitaction_test.brainWriting.dto.bwVote.BwVoteResponseDto;
import com.example.gitaction_test.brainWriting.dto.bwVoteView.BwVoteViewCardsItem;
import com.example.gitaction_test.brainWriting.dto.bwVoteView.BwVoteViewResponseDto;
import com.example.gitaction_test.brainWriting.repository.BwCommentsRepository;
import com.example.gitaction_test.brainWriting.repository.BwIdeaRepository;
import com.example.gitaction_test.brainWriting.repository.BwRoomRepository;
import com.example.gitaction_test.brainWriting.repository.BwUserRoomRepository;
import com.example.gitaction_test.user.User;
import com.example.gitaction_test.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Service
public class BwService {

    private final BwRoomRepository bwRoomRepository;
    private final BwUserRoomRepository bwUserRoomRepository;
    private final UserService userService;
    private final BwIdeaRepository bwIdeaRepository;
    private final BwCommentsRepository bwCommentsRepository;

    @Autowired
    public BwService(BwRoomRepository bwRoomRepository, BwUserRoomRepository bwUserRoomRepository, UserService userService, BwIdeaRepository bwIdeaRepository, BwCommentsRepository bwCommentsRepository) {
        this.bwRoomRepository = bwRoomRepository;
        this.bwUserRoomRepository = bwUserRoomRepository;
        this.userService = userService;
        this.bwIdeaRepository = bwIdeaRepository;
        this.bwCommentsRepository = bwCommentsRepository;
    }

    // 브레인 라이팅 방 생성.
    public BwRoomResponseDto createBwRoom(BwRoomRequestDto requestDto) {

        BwRoom bwRoom = new BwRoom(requestDto.getHeadCount(), requestDto.getTime(), 0);

        bwRoomRepository.save(bwRoom);

        return new BwRoomResponseDto(bwRoom.getId(),requestDto.getHeadCount(), requestDto.getTime());
    }

    // 브레인 라이팅 닉네임 입력.
    @Transactional
    public BwNickResponseDto createNickname(BwNickRequestDto requestDto) {

        BwRoom room = bwRoomRepository.findById(Long.valueOf(requestDto.getRoomId())).orElseThrow(
                () -> new NullPointerException()
        );

        User user = userService.save(requestDto.getNickname());

        if(room.getHostId() == null) {
            room.setHostId(user.getId());
        }
        bwRoomRepository.save(room);

        bwUserRoomRepository.save(new BwUserRoom(user, room));

        return new BwNickResponseDto(user.getId(), user.getNickname());
    }

    public void createIdea(Long bwRoomId) {
        BwRoom bwRoom = bwRoomRepository.findById(bwRoomId).orElseThrow(
                () -> new NullPointerException()
        );

        List<BwUserRoom> userRoomList = bwUserRoomRepository.findAllByBwroom(bwRoom);
        Queue<User> userQueue = new LinkedList<>();
        for(BwUserRoom bwUserRoom:userRoomList) {
            userQueue.add(bwUserRoom.getUser());
        }
        List<BwIdea> bwIdeaList = new ArrayList<>();
        for(int i=0; i<bwRoom.getHeadCount(); i++) {
            StringBuilder sequence = new StringBuilder();
            for(User user : userQueue) {
                sequence.append(user.getId());
                sequence.append(":");
            }
            User user = userQueue.poll();
            userQueue.add(user);

            BwIdea bwIdea = new BwIdea(user, sequence.toString(),bwRoom, 1, 0);

            bwIdeaList.add(bwIdea);
        }
        List<BwIdea> bwIdeaListTest =  bwIdeaRepository.saveAll(bwIdeaList);

        // 테스트용
//        List<BwIdea> bwIdeaListTest = bwIdeaRepository.findAllByBwRoom(bwRoom);
        for (BwIdea bwIdea : bwIdeaListTest) {
            log.info("{} {}", bwIdea.getId(),bwIdea.getBwSequence());
        }
    }

    // 브레인 라이팅 아이디어 입력
    @Transactional
    public BwIdeaResponseDto insertIdea(Long bwRoomId, BwIdeaRequestDto requestDto) {
        BwRoom bwRoom = bwRoomRepository.findById(bwRoomId).orElseThrow(
                () -> new NullPointerException()
        );
        User user = userService.findById(requestDto.getUserId());

        BwIdea bwIdea = bwIdeaRepository.findByUser(user);
        log.info(requestDto.getIdea());
        bwIdea.setIdea(requestDto.getIdea());
        bwIdeaRepository.save(bwIdea);

        return new  BwIdeaResponseDto(user.getId(),bwIdea.getIdea());
    }

    // 브레인 라이팅 아이디어 목록 반환
    public BwIdeaListDto getAllIdeaWithOrederBy(Long bwRoomId) {

        BwRoom bwRoom = bwRoomRepository.findById(bwRoomId).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );

        List<BwIdea> bwIdeaList = bwIdeaRepository.findAllByBwRoom(bwRoom);
        List<BwIdeaListItem> bwIdeaListItemList = new ArrayList<>();

        boolean endComment = false;
        for(BwIdea bwIdea:bwIdeaList) {
            BwIdeaListItem bwIdeaListItem = new BwIdeaListItem();

            String[] strings = bwIdea.getBwSequence().split(":");

            bwIdeaListItem.setViewUserId(Long.valueOf(strings[bwIdea.getBwIndex()]));
            bwIdeaListItem.setIdeaId(bwIdea.getId());
            bwIdeaListItem.setIdea(bwIdea.getIdea());
            bwIdeaListItemList.add(bwIdeaListItem);

            bwIdea.setBwIndex(bwIdea.getBwIndex()+1);
            bwIdeaRepository.save(bwIdea);
            if(bwIdea.getBwIndex().equals(bwRoom.getHeadCount())) {
                endComment = true;
            }
        }
        return new BwIdeaListDto(endComment, bwIdeaListItemList);
    }

    // 코멘트 입력
    public BwCommentResponseDto insertComment(Long bwRoomId, BwCommentRequestDto requestDto) {
        BwRoom bwRoom = bwRoomRepository.findById(bwRoomId).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
        User user =userService.findById(requestDto.getUserId());
        BwIdea bwIdea = bwIdeaRepository.findById(requestDto.getIdeaId()).orElseThrow(
                () -> new NullPointerException("해당 아이디어가 존재하지 않습니다.")
        );

        BwComments bwComments = new BwComments();
        bwComments.setComments(requestDto.getComment());
        bwComments.setUser(user);
        bwComments.setBwRoom(bwRoom);
        bwComments.setBwIdea(bwIdea);

        bwCommentsRepository.save(bwComments);

        return new BwCommentResponseDto(bwComments.getBwIdea().getId(), bwComments.getUser().getId(), bwComments.getComments());
    }

    // 투표뷰 데이터 주기.
    public BwVoteViewResponseDto voteViewIdea(Long bwRoomId) {

        BwRoom bwRoom = bwRoomRepository.findById(bwRoomId).orElseThrow(
                ()-> new NullPointerException()
        );

        List<BwVoteViewCardsItem> bwVoteViewCardsItemList = new ArrayList<>();

        List<BwIdea> bwIdeaList = bwIdeaRepository.findAllByBwRoom(bwRoom);

        for(BwIdea bwIdea : bwIdeaList) {

            List<BwComments> bwCommentsList = bwCommentsRepository.findAllByBwIdea(bwIdea);

            BwVoteViewCardsItem bwVoteViewCardsItem = new BwVoteViewCardsItem();

            bwVoteViewCardsItem.setIdeaId(bwIdea.getId());
            bwVoteViewCardsItem.setIdea(bwIdea.getIdea());
            bwVoteViewCardsItem.setCommentsList(bwCommentsList);

            bwVoteViewCardsItemList.add(bwVoteViewCardsItem);
        }
        return new BwVoteViewResponseDto(bwRoom.getSubject(), bwVoteViewCardsItemList);
    }

    // 투표하기
    @Transactional
    public BwVoteResponseDto voteIdea(Long bwRoomId, BwVoteRequestDto requestDto) {

        userService.isvote(requestDto.getUserId());

        for(Long votedIdeaId : requestDto.getVotedIdeaList()) {
            BwIdea bwIdea = bwIdeaRepository.findById(votedIdeaId).orElseThrow(
                    () -> new NullPointerException("찾는 아이디어가 없습니다.")
            );
            bwIdea.setNumberOfVotes(bwIdea.getNumberOfVotes()+1);
        }

        BwRoom bwRoom = bwRoomRepository.findById(bwRoomId).orElseThrow(
                () -> new NullPointerException("찾는 브레인라이팅 방이 없습니다.")
        );

        bwRoom.setPresentVoted(bwRoom.getPresentVoted()+1);
        bwRoomRepository.save(bwRoom);


        return new BwVoteResponseDto(bwRoom.getHeadCount(), bwRoom.getPresentVoted());
    }

    // 인원 1증가
    @Transactional
    public void plusUserCount(String roomId) {

        BwRoom bwRoom = bwRoomRepository.findById(Long.valueOf(roomId)).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );

        bwRoom.setCurrentUsers(bwRoom.getCurrentUsers() + 1);

        bwRoomRepository.save(bwRoom);
    }

    // 인원 1 감소
    @Transactional
    public void minusUserCount(String roomId) {
        BwRoom bwRoom = bwRoomRepository.findById(Long.valueOf(roomId)).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );

        bwRoom.setCurrentUsers(bwRoom.getCurrentUsers() -1);
        bwRoomRepository.save(bwRoom);
    }

    // 브레인 라이팅 방 찾기
    public BwRoom findBwRoom(String roomId) {
        return bwRoomRepository.findById(Long.valueOf(roomId)).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
    }
}
