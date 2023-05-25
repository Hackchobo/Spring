package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper mapper;

    @Autowired
    public BoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    public int insBoard(BoardInsDto dto){
        return mapper.insBoard(dto);//mybatis에서 구현부가 없어고 bean등록을 하면서 구현부를 만들어 준다.
    }

    public List<BoardVo> selBoardAll(BoardDto dto) {
        int ROW_PER_PAGE = dto.getRow();
        int startIdx = (dto.getPage()-1) * ROW_PER_PAGE;
        dto.setRowLen(ROW_PER_PAGE);
        dto.setStartIdx(startIdx);
        return mapper.selBoardAll(dto);
    }

    public BoardDetailVo selBoardById(BoardDto dto) {
        return mapper.selBoardById(dto);
    }

    public int upsBoard(BoardDto dto) {
        return mapper.upsBoard(dto);
    }

    public int delBoard(BoardDto dto) {
        return mapper.delBoard(dto);
    }
}
