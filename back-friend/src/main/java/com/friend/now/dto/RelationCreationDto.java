package com.friend.now.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationCreationDto {
    private FriendDto mainFriend;
    private FriendDto friendToAdd;
    private String relationType;
    private Double coefRelation;
}
