package com.callbuslab.zaritalk.model.entity;

import com.callbuslab.zaritalk.base.entity.BaseEntity;
import com.callbuslab.zaritalk.model.dto.request.UserRequest;
import com.callbuslab.zaritalk.model.dto.response.UserResponse;

import com.callbuslab.zaritalk.model.enums.AccountType;
import com.callbuslab.zaritalk.model.enums.Quit;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;


@Entity
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nickname;

    @Enumerated(EnumType.STRING)
    private AccountType accountType ;

    @NotBlank
    private String accountId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Quit quit;

    @Size(min = 0, max = 20)
    private String name;

    @Email
    private String email;

    private String phoneNumber;

    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdatedAt;

    private int loginFailCount;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    public static UserResponse.Base response(User user) {
        //user -> userApiResponse
        UserResponse.Base userApiResponse = UserResponse.Base.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .accountType(user.getAccountType())
                .accountId(user.getAccountId())
                .password(user.getPassword())
                .quit(user.getQuit())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return userApiResponse;
    }

    public User dtoToEntity(UserRequest.Base request) {
        return User.builder()
                .nickname(request.getNickname())
                .accountType(request.getAccountType())
                .accountId(request.getAccountId())
                .password(request.getPassword())
                .quit(request.getQuit())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
    }

    public User dtoToEntityAndPwdEncoder(UserRequest.Base request, PasswordEncoder passwordEncoder) {
        User user = this.dtoToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(accountType.toString());

        return new UsernamePasswordAuthenticationToken(email, password, Collections.singleton(grantedAuthority));
    }
}
