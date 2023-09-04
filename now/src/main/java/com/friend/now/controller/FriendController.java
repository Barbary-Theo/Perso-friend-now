package com.friend.now.controller;

import com.friend.now.dto.FriendDto;
import com.friend.now.dto.RelationDto;
import com.friend.now.dto.RelationCreationDto;
import com.friend.now.model.Friend;
import com.friend.now.model.FriendId;
import com.friend.now.model.RelationId;
import com.friend.now.service.FriendService;
import com.friend.now.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private RelationService relationService;

    @GetMapping("/all")
    public ResponseEntity<List<FriendDto>> getAll() {
        return ResponseEntity.ok(friendService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSomeone(@RequestBody FriendDto friend) {
        boolean doesFriendAlreadyExist = friendService.findById(new FriendId(friend.getFirstname(), friend.getLastname(), friend.getPseudo())) != null;
        if(doesFriendAlreadyExist) return ResponseEntity.status(HttpStatus.CONFLICT).body("User that you want to save already exist");
        friendService.save(new Friend(friend));
        return ResponseEntity.ok("User saved successfully");
    }

    @PatchMapping("/add/relation")
    public ResponseEntity<String> addRelation(@RequestBody RelationCreationDto dto) {
        FriendDto mainFriend = friendService.findById(new FriendId(dto.getMainFriend().getFirstname(), dto.getMainFriend().getLastname(), dto.getMainFriend().getPseudo()));
        FriendDto friendToAdd = friendService.findById(new FriendId(dto.getFriendToAdd().getFirstname(), dto.getFriendToAdd().getLastname(), dto.getFriendToAdd().getPseudo()));

        boolean doesMainFriendExist = mainFriend != null;
        boolean doesFriendToAddExist = friendToAdd != null;

        if(!doesMainFriendExist || !doesFriendToAddExist) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Impossible to find one of both user you sent");

        if(!doesFriendContainsFriendsInItsList(mainFriend, friendToAdd, dto.getRelationType())) mainFriend.getRelations().add(new RelationDto(mainFriend, friendToAdd, dto.getRelationType(), dto.getCoefRelation()));
        else updateCoefOfRelationTargeted(mainFriend, friendToAdd, dto.getCoefRelation(), dto.getRelationType());
        if(!doesFriendContainsFriendsInItsList(friendToAdd, mainFriend, dto.getRelationType())) friendToAdd.getRelations().add(new RelationDto(friendToAdd, mainFriend, dto.getRelationType(), dto.getCoefRelation()));
        else updateCoefOfRelationTargeted(friendToAdd, mainFriend, dto.getCoefRelation(), dto.getRelationType());

        friendService.saveBoth(new Friend(mainFriend), new Friend(friendToAdd));

        return ResponseEntity.ok("Both users are now '" + dto.getRelationType() + "'");
    }

    @PatchMapping("/remove/relation")
    public ResponseEntity<String> removeRelation(@RequestBody RelationCreationDto dto) {
        FriendDto mainFriend = friendService.findById(new FriendId(dto.getMainFriend().getFirstname(), dto.getMainFriend().getLastname(), dto.getMainFriend().getPseudo()));
        FriendDto friendToAdd = friendService.findById(new FriendId(dto.getFriendToAdd().getFirstname(), dto.getFriendToAdd().getLastname(), dto.getFriendToAdd().getPseudo()));

        boolean doesMainFriendExist = mainFriend != null;
        boolean doesFriendToAddExist = friendToAdd != null;

        if(!doesMainFriendExist || !doesFriendToAddExist) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Impossible to find one of both user you sent");

        if(doesFriendContainsFriendsInItsList(mainFriend, friendToAdd, dto.getRelationType())) relationService.deleteById(new RelationId(mainFriend.getFirstname(), mainFriend.getLastname(), mainFriend.getPseudo(), friendToAdd.getFirstname(), friendToAdd.getLastname(), friendToAdd.getPseudo(), dto.getRelationType()));
        if(doesFriendContainsFriendsInItsList(friendToAdd, mainFriend, dto.getRelationType())) relationService.deleteById(new RelationId(friendToAdd.getFirstname(), friendToAdd.getLastname(), friendToAdd.getPseudo(), mainFriend.getFirstname(), mainFriend.getLastname(), mainFriend.getPseudo(), dto.getRelationType()));

        return ResponseEntity.ok("Both users are not '" + dto.getRelationType() + "' anymore");
    }

    public boolean doesFriendContainsFriendsInItsList(FriendDto mainFriend, FriendDto friendToAdd, String type) {
        return mainFriend.getRelations().stream().anyMatch(friend ->
                friend.getRelationFirstname().equals(friendToAdd.getFirstname())
                && friend.getRelationLastname().equals(friendToAdd.getLastname())
                && friend.getRelationPseudo().equals(friendToAdd.getPseudo())
                && friend.getRelationType().equals(type)
        );
    }

    public void updateCoefOfRelationTargeted(FriendDto mainFriend, FriendDto friendToAdd, Double coefToUpdate, String type) {
        mainFriend.getRelations().stream().filter(relation ->
                relation.getRelationFirstname().equals(friendToAdd.getFirstname())
                        && relation.getRelationLastname().equals(friendToAdd.getLastname())
                        && relation.getRelationPseudo().equals(friendToAdd.getPseudo())
                        && relation.getRelationType().equals(type)
        ).findFirst().ifPresent(relationTargeted -> relationTargeted.setCoefRelation(coefToUpdate));
    }


}
