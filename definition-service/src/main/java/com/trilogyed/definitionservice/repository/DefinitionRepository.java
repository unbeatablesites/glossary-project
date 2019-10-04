package com.trilogyed.definitionservice.repository;

import com.trilogyed.definitionservice.models.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Integer> {
    List<Definition> findDefinitionByTerm(String term);
}
