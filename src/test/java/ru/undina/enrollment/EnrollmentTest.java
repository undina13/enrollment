//package ru.undina.enrollment;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.undina.enrollment.controller.EnrollmentController;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static ru.undina.enrollment.EnrollmentTestData.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class EnrollmentTest {
//    @Autowired
//    EnrollmentController controller;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper mapper;
//
//
//    @Test
//    @DirtiesContext
//    void importAllOk() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/imports")
//                        .content(mapper.writeValueAsString(systemItemImportRequest1))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getByIdOk() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/nodes/item1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//        . andExpect(content().json(mapper.writeValueAsString(item1)));
//    }
//}
