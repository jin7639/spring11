package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.BoardDao;
import spring.dto.BoardDto;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;
    public void save(BoardDto boardDto) {
        boardDao.save(boardDto);
    }
    public ArrayList<BoardDto> boardDtoList (){
        ArrayList<BoardDto> boardList =  boardDao.boardDtoList();
        System.out.println(boardList);
            return boardList;
    }

    public BoardDto getboard(int bno) {
        System.out.println(bno);
        return boardDao.getboard(bno);
    }

    public boolean update(BoardDto boardDto) {
        return boardDao.update(boardDto.getBno(),boardDto.getBtitle(),boardDto.getBcontent());
    }

    public boolean delete(int bno) {
        return boardDao.delete(bno);

    }
}
