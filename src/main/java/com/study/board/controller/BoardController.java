package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/board/writepro")
    public String boardWriteDo(Board board){

        boardService.write(board);

        return "boardwrite";
    }

    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";
    }
}
