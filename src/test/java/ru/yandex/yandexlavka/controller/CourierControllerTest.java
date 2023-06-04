package ru.yandex.yandexlavka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.request.CreateCourierRequest;
import ru.yandex.yandexlavka.service.impl.CourierServiceImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CourierController.class)
public class CourierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CourierServiceImpl courierServiceImpl; //todo

    @Test
    public void testShouldNotCreateWrongCourier() throws Exception {
        var request = new CreateCourierRequest(
                List.of(
                        new CreateCourierDto(
                                null,
                                List.of(1),
                                List.of("")
                        )
                )
        );

        mockMvc.perform(post("/couriers").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

}
