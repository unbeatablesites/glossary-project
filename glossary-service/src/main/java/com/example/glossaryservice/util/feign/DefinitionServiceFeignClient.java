package com.example.glossaryservice.util.feign;
import com.example.glossaryservice.model.Definition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(name = "definition-service")
public interface DefinitionServiceFeignClient {

    @RequestMapping(value = "/definition", method = RequestMethod.POST)
    Definition addDefinition(@RequestBody Definition definition);

    @RequestMapping(value = "/definition/term/{term}", method = RequestMethod.GET)
    List<Definition> getDefinitionsForTerm(@PathVariable String term);
}
