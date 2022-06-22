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
        ArrayList<BoardDto> boardList = new ArrayList<>();
        for (int i = 0; i < boardList.toArray().length; i++){

        String btitle = boardDao.boardDtoList().get(i).getBtitle();
        String bcontent = boardDao.boardDtoList().get(i).getBcontent();
        }
        try{
            return boardDao.boardDtoList();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
