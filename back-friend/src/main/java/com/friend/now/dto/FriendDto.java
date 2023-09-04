package com.friend.now.dto;

import com.friend.now.model.Friend;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class FriendDto {

    private String firstname;
    private String lastname;
    private String pseudo;
    private List<RelationDto> relations = new ArrayList<>();

    public FriendDto(Friend friend) {
        if(friend != null && friend.getId() != null) {
            this.firstname = friend.getId().getFirstname();
            this.lastname = friend.getId().getLastname();
            this.pseudo = friend.getId().getPseudo();
            this.relations = friend.getRelations().stream().map(RelationDto::new).collect(Collectors.toList());
        }
    }

}
