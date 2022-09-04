package com.example.templates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class Test2 {
    @Autowired
    private MockMvc mockMvc;

    // @BeforeEach
    // public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
    //     this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
    //         .apply(new MockMvcRestDocumentationConfigurer(restDocumentation))
    //         .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
    //         .build();
    // }
    
    @Test
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/echo/increament")).andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcRestDocumentation.document("example"));
    }
}
