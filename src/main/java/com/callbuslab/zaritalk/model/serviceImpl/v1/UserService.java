package com.callbuslab.zaritalk.model.serviceImpl.v1;

import com.callbuslab.zaritalk.base.service.BaseService;
import com.callbuslab.zaritalk.common.HttpHeader;
import com.callbuslab.zaritalk.common.Pagination;
import com.callbuslab.zaritalk.model.dto.request.UserRequest;
import com.callbuslab.zaritalk.model.dto.response.UserResponse;
import com.callbuslab.zaritalk.model.entity.User;
import com.callbuslab.zaritalk.model.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService extends BaseService<UserRequest.Base, UserResponse.Base, User> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Deprecated
    public HttpHeader<UserResponse.Base> create(UserRequest.Base request) {
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw new RuntimeException("[Exception] Already Existed User");
        }
        // 1. requestDto -> User
        User user = new User().dtoToEntityAndPwdEncoder(request, passwordEncoder);

        // 2. User 생성
        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return
        return HttpHeader.OK(User.response(newUser));
    }

    @Override
    public HttpHeader<UserResponse.Base> read(Long id) {
        //id -> repository getOne, getById
        Optional<User> optional = baseRepository.findById(id);

        return optional
                .map(User::response)
                .map(HttpHeader::OK).orElseGet(() -> {
                    return HttpHeader.ERROR("[Error] No Data");
                });
    }

    @Override
    public HttpHeader<UserResponse.Base> update(UserRequest.Base request) {
        // 1. data
        UserRequest.Base userRequest = request;

        // 2. id -> user 데이터를 찾고
        Optional<User> optional = baseRepository.findById(userRequest.getId());

        // 3. 데이터 수정
        return optional.map(user -> {
                    user.setNickname(userRequest.getNickname())
                            .setAccountType(userRequest.getAccountType())
                            .setAccountId(userRequest.getAccountId())
                            .setName(userRequest.getNickname())
                            .setPhoneNumber(userRequest.getPhoneNumber())
                            .setEmail(userRequest.getEmail())
                            .setRegisteredAt(userRequest.getRegisteredAt())
                            .setUnregisteredAt(userRequest.getUnregisteredAt());
                    return user;
                })
                .map(user -> baseRepository.save(user))
                .map(User::response)
                .map(HttpHeader::OK)
                .orElseGet(() -> HttpHeader.ERROR("[Error] No Data"));
    }

    @Override
    public HttpHeader delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        // 2. repository -> delete
        return optional.map(user -> {
                    baseRepository.delete(user);
                    return HttpHeader.OK();
                })
                .orElseGet(() -> HttpHeader.ERROR("[Error] No Data"));
    }

    @Override
    public HttpHeader<List<UserResponse.Base>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);
        List<UserResponse.Base> userResponseList = users.stream()
                .map(User::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return HttpHeader.OK(userResponseList, pagination);
    }
}
