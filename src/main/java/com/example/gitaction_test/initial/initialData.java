package com.example.gitaction_test.initial;

//@Component
//@RequiredArgsConstructor
//public class initialData implements ApplicationRunner {

//    private final UserRepository userRepository;
//    private final BwRoomRepository chatRoomRepository;
//    private  final BwUserRoomRepository bwUserRoomRepository;
//
//    @Override
//    @Transactional
//    public void run(ApplicationArguments args) throws Exception {
////        String password = bCryptPasswordEncoder.encode("123456");
////        User user = new User("123@gmail.com", "익명의 프로그래머",password );
//
//        User user1 = new User("testuser1");
//        userRepository.save(user1);
//        User user2 = new User("testuser2");
//        userRepository.save(user2);
//        User user3 = new User("testuser3");
//        userRepository.save(user3);
//        User user4 = new User("testuser4");
//        userRepository.save(user4);
//
//        BwRoom bwRoom = new BwRoom(4, 1, "테스트용주제", 1L);
//
//        chatRoomRepository.save(bwRoom);
//
//        BwUserRoom bwUserRoom1 = new BwUserRoom(user1, bwRoom);
//        bwUserRoomRepository.save(bwUserRoom1);
//        BwUserRoom bwUserRoom2 = new BwUserRoom(user2, bwRoom);
//        bwUserRoomRepository.save(bwUserRoom2);
//        BwUserRoom bwUserRoom3 = new BwUserRoom(user3, bwRoom);
//        bwUserRoomRepository.save(bwUserRoom3);
//        BwUserRoom bwUserRoom4 = new BwUserRoom(user4, bwRoom);
//        bwUserRoomRepository.save(bwUserRoom4);
//
//
//
//    }
//}
