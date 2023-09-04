package com.friend.now.repository;

import com.friend.now.model.Friend;
import com.friend.now.model.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

}
