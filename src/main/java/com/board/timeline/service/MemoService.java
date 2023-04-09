package com.board.timeline.service;

import com.board.timeline.domain.Memo;
import com.board.timeline.domain.MemoRepository;
import com.board.timeline.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor //(** final로 선언된 필드에 대한 객체를 생성해서 자동주입해줌)
@Service    //(** 스프링한테 MemoService가 Service임을 알려주기 위해 적어줌)
public class MemoService { // (** 생성,조회,변경,삭제 중에 변경 기능을 할때 Service가 필요함)

    //(** 스프링이 MemoRepository를 자동주입 해주려면 final로 해주고 @RequiredArgsConstructor를 해줘야함)
    private final MemoRepository memoRepository; //(** Memo를 find해서 찾으려면 MemoRepository에서 찾아야하므로)

    @Transactional //(**update를 할때 DB에 반영이 되야한다고 알려주는 어노테이션)
    public Long update(Long id, MemoRequestDto requestDto) { //(**변경시킬 메모의 id, 변경시킬 메모의 내용을 파라미터로 받음)
        Memo memo = memoRepository.findById(id).orElseThrow( //(**findById를 하는데 없을 경우를 대비해서 orElseThrow를 한다)
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.") //(** 예외 및 예외 메시지를 보여줌)
        );
        memo.update(requestDto); //(** 찾아온 Memo를 변경)
        return memo.getId(); //(** 어떤 Memo가 변경됬는지 변경된 Memo의 id를 리턴)
    }
}
