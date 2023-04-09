package com.board.timeline.controller;

import com.board.timeline.domain.Memo;
import com.board.timeline.domain.MemoRepository;
import com.board.timeline.domain.MemoRequestDto;
import com.board.timeline.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor //(** final로 선언된 필드에 대한 객체를 생성해서 자동주입해줌)
@RestController //(** @Controller만 있으면 스프링이 알아서 MemoController빈을 생성해줌
public class MemoController { //(** @RestController는 @Controller와 @ResponseBody가 결합한 어노테이션이다 그래서 메서드에 @ResponseBody를 안적어도됨)

    private final MemoRepository memoRepository; //(**CRUD중 생성,조회,삭제를 위해 Repository가 필요함)
    private final MemoService memoService; //(**CRUD중 update(변경)을 위해 Service가 필요함)

    @PostMapping("/api/memos") //(**클라이언트가 요청할때 보낸 json 데이터를 MemoRequestDto객체에 바로 넣으려면 @RequestBody가 필요하다)
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) { //(** 생성할 메모의 데이터가 필요하고, 이 데이터는 Dto에 저장되어있음)
        Memo memo = new Memo(requestDto); //(**RequestDto를 바탕으로 Memo생성)
        return memoRepository.save(memo); //(**MemoRepository에 Memo 저장)
    }                                     //(**저장한 Memo를 리턴)

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        //return memoRepository.findAllByOrderByModifiedAtDesc(); // (**MemoRepository에서 모든 Memo를 찾는데, 수정된날짜를 기준으로 내림차순(최신순)으로 정렬해서 다 찾아줌)
                                                                  //(**찾아온 모든 Memo를 List에 담아서 리턴)

        // 타임라인서비스가 불러오는 메모 목록의 시간을 조회 시간으로 부터 24시간 이내로만 할때
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start,end);
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) { //(**경로변수로 삭제할 Memo의 id를 받아옴)
        memoRepository.deleteById(id);      //(**MemoRepository에서 Memo삭제)
        return id;
    }                                       //(**삭제한 Memo의 id를 리턴)

    // Memo의 정보를 받아서 DB의 데이터를 변경하는 메소드
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {//(** 변경할 메모의 데이터가 필요하고, 이 데이터는 Dto에 저장되어있음)
        memoService.update(id, requestDto); //(**update는 Service에서 했으므로 memoService.update()를 한다)
        return id;
    }                       //(**변경된 Memo의 id를 리턴)
}
