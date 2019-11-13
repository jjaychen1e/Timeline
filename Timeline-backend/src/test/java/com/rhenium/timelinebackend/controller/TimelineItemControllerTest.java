package com.rhenium.timelinebackend.controller;

import com.rhenium.timelinebackend.service.TimelineItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author JJAYCHEN
 * @date 2019/11/12 6:42 下午
 */
@SpringBootTest
@AutoConfigureMockMvc
class TimelineItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TimelineItemService timelineItemService;

    @Test
    public void getInitialTimelineItem() throws Exception {
        mockMvc.perform(get("/InitialTimelineItems").param("count", "5"));
        verify(timelineItemService, times(1)).getInitialTimelineItem(5);
    }

    @Test
    void getMoreTimelineItem() throws Exception {
        mockMvc.perform(get("/TimelineItems").param("type", "0").param("id", "5").param("count", "5"));
        verify(timelineItemService, times(1)).getMoreTimelineItem(0, 5, 5);
    }

    @Test
    void addTimelineItem() throws Exception {
        mockMvc.perform(post("/uploadItem")
                .param("userName", "JJAYCHEN")
                .param("title", "TEST TITLE")
                .param("text", "TEST CONTENT")
                .param("file", ""));
        verify(timelineItemService, times(1)).addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT");


        MockMultipartFile file = new MockMultipartFile("file", "testImage.png", "image", "some xml".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/uploadItem")
                .file(file)
                .param("userName", "JJAYCHEN")
                .param("title", "TEST TITLE")
                .param("text", "TEST CONTENT"))
                .andExpect(status().is(200));

        verify(timelineItemService, times(1)).addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT", file);
    }
}