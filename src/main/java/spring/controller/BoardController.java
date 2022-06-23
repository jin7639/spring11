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
            model.addAttribute("boardlist", boardService.boardDtoList());
        return "board/list";
    }

    @GetMapping("/view/{bno}")
    public String view(@PathVariable("bno") int bno , Model model){
        BoardDto boardDto = boardService.getboard(bno);
        model.addAttribute("board",boardDto);
        request.getSession().setAttribute("bno", bno);
        return "board/view";
    }
    @GetMapping("/update")
    public String update(){
        return "update";
    }
    @PutMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute BoardDto boardDto,Model model){
        int bno =  (Integer) request.getSession().getAttribute("bno");
        model.addAttribute("board",boardDto);
        boardDto.setBno( bno );
        boardService.update(boardDto);
        request.getSession().setAttribute("bno", bno);
        return "board/view";
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestParam("bno") int bno){
        return boardService.delete(bno);
    }


}
