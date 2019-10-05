package com.example.glossaryservice.controller;

import com.example.glossaryservice.model.Definition;
import com.example.glossaryservice.service.ServiceLayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GlossaryServiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class GlossaryServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addDefinitionShouldReturnAddedDefinition() {
        Definition inputDefinition = new Definition();
        inputDefinition.setTerm("mudkip");
        inputDefinition.setDefinition("does one like mudkips?");

        //Object to JSON in String
        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputDefinition);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        Definition outputDefinition = new Definition();
        outputDefinition.setId(1);
        outputDefinition.setTerm("mudkip");
        outputDefinition.setDefinition("does one like mudkips?");

        //Object to JSON in String
        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputDefinition);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(service.addDefinition(inputDefinition)).thenReturn(outputDefinition);

        try {
            this.mockMvc.perform(post("/glossary")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addDefinitionMissingTermShouldReturn400() {
        Definition inputDefinition = new Definition();
        inputDefinition.setDefinition("does one like mudkips?");

        //Object to JSON in String
        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputDefinition);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        Definition outputDefinition = new Definition();
        outputDefinition.setId(1);
        outputDefinition.setTerm("mudkip");
        outputDefinition.setDefinition("does one like mudkips?");

        //Object to JSON in String
        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputDefinition);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(service.addDefinition(inputDefinition)).thenReturn(outputDefinition);

        try {
            this.mockMvc.perform(post("/glossary")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addDefinitionBadWordShouldReturn400() {
        Definition inputDefinition = new Definition();
        inputDefinition.setTerm("mudkip");
        inputDefinition.setDefinition("does jerk like mudkips?");

        //Object to JSON in String
        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputDefinition);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(service.addDefinition(inputDefinition)).thenThrow(new IllegalArgumentException());

        try {
            this.mockMvc.perform(post("/glossary")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDefinitionByTermShouldReturnListOfDefinitions() {
        Definition outputDefinition = new Definition();
        outputDefinition.setId(1);
        outputDefinition.setTerm("mudkip");
        outputDefinition.setDefinition("does one like mudkips?");

        List<Definition> definitionList = new ArrayList<>();
        definitionList.add(outputDefinition);

        //Object to JSON in String
        when(service.getDefinitionByTerm(outputDefinition.getTerm())).thenReturn(definitionList);

        List<Definition> listChecker = new ArrayList<>();
        listChecker.addAll(definitionList);

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(listChecker);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        try {
            this.mockMvc.perform(get("/glossary/term/" + outputDefinition.getTerm()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}