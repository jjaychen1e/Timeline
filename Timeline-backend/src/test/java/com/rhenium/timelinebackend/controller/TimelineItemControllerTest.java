package com.rhenium.timelinebackend.controller;

import com.rhenium.timelinebackend.mapper.TimelineItemMapper;
import com.rhenium.timelinebackend.service.TimelineItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author HavenTong
 * @date 2019/11/11 4:52 下午
 */

@SpringBootTest
@AutoConfigureMockMvc
class TimelineItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TimelineItemService timelineItemService;

    @Test
    void shouldGetInitialItems() throws Exception{
        ResultActions perform = mockMvc.perform(get("/InitialTimelineItems")
                .param("count", "4"));
        perform.andExpect(status().isOk());
        verify(timelineItemService, times(1)).getInitialTimelineItem(4);
    }

    @Test
    void shouldGetOldItems() throws Exception{
        ResultActions perform = mockMvc.perform(get("/TimelineItems")
                                                .param("type","1")
                                                .param("id", "5")
                                                .param("count", "5"));
        perform.andExpect(status().isOk());
        verify(timelineItemService, times(1)).getMoreTimelineItem(1, 5, 5);
    }

    @Test
    void shouldGetNewItems() throws Exception{
        ResultActions perform = mockMvc.perform(get("/TimelineItems")
                .param("type","0")
                .param("id", "7")
                .param("count", "7"));
        perform.andExpect(status().isOk());
        verify(timelineItemService, times(1)).getMoreTimelineItem(0, 7, 7);
    }

    @Test
    void addNewItemsWithoutFile() throws Exception{
        ResultActions perform = mockMvc.perform(post("/uploadItem")
                .param("userName", "user")
                .param("title", "test")
                .param("text", "test case")
                .param("file", ""));
        perform.andExpect(status().isOk());
        verify(timelineItemService, times(1)).addTimelineItem("user", "test", "test case");
    }

    @Test
    void addNewItemsWithFile() throws Exception{
        MockMultipartFile mockFile = new MockMultipartFile("file","mock file".getBytes());
        ResultActions perform = mockMvc.perform(multipart("/uploadItem")
                .file(mockFile)
                .param("userName", "user")
                .param("title", "text")
                .param("text", "text case"));
        perform.andExpect(status().isOk());
        verify(timelineItemService, times(1)).addTimelineItem("user", "text","text case", mockFile);
    }

}