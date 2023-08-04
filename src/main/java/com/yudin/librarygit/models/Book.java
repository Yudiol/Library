package com.yudin.spring.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @NotEmpty(message = "The book should has a name")
    @Column(name = "name")
    private String name;

    @NonNull
    @NotEmpty(message = "The book should has a author")
    @Column(name = "author")
    private String author;

    @NonNull
    @Min(value = 1901, message = "min 1901")
    @Column(name = "year_of_production")
    private int yearOfProduction;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_appointment")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAppointment;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person reader;

    @Transient
    private boolean expired;

    public boolean getExpired() {
        if (dateOfAppointment != null) {
            LocalDate localDateTime = new java.sql.Date(dateOfAppointment.getTime()).toLocalDate();
            return LocalDateTime.now().isAfter(localDateTime.plusDays(10).atStartOfDay());
        }
        return false;
    }
}

