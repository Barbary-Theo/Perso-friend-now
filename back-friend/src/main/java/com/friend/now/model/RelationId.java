package com.friend.now.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationId implements Serializable {

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String pseudo;
    @Column(name = "relation_firstname")
    private String relationFirstname;
    @Column(name = "relation_lastname")
    private String relationLastname;
    @Column(name = "relation_pseudo")
    private String relationPseudo;
    @Column(name = "relation_type")
    private String relationType;

}
