package com.rhenium.timelinebackend.controller;

import com.rhenium.timelinebackend.service.TimelineItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;

/**
 * @author JJAYCHEN
 * @date 2019/11/12 6:42 下午
 */
@ExtendWith({MockitoExtension.class})
class TimelineItemControllerTest {
    @Mock
    TimelineItemService timelineItemService;

    TimelineItemController timelineItemController;

    @BeforeEach
    void initialize() {
        timelineItemController = new TimelineItemController();
        timelineItemController.timelineItemService = this.timelineItemService;
    }

    @Test
    void getInitialTimelineItem() {
        timelineItemController.getInitialTimelineItem(5);
        verify(timelineItemService, times(1)).getInitialTimelineItem(5);
    }

    @Test
    void getMoreTimelineItem() {
        timelineItemController.getMoreTimelineItem(0, 5, 5);
        verify(timelineItemService, times(1)).getMoreTimelineItem(0, 5, 5);
    }

    @Test
    void addTimelineItem() {
        when(timelineItemService.addTimelineItem(anyString(), anyString(), anyString(), isNull())).thenThrow(new RuntimeException());

        timelineItemController.addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT", null);
        verify(timelineItemService, times(1)).addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT");

        MultipartFile file = mock(MultipartFile.class);
        timelineItemController.addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT", file);
        verify(timelineItemService, times(1)).addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT", file);

    }
}