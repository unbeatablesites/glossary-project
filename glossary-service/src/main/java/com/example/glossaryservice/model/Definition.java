package com.example.glossaryservice.model;
import java.util.Objects;

public class Definition {
    // Properties
    private Integer id;
    private String term;
    private String definition;

    // Constructors
    public Definition() {

    }

    public Definition(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public Definition(Integer id, String term, String definition) {
        this.id = id;
        this.term = term;
        this.definition = definition;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    // equals, hashCode(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Definition that = (Definition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(term, that.term) &&
                Objects.equals(definition, that.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, term, definition);
    }

    @Override
    public String toString() {
        return "Definition{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
