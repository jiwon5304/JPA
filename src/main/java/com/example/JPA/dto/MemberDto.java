package com.example.JPA.dto;

import lombok.Data;

// 엔티티에는 @Data 를 쓰지 않는다.
@Data
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
