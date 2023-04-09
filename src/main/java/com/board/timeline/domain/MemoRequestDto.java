package com.board.timeline.domain;

import lombok.Getter;

@Getter //(** private으로 선언된 필드를 가져오기 위한 Getter)
public class MemoRequestDto { //(** 예를들어 수정요청이 오면 그 내용(누가 작성했고, 내용이 무엇인지)을 가지고 있는 객체, 또는 생성할 메모의 데이터를 가지고 있는 객체)
    private String username;
    private String contents;
}
