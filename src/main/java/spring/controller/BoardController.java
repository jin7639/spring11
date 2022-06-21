package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.dao.BaordDao;
import spring.dto.BoardDto;

import java.sql.SQLException;


@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/write")
    public String write(){
        return "board/write";
    }
    @PostMapping("/write")
    @ResponseBody
    public boolean write(BoardDto boardDto) throws SQLException, ClassNotFoundException {
        boolean result = BaordDao.save(boardDto);
        return result;
    }
    @GetMapping("/update")
    public String update(){
        return "board/update";
    }
}
