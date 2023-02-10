package com.callbuslab.zaritalk.model.serviceImpl.v1;

import com.callbuslab.zaritalk.base.service.BaseService;
import com.callbuslab.zaritalk.common.HttpHeader;
import com.callbuslab.zaritalk.common.Pagination;
import com.callbuslab.zaritalk.model.dto.BoardDto;
import com.callbuslab.zaritalk.model.entity.Board;
import com.callbuslab.zaritalk.model.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService extends BaseService<BoardDto.Base, BoardDto.Base, Board> {

    private final BoardRepository boardRepository;

    @Override
    @Deprecated
    public HttpHeader<BoardDto.Base> create(BoardDto.Base request) {
        Board board = new Board().dtoToEntity(request);
        Board newBoard = baseRepository.save(board);
        return null;
    }

    @Override
    public HttpHeader<BoardDto.Base> read(Long id) {
        //id -> repository getOne, getById
        Optional<Board> optional = baseRepository.findById(id);

        return optional
                .map(Board::response)
                .map(HttpHeader::OK).orElseGet(() -> {
                    return HttpHeader.ERROR("[Error] No Data");
                });
    }

    @Override
    public HttpHeader<BoardDto.Base> update(BoardDto.Base request) {
        BoardDto.Base boardRequest = request;

        Optional<Board> optional = baseRepository.findById(boardRequest.getId());

        return optional.map(board -> {
                    board.setTitle(boardRequest.getTitle())
                            .setContent(boardRequest.getContent())
                            .setWriter(boardRequest.getWriter());
                    return board;
                })
                .map(board -> baseRepository.save(board))
                .map(Board::response)
                .map(HttpHeader::OK)
                .orElseGet(() -> HttpHeader.ERROR("[Error] No Data"));
    }

    @Override
    public HttpHeader delete(Long id) {

        Optional<Board> optional = baseRepository.findById(id);

        // 2. repository -> delete
        return optional.map(Board -> {
                    baseRepository.delete(Board);
                    return HttpHeader.OK();
                })
                .orElseGet(() -> HttpHeader.ERROR("[Error] No Data"));
    }

    @Override
    public HttpHeader<List<BoardDto.Base>> search(Pageable pageable) {
        Page<Board> boards = baseRepository.findAll(pageable);
        List<BoardDto.Base> boardResponseList = boards.stream()
                .map(Board::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(boards.getTotalPages())
                .totalElements(boards.getTotalElements())
                .currentPage(boards.getNumber())
                .currentElements(boards.getNumberOfElements())
                .build();

        return HttpHeader.OK(boardResponseList, pagination);
    }
}
