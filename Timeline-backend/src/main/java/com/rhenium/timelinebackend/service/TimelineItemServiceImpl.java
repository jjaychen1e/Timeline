package com.rhenium.timelinebackend.service;

import com.rhenium.timelinebackend.bean.TimelineItem;
import com.rhenium.timelinebackend.mapper.TimelineItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/1 3:47 下午
 */
@Service
@Transactional
public class TimelineItemServiceImpl implements TimelineItemService {

    @Resource
    TimelineItemMapper timelineItemMapper;

    @Override
    public List<TimelineItem> getInitialTimelineItem(int count) {
        return timelineItemMapper.getInitialTimelineItem(count);
    }

    /**
     * @param type  0 means update, 1 means get earlier messages.
     * @param id    when type == 0, id is the id of latest message
     *              from user's site. when type == 1, id is the id
     *              of the earliest message from user's site.
     * @param count how many messages returned(if there are enough
     *              messages from the database).
     * @return
     */
    @Override
    public List<TimelineItem> getMoreTimelineItem(int type, int id, int count) {
        if (type == 0) {
            return timelineItemMapper.getNewTimelineItem(id);
        } else {
            List<TimelineItem> oldTimelineItems = timelineItemMapper.getOldTimelineItem(id, count);
            return oldTimelineItems;
        }
    }

    @Override
    public int addTimelineItem(String userName, String title, String text) {
        String imageUrl = null;
        TimelineItem timelineItem = new TimelineItem(0, userName, title, LocalDateTime.now(), text, imageUrl);
        return timelineItemMapper.addTimelineItem(timelineItem);
    }

    @Override
    public int addTimelineItem(String userName, String title, String text, MultipartFile file) {
        String imageUrl = saveImageFile(file);
        TimelineItem timelineItem = new TimelineItem(0, userName, title, LocalDateTime.now(), text, imageUrl);
        return timelineItemMapper.addTimelineItem(timelineItem);
    }

    @Override
    public String saveImageFile(MultipartFile file) {
        String pathName = "/data/images/";
        String fName = file.getOriginalFilename();
        pathName += fName;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathName);
            fos.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String imageUrl = "http://152.136.173.30/images/" + fName;
        return imageUrl;
    }
}
