package com.rhenium.timelinebackend.service;

import com.rhenium.timelinebackend.bean.TimelineItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/27 8:09 下午
 */
public interface TimelineItemService {
    List<TimelineItem> getInitialTimelineItem(int count);

    List<TimelineItem> getMoreTimelineItem(int type, int id, int count);

    int addTimelineItem(String userName, String title, String text);

    int addTimelineItem(String userName, String title, String text, MultipartFile file);
}
