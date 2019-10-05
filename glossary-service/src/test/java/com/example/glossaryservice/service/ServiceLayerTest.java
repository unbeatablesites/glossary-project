package com.example.glossaryservice.service;

import com.example.glossaryservice.model.Definition;
import com.example.glossaryservice.util.feign.DefinitionServiceFeignClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    private ServiceLayer service;
    private DefinitionServiceFeignClient client;

    @Before
    public void setUp() throws Exception {
        setupClientMock();

        service = new ServiceLayer(client);
    }

    @Test
    public void addDefinition() {
        Definition definition = new Definition();
        definition.setTerm("mudkip");
        definition.setDefinition("does one like mudkips?");

        Definition definition1 = new Definition();
        definition1.setId(1);
        definition1.setTerm("mudkip");
        definition1.setDefinition("does one like mudkips?");

        definition = service.addDefinition(definition);
        assertEquals(definition1, definition);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addDefinitionBadWordExpectException() {
        Definition definition = new Definition();
        definition.setTerm("mudkip");
        definition.setDefinition("does jerk like mudkips?");

        service.addDefinition(definition);
    }

    @Test
    public void getDefinitionByTerm() {
        Definition definition1 = new Definition();
        definition1.setId(1);
        definition1.setTerm("mudkip");
        definition1.setDefinition("does one like mudkips?");

        List<Definition> definitionList = new ArrayList<>();
        definitionList.add(definition1);

        List<Definition> definitionList1 = service.getDefinitionByTerm(definition1.getTerm());

        assertEquals(definitionList1, definitionList);
    }

    private void setupClientMock() {
        client = mock(DefinitionServiceFeignClient.class);

        Definition definition = new Definition();
        definition.setTerm("mudkip");
        definition.setDefinition("does one like mudkips?");

        Definition definition1 = new Definition();
        definition1.setId(1);
        definition1.setTerm("mudkip");
        definition1.setDefinition("does one like mudkips?");

        List<Definition> definitionList = new ArrayList<>();
        definitionList.add(definition1);

        doReturn(definition1).when(client).addDefinition(definition);
        doReturn(definitionList).when(client).getDefinitionsForTerm(definition.getTerm());
    }
}