package com.friend.now.service;

import com.friend.now.dto.FriendDto;
import com.friend.now.mapper.FriendMapper;
import com.friend.now.model.Friend;
import com.friend.now.model.FriendId;
import com.friend.now.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private FriendMapper friendMapper;

    public List<FriendDto> findAll() {
        return friendMapper.mapFriendToDto(friendRepository.findAll());
    }
    public FriendDto findById(FriendId id) {
        Optional<Friend> friend = friendRepository.findById(id);
        return friend.map(FriendDto::new).orElse(null);
    }

    public void save(Friend friend) {
        friendRepository.save(friend);
    }
    public void saveBoth(Friend friendOne, Friend friendTwo) {
        friendRepository.saveAll(Arrays.asList(friendOne, friendTwo));
    }

    public FriendDto findOneByInfo(String info) {
        Optional<Friend> friend = friendRepository.findFirstByFirstnameOrLastnameOrPseudo(info);
        return friend.map(FriendDto::new).orElse(null);
    }

}