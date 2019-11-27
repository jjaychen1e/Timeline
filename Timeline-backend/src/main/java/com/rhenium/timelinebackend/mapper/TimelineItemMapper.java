package com.rhenium.timelinebackend.mapper;

import com.rhenium.timelinebackend.bean.TimelineItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/1 3:31 下午
 */
@Mapper
@Repository
public interface TimelineItemMapper {

    @Select("SELECT * FROM timeline_items ORDER BY id DESC LIMIT #{count}")
    List<TimelineItem> getInitialTimelineItem(@Param("count")int count);

    @Select("SELECT * FROM timeline_items WHERE id > #{id} ORDER BY id DESC")
    List<TimelineItem> getNewTimelineItem(@Param("id")int id);

    @Select("SELECT * FROM timeline_items WHERE id < #{id} ORDER BY id DESC LIMIT #{limit}")
    List<TimelineItem> getOldTimelineItem(@Param("id") int id, @Param("limit") int limit);

    @Insert("INSERT INTO timeline_items (user_name, title, post_date_time, text, image_url)" +
            "VALUES (#{userName}, #{title}, now(), #{text}, #{imageUrl})")
    int addTimelineItem(TimelineItem timelineItem);
}
