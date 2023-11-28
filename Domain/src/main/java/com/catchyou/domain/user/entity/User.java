package com.catchyou.domain.user.entity;

import com.catchyou.domain.common.BaseTimeEntity;
import com.catchyou.domain.user.enums.Role;
import com.catchyou.domain.user.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Optional;

import static com.catchyou.domain.user.enums.Status.N;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;    //사용자 실명

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status isDeleted = N;

    public void updatePassword(String password) {
        this.password = password;
    }

}
