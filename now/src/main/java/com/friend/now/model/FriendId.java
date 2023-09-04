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
public class FriendId implements Serializable {

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String pseudo;

}
