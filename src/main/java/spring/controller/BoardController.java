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
public class BoardController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    BoardService boardService;
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("list",boardService.getlist());
        return "board/list";
    }
    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    @GetMapping("/update")
    public String update(){
        return "board/update";
    }

    @GetMapping("/view/{bno}")
    public String view(@PathVariable("bno") int bno , Model model){
        BoardDto boardDto = boardService.board(bno);
        model.addAttribute("board",boardDto);
        request.getSession().setAttribute("bno", bno);
        return "board/view";
    }


    @PostMapping("/write")
    public String write(@ModelAttribute BoardDto boardDto,Model model){
        boardService.save(boardDto);
        model.addAttribute("list",boardService.getlist());
        return "board/list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto,Model model){
        int bno =  (Integer) request.getSession().getAttribute("bno");
        boardDto.setBno( bno );
        boardService.update(boardDto);
        request.getSession().setAttribute("bno", bno);
        return "board/view";
    }

    @GetMapping("/delete")
    public String delete( Model model){
        int bno =  (Integer) request.getSession().getAttribute("bno");
        boardService.delete(bno);
        model.addAttribute("list",boardService.getlist());
        return "board/list";
    }

}
