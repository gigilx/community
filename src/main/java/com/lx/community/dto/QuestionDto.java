package com.lx.community.dto;

import com.lx.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Long creator;
    private Long comment_count;
    private Long view_count;
    private Long like_count;
    private String tag;
    private User user ;
}
