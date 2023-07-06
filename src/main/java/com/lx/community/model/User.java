package com.lx.community.model;

import lombok.Data;

@Data
public class User {
    private Long id ;
    private Long account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;
}
