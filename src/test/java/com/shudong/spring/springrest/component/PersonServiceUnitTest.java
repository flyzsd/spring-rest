package com.shudong.spring.springrest.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class PersonServiceUnitTest {
    @InjectMocks
    PersonService personService;

    @Test
    void shouldInjectMocks() {
        assertThat(personService).isNotNull();
    }

    @Nested
    class ObjectToJson {
        @Test
        void shouldReturnJson() {
            Person person = Person.builder().id(UUID.randomUUID().toString()).name("shudong").email("shudong@gmail.com").modifiedOn(Instant.now()).version(1L).xxx("xxx").build();
            String json = personService.fromJavaObjectToJsonObject(person);
            assertThat(json).isNotBlank();
        }

        @Test
        void shouldReturnJsonGivenJavaNull() {
            String json = personService.fromJavaObjectToJsonObject(null);
            assertThat(json).isEqualTo("null");
        }
    }

    @Nested
    class ObjectArrayToJson {
        @Test
        void shouldReturnJsonGivenArray() {
            Person person = Person.builder().id(UUID.randomUUID().toString()).name("shudong").email("shudong@gmail.com").modifiedOn(Instant.now()).version(1L).xxx("xxx").build();
            String json = personService.fromJavaArrayToJsonArray(new Person[]{person});
            assertThat(json).isNotBlank();
        }

        @Test
        void shouldReturnJsonGivenEmptyArray() {
            String json = personService.fromJavaArrayToJsonArray(new Person[0]);
            assertThat(json).isEqualTo("[]");
        }

        @Test
        void shouldReturnJsonGivenNullArray() {
            String json = personService.fromJavaArrayToJsonArray(null);
            assertThat(json).isEqualTo("null");
        }
    }

    @Nested
    class ObjectListToJson {
        @Test
        void shouldReturnJsonGivenList() {
            Person person = Person.builder().id(UUID.randomUUID().toString()).name("shudong").email("shudong@gmail.com").modifiedOn(Instant.now()).version(1L).xxx("xxx").build();
            String json = personService.fromJavaListToJsonArray(Arrays.asList(person));
            assertThat(json).isNotBlank();
        }

        @Test
        void shouldReturnJsonGivenEmptyList() {
            String json = personService.fromJavaListToJsonArray(Collections.emptyList());
            assertThat(json).isEqualTo("[]");
        }

        @Test
        void shouldReturnJsonGivenNullList() {
            String json = personService.fromJavaListToJsonArray(null);
            assertThat(json).isEqualTo("null");
        }
    }

    @Nested
    class JsonToObject {
        @Test
        void shouldReturnObject() {
            String json = "{\"id\":\"6ebfa0ae-34aa-40ff-9ff2-b400d769831e\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:33:25.612Z\"}";
            Person person = personService.fromJsonObjectToJavaObject(json);
            assertThat(person).isNotNull();
            String json2 = personService.fromJavaObjectToJsonObject(person);
            assertThat(json).isEqualToIgnoringWhitespace(json2);
        }

        @Test
        void shouldReturnNullObject() {
            String json = "null";
            Person person = personService.fromJsonObjectToJavaObject(json);
            assertThat(person).isNull();
        }

        @Test
        void shouldReturnObjectGivenJsonStringWithUnknown() {
            String json = "{\"id\":\"6ebfa0ae-34aa-40ff-9ff2-b400d769831e\",\"name\":\"shudong\",\"unknown\":\"xxxyyyy\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:33:25.612Z\"}";
            Person person = personService.fromJsonObjectToJavaObject(json);
            assertThat(person).isNotNull();
            personService.fromJavaObjectToJsonObject(person);
        }

        @Test
        void shouldReturnArray() {
            String json = "[{\"id\":\"c03121be-8de3-47b5-bc14-033e591547dd\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:45:10.847Z\"}]";
            Person[] persons = personService.fromJsonArrayToJavaArray(json);
            assertThat(persons).isNotNull();
            assertThat(persons).hasSize(1);
        }

        @Test
        void shouldReturnList() {
            String json = "[{\"id\":\"c03121be-8de3-47b5-bc14-033e591547dd\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:45:10.847Z\"}]";
            List<Person> persons = personService.fromJsonArrayToJavaList(json);
            assertThat(persons).isNotNull();
            assertThat(persons).hasSize(1);
        }

        @Test
        void shouldReturnList2() {
            String json = "[{\"id\":\"c03121be-8de3-47b5-bc14-033e591547dd\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:45:10.847Z\"}]";
            List<Person> persons = personService.fromJsonArrayToJavaList2(json);
            assertThat(persons).isNotNull();
            assertThat(persons).hasSize(1);
        }
    }

    @Nested
    class JsonToObjectArray {
        @Test
        void shouldReturnArray() {
            String json = "[{\"id\":\"c03121be-8de3-47b5-bc14-033e591547dd\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:45:10.847Z\"}]";
            Person[] persons = personService.fromJsonArrayToJavaArray(json);
            assertThat(persons).isNotNull();
            assertThat(persons).hasSize(1);
        }

        @Test
        void shouldReturnNullArray() {
            String json = "null";
            Person[] persons = personService.fromJsonArrayToJavaArray(json);
            assertThat(persons).isNull();
        }

        @Test
        void shouldReturnEmptyArray() {
            String json = "[]";
            Person[] persons = personService.fromJsonArrayToJavaArray(json);
            assertThat(persons).isEmpty();
            assertThat(persons).hasSize(0);
        }
    }

    @Nested
    class JsonToObjectList1 {
        @Test
        void shouldReturnList() {
            String json = "[{\"id\":\"c03121be-8de3-47b5-bc14-033e591547dd\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:45:10.847Z\"}]";
            List<Person> persons = personService.fromJsonArrayToJavaList(json);
            assertThat(persons).isNotNull();
            assertThat(persons).hasSize(1);
        }

        @Test
        void shouldReturnNullList() {
            String json = "null";
            List<Person> persons = personService.fromJsonArrayToJavaList(json);
            assertThat(persons).isNull();
        }

        @Test
        void shouldReturnEmptyList() {
            String json = "[]";
            List<Person> persons = personService.fromJsonArrayToJavaList(json);
            assertThat(persons).isEmpty();
            assertThat(persons).hasSize(0);
        }
    }

    @Nested
    class JsonToObjectList2 {
        @Test
        void shouldReturnList() {
            String json = "[{\"id\":\"c03121be-8de3-47b5-bc14-033e591547dd\",\"name\":\"shudong\",\"email\":\"shudong@gmail.com\",\"version\":1,\"lastModifiedOn\":\"2018-09-16T09:45:10.847Z\"}]";
            List<Person> persons = personService.fromJsonArrayToJavaList2(json);
            assertThat(persons).isNotNull();
            assertThat(persons).hasSize(1);
        }

        @Test
        void shouldReturnNullList() {
            String json = "null";
            List<Person> persons = personService.fromJsonArrayToJavaList2(json);
            assertThat(persons).isNull();
        }

        @Test
        void shouldReturnEmptyList() {
            String json = "[]";
            List<Person> persons = personService.fromJsonArrayToJavaList2(json);
            assertThat(persons).isEmpty();
            assertThat(persons).hasSize(0);
        }
    }
}
