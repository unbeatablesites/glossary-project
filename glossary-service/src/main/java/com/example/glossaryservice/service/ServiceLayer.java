package com.example.glossaryservice.service;
import com.example.glossaryservice.model.Definition;
import com.example.glossaryservice.util.feign.DefinitionServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ServiceLayer {
    // Properties
    private DefinitionServiceFeignClient client;

    // Constructors
    @Autowired
    public ServiceLayer(DefinitionServiceFeignClient client) {
        this.client = client;
    }

    // Methods
    public Definition addDefinition(Definition definition) {
        // Create a list of dirty words
        List<String> dirtyWords = new ArrayList<>();
        dirtyWords.add("darn");
        dirtyWords.add("heck");
        dirtyWords.add("drat");
        dirtyWords.add("jerk");
        dirtyWords.add("butt");

        // Converting the passed definition to a List of strings
        String[] definitionArray = definition.getDefinition().split(" ");
        List<String> definitionList = Arrays.asList(definitionArray);

        boolean isBad = false;
        for (String word: definitionList) {
            word = word.toLowerCase();
            for (String dirtyWord : dirtyWords) {
                if (dirtyWord.equals(word)) {
                    isBad = true;
                }
            }
        }

        if (isBad == true) {
            throw new IllegalArgumentException();
        }

        return client.addDefinition(definition);
    }

    public List<Definition> getDefinitionByTerm(String term) {
        return client.getDefinitionsForTerm(term);
    }
}
