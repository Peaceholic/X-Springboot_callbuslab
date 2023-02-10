package com.callbuslab.zaritalk.model.serviceImpl.v1;

import com.callbuslab.zaritalk.base.service.BaseService;
import com.callbuslab.zaritalk.common.HttpHeader;
import com.callbuslab.zaritalk.common.Pagination;
import com.callbuslab.zaritalk.model.dto.LikeDto;
import com.callbuslab.zaritalk.model.entity.Like;
import com.callbuslab.zaritalk.model.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikeService extends BaseService<LikeDto.Base, LikeDto.Base, Like> {

    private final LikeRepository likeRepository;

    @Override
    @Deprecated
    public HttpHeader<LikeDto.Base> create(LikeDto.Base request) {
        Like like = new Like().dtoToEntity(request);
        Like newLike = baseRepository.save(like);
        return null;
    }

    @Override
    public HttpHeader<LikeDto.Base> read(Long id) {
        //id -> repository getOne, getById
        Optional<Like> optional = baseRepository.findById(id);

        return optional
                .map(Like::response)
                .map(HttpHeader::OK).orElseGet(() -> {
                    return HttpHeader.ERROR("[Error] No Data");
                });
    }

    @Override
    public HttpHeader<LikeDto.Base> update(LikeDto.Base request) {
        LikeDto.Base likeRequest = request;

        Optional<Like> optional = baseRepository.findById(likeRequest.getId());

        return optional.map(like -> {
                    like.setUser(likeRequest.getUser())
                            .setBoard(likeRequest.getBoard());
                    return like;
                })
                .map(like -> baseRepository.save(like))
                .map(Like::response)
                .map(HttpHeader::OK)
                .orElseGet(() -> HttpHeader.ERROR("[Error] No Data"));
    }

    @Override
    public HttpHeader delete(Long id) {

        Optional<Like> optional = baseRepository.findById(id);

        // 2. repository -> delete
        return optional.map(Like -> {
                    baseRepository.delete(Like);
                    return HttpHeader.OK();
                })
                .orElseGet(() -> HttpHeader.ERROR("[Error] No Data"));
    }

    @Override
    public HttpHeader<List<LikeDto.Base>> search(Pageable pageable) {
        Page<Like> likes = baseRepository.findAll(pageable);
        List<LikeDto.Base> likeResponseList = likes.stream()
                .map(Like::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(likes.getTotalPages())
                .totalElements(likes.getTotalElements())
                .currentPage(likes.getNumber())
                .currentElements(likes.getNumberOfElements())
                .build();

        return HttpHeader.OK(likeResponseList, pagination);
    }
}
