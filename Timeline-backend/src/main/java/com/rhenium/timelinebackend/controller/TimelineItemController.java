package com.rhenium.timelinebackend.controller;

import com.rhenium.timelinebackend.bean.TimelineItem;
import com.rhenium.timelinebackend.service.TimelineItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/1 3:49 下午
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class TimelineItemController {

    @Resource
    TimelineItemService timelineItemService;

    @RequestMapping(value = "InitialTimelineItems", method = RequestMethod.GET)
    public List<TimelineItem> getInitialTimelineItem(@RequestParam int count) {
        return timelineItemService.getInitialTimelineItem(count);
    }

    @RequestMapping(value = "TimelineItems", method = RequestMethod.GET)
    public List<TimelineItem> getMoreTimelineItem(@RequestParam int type,
                                                  @RequestParam int id,
                                                  @RequestParam int count) {
        return timelineItemService.getMoreTimelineItem(type, id, count);
    }

    @RequestMapping(value = "uploadItem", method = RequestMethod.POST)
    public int addTimelineItem(@RequestParam String userName,
                               @RequestParam String title,
                               @RequestParam String text,
                               @RequestParam Object file) {
        try {
            return timelineItemService.addTimelineItem(userName, title, text, (MultipartFile) file);
        } catch (Exception e) {
            return timelineItemService.addTimelineItem(userName, title, text);
        }
    }

}
