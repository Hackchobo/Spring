package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name ="게시판", description = "게시판 CRUD")
@RestController
@Validated // 이것을 해줘야 된다.
@RequestMapping("/board") // "/" 이거만 해주면 된다.
public class BoardController {
    private final Logger LOGGER;
    private final BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        LOGGER = LoggerFactory.getLogger(BoardController.class);
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "글등록", description = ""+
            "title(String) : 제목 <br>\n" +
            "ctnt(String) : 내용 <br>\n" +
            "writer(String) : 작성자 <br>")
    public int boardPost(@RequestBody BoardInsDto dto) {
        LOGGER.warn("경고, 글 등록이 됩니다.");
        return service.insBoard(dto);
    }

    @GetMapping
    public List<BoardVo> getBoard(@RequestParam @Min(value = 1, message = "page값은 1이상이어야 합니다. ㅋㅋㅋ") int page
                                    ,@RequestParam(defaultValue = "30") int row) {
        LOGGER.info("page : " + page); // 페이징 처리
        BoardDto dto = new BoardDto();
        dto.setPage(page);
        dto.setRow(row);
        return service.selBoardAll(dto);
    }

    @GetMapping("/{iboard}")
    public BoardDetailVo getBoardById(@PathVariable int iboard) {
        BoardDto dto = new BoardDto();
        dto.setIboard(iboard);
        LOGGER.info(dto.toString());
        return service.selBoardById(dto);
    }

    @PutMapping
    public int upsBoard(@RequestBody BoardDto dto) {
        return service.upsBoard(dto);
    }

    @DeleteMapping("/{iboard}")
    public int delBoard(@PathVariable int iboard) {
        BoardDto dto = new BoardDto();
        dto.setIboard(iboard);
        return service.delBoard(dto);
    }


}
