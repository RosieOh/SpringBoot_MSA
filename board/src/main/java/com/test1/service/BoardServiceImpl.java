package com.test1.service;

import com.test1.dto.BoardDTO;
import com.test1.entity.Board;
import com.test1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final ModelMapper modelMapper;

    @Override
    public BoardDTO findByBno(Integer bno) {
        Optional<Board> result = boardRepository.findById(bno);
        BoardDTO dto = modelMapper.map(result, BoardDTO.class);
        // 한줄로도 가능 = return modelMapper.map(result, BoardDTO.class);
        return dto;
    }

    /*
    자바스크립트의 람다식과 유사
    */

    @Override
    public List<BoardDTO> findAll() {
        List<Board> lst = boardRepository.findAll();
        // 리스트로 부터 다시 데이터를 받아 다시 리스트로 던져줌
        // board -> BoardDTO.class 끝까지 하나씩 반복
        List<BoardDTO> boardList = lst.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
        /*
        for(Board board:lst) {

        }

        */

        return boardList;
    }

    @Override
    public Integer register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Integer bno = boardRepository.save(board).getBno();
        return bno;
    }
}
