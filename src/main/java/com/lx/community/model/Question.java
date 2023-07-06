package com.lx.community.model;

import lombok.Data;

@Data
public class Question {
    private Long id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Long creator;
    private Long comment_count;
    private Long view_count ;
    private Long like_count ;
    private String tag;

}
