package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.BoardDao;
import spring.dto.BoardDto;

import java.util.ArrayList;

@Service
public class BoardService {

    BoardDao boardDao = new BoardDao();

    public boolean save(BoardDto boardDto){
        boardDao.save(boardDto);
        return true;
    }

    public ArrayList<BoardDto> getlist(){
        ArrayList<BoardDto> list = boardDao.list();
        return list;
    }
    public BoardDto board( int bno ) {
        return boardDao.board(bno);
    }
    public boolean delete(int bno){
        return boardDao.delete(bno);
    }

    public boolean update(BoardDto boardDto){
        return boardDao.update(boardDto.getBno(),boardDto.getBtitle(),boardDto.getBcontent());
    }
}
