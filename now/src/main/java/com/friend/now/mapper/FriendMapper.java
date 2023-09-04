package com.friend.now.mapper;

import com.friend.now.dto.FriendDto;
import com.friend.now.model.Friend;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FriendMapper {

    public List<FriendDto> mapFriendToDto(List<Friend> friends) {
        return friends.stream().map(FriendDto::new).collect(Collectors.toList());
    }

}
