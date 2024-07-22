package gr.aueb.cf.project8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode@Table(name="fault")
public class Fault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="location is a required field")
    private String location;

    @NotBlank(message="fault type selection is required")
    private String faultType;

    @NotBlank(message="description is a required field")
    private String description;

    @Column(nullable = false,updatable = false)
    private LocalDateTime observationTime;

    @Email(message = "Invalid email format")
    private String contactInfo;

    @Transient
    private String photoBase64;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate() {
        observationTime = LocalDateTime.now();
    }


}
