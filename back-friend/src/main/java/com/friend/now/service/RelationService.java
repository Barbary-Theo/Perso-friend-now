package com.friend.now.service;

import com.friend.now.model.RelationId;
import com.friend.now.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

    @Autowired
    private RelationRepository relationRepository;


    public void deleteById(RelationId id) {
        relationRepository.deleteById(id);
    }

}
