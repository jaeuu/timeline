package com.board.timeline.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// (** JpaRepository< > 안에는 각각 엔티티 클래스 이름과 ID 필드 타입이 지정된다.
// (**JpaRepository를 상속함으로써 JpaRepository에 작성된 메서드를 갖다 쓸수있다. findAll(), delete(), findByID(), save() 등등.
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // 타임라인서비스가 불러오는 메모 목록의 시간을 조회 시간으로 부터 24시간 이내로만 할때
    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);

    //List<Memo> findAllByOrderByModifiedAtDesc(); // (**수정된날짜를 기준으로 내림차순(최신순)으로 정렬해서 다 찾아줌)



}
