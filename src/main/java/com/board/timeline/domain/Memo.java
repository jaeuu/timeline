package com.board.timeline.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id; // (** Memo클래스는 @Entity를 적어서 테이블임을 나타내므로 Memo클래스의 필드는 테이블의 컬럼이 된다)

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    public Memo(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void update(MemoRequestDto requestDto) { //(**MemoRequestDto의 내용에 따라 Memo의 내용 변경)
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
