package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.dto.BoardDto;
import spring.service.BoardService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;


    @GetMapping("/save")
    public String save() {

        return "board/write";
    }
    /////////////////////////////////////////2. service 매핑 ///////////////////////////////////////////////
    //1. C
    @PostMapping("/save")
    @ResponseBody   // 템플릿 아닌 객체 반환
    public String save(BoardDto boardDto ) {
        System.out.println("save");
        boardService.save(boardDto);
        return "board/write";
    }

    @GetMapping("/list")
    public String list(Model model) {
            model.addAllAttributes("btitle",boardService.boardDtoList());
            model.addAllAttributes("bcontent",boardService.boardDtoList())
        return "board/list";
    }
//    @GetMapping("/getboardlist")
//    public void getboardlist(HttpServletResponse response) {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application.json");
//            response.getWriter().print(boardService.getboardlist());
//            out.println(변수명);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }


}
