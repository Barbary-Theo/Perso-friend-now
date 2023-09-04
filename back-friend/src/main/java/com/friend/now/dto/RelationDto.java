package com.friend.now.dto;

import com.friend.now.model.Relation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationDto {

    private String firstname;
    private String lastname;
    private String pseudo;
    private String relationFirstname;
    private String relationLastname;
    private String relationPseudo;
    private String relationType;
    private Double coefRelation;

    public RelationDto(Relation relation) {
        if(relation != null && relation.getId() != null) {
            this.firstname = relation.getId().getFirstname();
            this.relationFirstname = relation.getId().getRelationFirstname();
            this.lastname = relation.getId().getLastname();
            this.relationLastname = relation.getId().getRelationLastname();
            this.pseudo = relation.getId().getPseudo();
            this.relationPseudo = relation.getId().getRelationPseudo();
            this.relationType = relation.getId().getRelationType();
            this.coefRelation = relation.getCoefRelation();
        }
    }

    public RelationDto(FriendDto mainFriend, FriendDto friendToAdd, String type, Double coef) {
        if(mainFriend != null && friendToAdd != null) {
            this.firstname = mainFriend.getFirstname();
            this.relationFirstname = friendToAdd.getFirstname();
            this.lastname = mainFriend.getLastname();
            this.relationLastname = friendToAdd.getLastname();
            this.pseudo = mainFriend.getPseudo();
            this.relationPseudo = friendToAdd.getPseudo();
            this.relationType = type;
            this.coefRelation = coef;
        }
    }

}
