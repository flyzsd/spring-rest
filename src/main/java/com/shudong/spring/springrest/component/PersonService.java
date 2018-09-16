package com.shudong.spring.springrest.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class PersonService {

    public String fromJavaObjectToJsonObject(Person person) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            String json = mapper.writeValueAsString(person);
            log.info(json);
            return json;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String fromJavaListToJsonArray(List<Person> persons) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            String json = mapper.writeValueAsString(persons);
            log.info(json);
            return json;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String fromJavaArrayToJsonArray(Person[] persons) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            String json = mapper.writeValueAsString(persons);
            log.info(json);
            return json;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Person fromJsonObjectToJavaObject(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Person person = mapper.readValue(json, Person.class);
            return person;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Person[] fromJsonArrayToJavaArray(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Person[] persons = mapper.readValue(json, Person[].class);
            return persons;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Person> fromJsonArrayToJavaList(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            List<Person> persons = mapper.readValue(json, new TypeReference<List<Person>>() {
            });
            return persons;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Person> fromJsonArrayToJavaList2(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            List<Person> persons = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Person.class));
            return persons;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
