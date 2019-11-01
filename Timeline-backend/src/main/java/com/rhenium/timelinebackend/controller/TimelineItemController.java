package com.rhenium.timelinebackend.controller;

import com.rhenium.timelinebackend.bean.TimelineItem;
import com.rhenium.timelinebackend.service.TimelineItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/1 3:49 下午
 */
@RestController
@RequestMapping("/")
public class TimelineItemController {

    @Resource
    TimelineItemService timelineItemService;

    @RequestMapping(value = "InitialTimelineItems", method = RequestMethod.GET)
    public List<TimelineItem> getInitialTimelineItem(@RequestParam int count){
        return timelineItemService.getInitialTimelineItem(count);
    }

    @RequestMapping(value = "TimelineItems", method = RequestMethod.GET)
    public List<TimelineItem> getMoreTimelineItem(@RequestParam int type,
                                                 @RequestParam int id,
                                                 @RequestParam int count){
        return timelineItemService.getMoreTimelineItem(type, id, count);
    }




}
