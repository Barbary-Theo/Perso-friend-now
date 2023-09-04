package com.friend.now.model;

import com.friend.now.dto.FriendDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "friend")
@Data
@NoArgsConstructor
public class Friend {

    @EmbeddedId
    private FriendId id;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumns({
            @JoinColumn(name = "firstname", referencedColumnName = "firstname"),
            @JoinColumn(name = "lastname", referencedColumnName = "lastname"),
            @JoinColumn(name = "pseudo", referencedColumnName = "pseudo")
    })
    private List<Relation> relations = new ArrayList<>();

    public Friend(FriendDto dto) {
        this.id =new FriendId(dto.getFirstname(), dto.getLastname(), dto.getPseudo());
        relations = dto.getRelations().stream().map(dtoLink -> new Relation(id, dtoLink)).collect(Collectors.toList());
    }

}
