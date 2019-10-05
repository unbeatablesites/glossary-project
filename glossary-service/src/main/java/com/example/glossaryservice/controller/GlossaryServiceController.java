package com.example.glossaryservice.controller;
import com.example.glossaryservice.model.Definition;
import com.example.glossaryservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class GlossaryServiceController {
    // Properties
    private ServiceLayer serviceLayer;

    // Constructor
    @Autowired
    public GlossaryServiceController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @RequestMapping(value = "/glossary", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Definition addDefinition(@RequestBody @Valid Definition definition) {
        return serviceLayer.addDefinition(definition);
    }

    @RequestMapping(value = "/glossary/term/{term}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Definition> getDefinitionByTerm(@PathVariable String term) {
        return serviceLayer.getDefinitionByTerm(term);
    }
}
