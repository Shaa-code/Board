package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/board/writepro")
    public String boardWriteDo(Board board, Model model, MultipartFile file) throws Exception{

        boardService.write(board,file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("url","/board/lists");

        return "message";
    }

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    @GetMapping("board/lists")
    public String boardList(Model model,@PageableDefault(page = 0, size = 10, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("list", boardService.boardList(pageable));

        return "boardlist";
    }

    @GetMapping("board/view")
    public String boardView(Model model, Integer id) {
        model.addAttribute("article", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/lists";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("article",boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        model.addAttribute("message","수정이 완료되었습니다.");
        model.addAttribute("url","/board/lists");

        return "message";
    }
}