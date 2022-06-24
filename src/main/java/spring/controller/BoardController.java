package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.dto.BoardDto;
import spring.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/save")
    public String save() {

        return "board/write"; //파일 명
    }
    @PostMapping("/save")
    @ResponseBody   // 템플릿 아닌 객체 반환
    public String save(BoardDto boardDto ) {
        System.out.println("save");
        boardService.save(boardDto);
        return "board/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
            model.addAttribute("boardlist", boardService.boardDtoList());
        return "board/list";
    }

    @GetMapping("/view/{bno}")
    public String view(@PathVariable("bno") int bno , Model model){
        BoardDto boardDto = boardService.getboard(bno);
        model.addAttribute("board",boardDto); //model = boarddto
        request.getSession().setAttribute("bno", bno);
        return "board/view";
    }
    @GetMapping("/update")
    public String update(){
        return "/board/update";
    }
    @RequestMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute BoardDto boardDto,Model model){
        int bno =  (Integer) request.getSession().getAttribute("bno");

        model.addAttribute("board",boardDto);
        boardDto.setBno( bno );
        boardService.update(boardDto);
        return "board/list";
    }

    @GetMapping("/delete")
    @ResponseBody
    public boolean delete(){
        int bno =  (Integer) request.getSession().getAttribute("bno");

        System.out.println(bno);
        return boardService.delete(bno);
    }


}
