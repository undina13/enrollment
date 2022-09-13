package ru.undina.enrollment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.undina.enrollment.controller.EnrollmentController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.undina.enrollment.EnrollmentTestData.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EnrollmentTest {
    @Autowired
    EnrollmentController controller;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    void importAllOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/imports")
                        .content(mapper.writeValueAsString(systemItemImportRequest1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void importWrongParentNotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/imports")
                        .content(mapper.writeValueAsString(systemItemImportRequest2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void importWrongParentFile() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/imports")
                        .content(mapper.writeValueAsString(systemItemImportRequest3))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void importDoubleId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/imports")
                        .content(mapper.writeValueAsString(systemItemImportRequest4))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByIdOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/nodes/item2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(item2)));
    }


    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/nodes/1item1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void deleteOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/delete/item1?date=2022-09-13T22:12:01.00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/nodes/item2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/delete/44item1?date=2022-09-13T22:12:01.00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
