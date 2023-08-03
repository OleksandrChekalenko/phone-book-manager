package com.chelex.phonebook.controller;

import com.chelex.phonebook.TestObjects;
import com.chelex.phonebook.service.KafkaEventProducerService;
import com.chelex.phonebook.service.PersonService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(PersonController.class)
@Import({KafkaEventProducerService.class})
class PersonControllerTest {

    private static final String BASE_PATH = "/api/v1";

    @MockBean
    private PersonService personService;
    @MockBean
    private KafkaEventProducerService kafkaEventProducerService;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Get Persons")
    class GetPersons {

        private static final String FIRST_NAME = "Jonny";
        private static final String FIRST_NAME_PARAM = "first-name";
        private static final String PERSON_UUID_PARAM = "uuid";

        private static final String GET_ALL_PATH = BASE_PATH + "/persons/getAll";
        private static final String GET_PERSON_BY_UUID_PATH = BASE_PATH + "/persons/personByUuid";
        private static final String GET_PERSON_BY_NAME_PATH = BASE_PATH + "/persons";

        @Test
        @SneakyThrows
        void getAll_ValidRequest_ShouldSuccess() {
            Mockito.when(personService.getAll()).thenReturn(List.of(TestObjects.getPersonDto(FIRST_NAME)));

            mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_PATH))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data[0]").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].first-name").value(FIRST_NAME));
//            .andExpect etc

            Mockito.verify(personService).getAll();
        }

        @Test
        @SneakyThrows
        void getPersonByName_ValidParam_ShouldSuccess() {
            Mockito.when(personService.getPersonByFirstName(FIRST_NAME)).thenReturn(TestObjects.getPersonDto(FIRST_NAME));

            mockMvc.perform(MockMvcRequestBuilders.get(GET_PERSON_BY_NAME_PATH).param(FIRST_NAME_PARAM, FIRST_NAME))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data." + FIRST_NAME_PARAM).value(FIRST_NAME));

            Mockito.verify(personService).getPersonByFirstName(FIRST_NAME);
        }

        @Test
        @SneakyThrows
        void getPersonByUuid_ValidRequest_ShouldSuccess() {
            String personUuid = TestObjects.getRandomUuid();
            Mockito.when(personService.getPersonByUuid(personUuid)).thenReturn(TestObjects.getPersonDtoWithUuid(personUuid));

            mockMvc.perform(MockMvcRequestBuilders.get(GET_PERSON_BY_UUID_PATH).param(PERSON_UUID_PARAM, personUuid))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.uuid").value(personUuid));
//            .andExpect etc

            Mockito.verify(personService).getPersonByUuid(personUuid);
        }

        //TODO: add negative case, validation case, throw exception case
    }
}
