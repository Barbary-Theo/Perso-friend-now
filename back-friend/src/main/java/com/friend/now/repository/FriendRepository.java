package com.friend.now.repository;

import com.friend.now.model.Friend;
import com.friend.now.model.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    @Query("SELECT f FROM Friend f" +
            "    WHERE UPPER(CONCAT(f.id.pseudo, ' ', f.id.lastname, ' ', f.id.firstname)) = UPPER(:info)" +
            "       OR UPPER(CONCAT(f.id.pseudo, ' ', f.id.firstname, ' ', f.id.lastname)) = UPPER(:info)" +
            "       OR UPPER(CONCAT(f.id.lastname, ' ', f.id.firstname, ' ', f.id.pseudo)) = UPPER(:info)" +
            "       OR UPPER(CONCAT(f.id.firstname, ' ', f.id.lastname, ' ', f.id.pseudo)) = UPPER(:info)" +
            "       OR UPPER(CONCAT(f.id.lastname, ' ', f.id.firstname)) = UPPER(:info)" +
            "       OR UPPER(CONCAT(f.id.firstname, ' ', f.id.lastname)) = UPPER(:info)" +
            "       OR UPPER(f.id.firstname) = UPPER(:info) " +
            "       OR UPPER(f.id.lastname) = UPPER(:info) " +
            "       OR UPPER(f.id.pseudo) = UPPER(:info)")
    public Optional<Friend> findFirstByFirstnameOrLastnameOrPseudo(@Param("info") String info);

}
