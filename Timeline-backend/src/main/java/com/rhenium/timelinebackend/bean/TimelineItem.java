package com.rhenium.timelinebackend.bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author HavenTong
 * @date 2019/11/1 3:19 下午
 */
@Data
public class TimelineItem {
    private Integer id;
    private String userName;
    private String title;
    private LocalDateTime postDateTime;
    private String text;
    private String imageURL;
}
