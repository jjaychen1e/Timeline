package com.rhenium.timelinebackend.service;

import com.rhenium.timelinebackend.bean.TimelineItem;
import com.rhenium.timelinebackend.mapper.TimelineItemMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author JJAYCHEN
 * @date 2019/11/12 3:30 下午
 */
@SpringBootTest
class TimelineItemServiceImplTest {

    /**
     * @Mock 注解只能加在全局变量上
     */
    @Mock
    TimelineItemMapper timelineItemMapper;

    @InjectMocks
    TimelineItemServiceImplFake timelineItemService;

    @Test
    /**
     * 单纯地依赖 timelineItemMapper 从数据库中取出数据并返回，
     * 所以只需要测试是否调用了 timelineItemMapper 的函数即可。
     */
    void getInitialTimelineItem() {
        /** 好像不需要知道返回值，所以不需要打桩 */
        timelineItemService.getInitialTimelineItem(5);
        verify(timelineItemMapper, times(1)).getInitialTimelineItem(5);

        /** 被打桩的代码不考虑在内 */
        verifyNoMoreInteractions(timelineItemMapper);
    }

    @Test
    /**
     * 若 type == 0, 单纯地依赖 timelineItemMapper 从数据库
     * 中取出数据并返回，所以只需要测试是否调用了 timelineItemMapper
     * 的函数即可。若 type == 1, 单纯地依赖 timelineItemMapper
     * 从数据库中取出数据并返回，所以只需要测试是否调用了 timelineItemMapper.
     */
    void getMoreTimelineItem() {
        timelineItemService.getMoreTimelineItem(0, 5, 5);
        verify(timelineItemMapper, times(1)).getNewTimelineItem(5);

        timelineItemService.getMoreTimelineItem(1, 5, 5);
        verify(timelineItemMapper, times(1)).getOldTimelineItem(5, 5);

        verifyNoMoreInteractions(timelineItemMapper);
    }

    @Test
    /**
     * 如果添加的 timelineItem 没有图片，直接调用 timelineItemMapper
     * 插入数据库
     */
    void addTimelineItemWithoutImage() {
        timelineItemService.addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT");

        ArgumentCaptor<TimelineItem> timelineItemArgumentCaptor = ArgumentCaptor.forClass(TimelineItem.class);
        verify(timelineItemMapper, times(1)).addTimelineItem(timelineItemArgumentCaptor.capture());

        assertEquals("JJAYCHEN", timelineItemArgumentCaptor.getValue().getUserName());
        assertEquals("TEST TITLE", timelineItemArgumentCaptor.getValue().getTitle());
        assertEquals("TEST CONTENT", timelineItemArgumentCaptor.getValue().getText());
        assertNull(timelineItemArgumentCaptor.getValue().getImageUrl());
    }

    @Test
    /** 如果添加的 timelineItem 包括图片，需要把图片存在
     * 本地文件系统中，生成一个 URL，再存入数据库。这里需要
     * 断开与本地文件系统的连接。
     */
    void addTimelineItemWithImage() {
        MultipartFile file = mock(MultipartFile.class);
        timelineItemService.addTimelineItem("JJAYCHEN", "TEST TITLE", "TEST CONTENT", file);

        ArgumentCaptor<TimelineItem> timelineItemArgumentCaptor = ArgumentCaptor.forClass(TimelineItem.class);
        verify(timelineItemMapper, times(1)).addTimelineItem(timelineItemArgumentCaptor.capture());

        assertEquals("JJAYCHEN", timelineItemArgumentCaptor.getValue().getUserName());
        assertEquals("TEST TITLE", timelineItemArgumentCaptor.getValue().getTitle());
        assertEquals("TEST CONTENT", timelineItemArgumentCaptor.getValue().getText());
        assertEquals("http://152.136.173.30/images/testImage.png", timelineItemArgumentCaptor.getValue().getImageUrl());
    }
}

class TimelineItemServiceImplFake extends TimelineItemServiceImpl {
    @Override
    public String saveImageFile(MultipartFile file) {
        return "http://152.136.173.30/images/testImage.png";
    }
}