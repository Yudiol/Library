package com.yudin.librarygit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Librarian {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    private String password;

    @Email
    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "role")
    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "status")
    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
