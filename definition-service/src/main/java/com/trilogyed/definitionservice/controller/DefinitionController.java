package com.trilogyed.definitionservice.controller;

import com.trilogyed.definitionservice.exception.NotFoundException;
import com.trilogyed.definitionservice.models.Definition;
import com.trilogyed.definitionservice.repository.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DefinitionController {
    @Autowired
    DefinitionRepository repo;

    @RequestMapping(value = "/definition/term/{term}", method = RequestMethod.GET)
    public List<Definition> getDefinitionsForTerm(@PathVariable String term) {
        return repo.findDefinitionByTerm(term);
    }

    @RequestMapping(value = "/definition", method = RequestMethod.POST)
    public Definition addDefinition(@RequestBody Definition definition) {
        definition.setId(null);
        return repo.save(definition);
    }

    @RequestMapping(value = "/definition/{id}", method = RequestMethod.GET)
    public Definition getDefinition(@PathVariable int id) {
        Optional<Definition> definition = repo.findById(id);
        if (definition.isPresent()) {
            return definition.get();
        }
        throw new NotFoundException(id);
    }
}
