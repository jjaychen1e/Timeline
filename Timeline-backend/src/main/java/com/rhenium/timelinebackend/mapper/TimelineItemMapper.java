package com.rhenium.timelinebackend.mapper;

import com.rhenium.timelinebackend.bean.TimelineItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/1 3:31 下午
 */
@Mapper
public interface TimelineItemMapper {

    @Select("SELECT * FROM timeline_items LIMIT #{count}")
    List<TimelineItem> getInitialTimelineItem(@Param("count")int count);

    @Select("SELECT * FROM timeline_items WHERE id > #{id}")
    List<TimelineItem> getNewTimelineItem(@Param("id")int id);

    @Select("SELECT * FROM timeline_items WHERE id < #{id}")
    List<TimelineItem> getOldTimeLineItem(@Param("id") int id);
}
