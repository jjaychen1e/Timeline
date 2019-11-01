package com.rhenium.timelinebackend.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime postDateTime;
    private String text;
    private String imageUrl;
}
