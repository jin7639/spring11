package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.dao.BaordDao;
import spring.dto.BoardDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private HttpServletRequest request;


    //4. 게시물 쓰기 페이지
    @GetMapping("/save")
    public String save() {

        return "board/write";
    }
    /////////////////////////////////////////2. service 매핑 ///////////////////////////////////////////////
    //1. C
    @PostMapping("/save")
    @ResponseBody   // 템플릿 아닌 객체 반환
    public String save(BoardDto boardDto ) throws SQLException, ClassNotFoundException {
        System.out.println("save");
        BaordDao.save(boardDto);
        return "board/write";
    }
}
