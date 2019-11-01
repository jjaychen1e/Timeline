package com.rhenium.timelinebackend.service;

import com.rhenium.timelinebackend.bean.TimelineItem;
import com.rhenium.timelinebackend.mapper.TimelineItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/1 3:47 下午
 */
@Service
@Transactional
public class TimelineItemService {

    @Resource
    TimelineItemMapper timelineItemMapper;

    public List<TimelineItem> getInitialTimelineItem(int count){
        return timelineItemMapper.getInitialTimelineItem(count);
    }

    public List<TimelineItem> getMoreTimelineItem(int type, int id, int count){
        if (type == 0){
            return timelineItemMapper.getNewTimelineItem(id);
        } else {
            List<TimelineItem> allOldTimelineItems = timelineItemMapper.getOldTimeLineItem(id);
            List<TimelineItem> oldTimelineItems = new ArrayList<>();
            Collections.reverse(allOldTimelineItems);
            for (int i = 0; i < count ; i++){
                oldTimelineItems.add(allOldTimelineItems.get(i));
            }
            return oldTimelineItems;
        }
    }
}
