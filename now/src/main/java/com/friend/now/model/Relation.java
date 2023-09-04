package com.friend.now.model;

import com.friend.now.dto.RelationDto;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "relation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {

    @EmbeddedId
    private RelationId id;
    @Column(name = "coef_relation")
    private Double coefRelation;

    public Relation(FriendId mainId, RelationDto dto) {
        this.id = new RelationId(mainId.getFirstname(), mainId.getLastname(), mainId.getPseudo(),
                dto.getRelationFirstname(), dto.getRelationLastname(), dto.getRelationPseudo(), dto.getRelationType());
        this.coefRelation = dto.getCoefRelation();
    }
}
