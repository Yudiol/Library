package com.yudin.librarygit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    @NonNull
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Email
    @NotEmpty(message = "Email should not be empty")
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @NonNull
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
