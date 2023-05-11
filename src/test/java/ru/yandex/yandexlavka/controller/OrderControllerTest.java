package ru.yandex.yandexlavka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.request.CreateOrderRequest;
import ru.yandex.yandexlavka.service.impl.OrderServiceImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void testShouldNotCreateWrongOrder() throws Exception {
        var request = new CreateOrderRequest(
                List.of(
                        new CreateOrderDto(
                                null,
                                1,
                                List.of(""),
                                10
                        )
                )
        );

        mockMvc.perform(post("/orders").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
