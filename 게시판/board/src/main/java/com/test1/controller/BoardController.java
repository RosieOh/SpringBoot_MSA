package com.test1.controller;

import com.test1.dto.BoardDTO;
import com.test1.entity.Board;
import com.test1.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService boardService;


    @GetMapping("list.do")
    @ResponseBody
    public List<BoardDTO> listAll() {
        List<BoardDTO> boardList = boardService.findAll();
        return boardList;
    }

    @GetMapping("read.do")
    @ResponseBody
    public BoardDTO findByBno(Integer bno) {
        BoardDTO board = boardService.findByBno(bno);
        return board;
    }

    @GetMapping("write.do")
    public String boardForm() {
        return "board/write";
    }

    @PostMapping("write.do")
    @ResponseBody
    public Integer boardWrite(@Valid BoardDTO boardDTO) {
        return boardService.register(boardDTO);

    }

    // 애플리케이션에서 어떤 언어든지 받을 수 있는 공통 통로 = API


}
