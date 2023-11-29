package com.test1.service;

import com.test1.dto.BoardDTO;
import com.test1.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    BoardDTO findByBno(Integer bno);

    List<BoardDTO> findAll();

    Integer register(BoardDTO boardDTO);


}
