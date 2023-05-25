package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardInsDto dto); // 영향받은 행의 수가 리턴이 된다. 0이면 무언가가 이상이 있다 라는 것이다.\
    List<BoardVo> selBoardAll(BoardDto dto); // 설렉트용 Vo는 별도로 만들것이다.
    BoardDetailVo selBoardById(BoardDto dto);
    int upsBoard(BoardDto dto);
    int delBoard(BoardDto dto);
}
