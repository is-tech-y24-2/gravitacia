package com.kotiki.dto;

import Entities.Cat;
import Entities.Specification.CatColour;

import java.util.*;

public class CatDTO {
    private Long id;
    private String name;
    private CatColour catColour;
    private Long ownerId;
    private String dateOfBirth;
    private Set<Long> friendsId;

    public CatDTO() {
        friendsId = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatColour getBreed() {
        return catColour;
    }

    public void setBreed(CatColour breed) {
        this.catColour = breed;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Set<Long> getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(Set<Cat> friends) {
        for(Cat friend: friends) {
            friendsId.add(friend.getId());
        }
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}