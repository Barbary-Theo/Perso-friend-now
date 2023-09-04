package com.friend.now.repository;

import com.friend.now.model.Relation;
import com.friend.now.model.RelationId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RelationRepository extends JpaRepository<Relation, RelationId> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Relation relation WHERE relation.id = :id")
    public void deleteById(@Param("id") RelationId id);

}
