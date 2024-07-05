package gr.aueb.cf.project8.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;

@Entity
@Data
public class ElectricityFault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String faultType;
    private String description;
    private String observationTime;
    private String contactInfo;

    @Transient
    private String photoBase64;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setUser(Optional<User> user) {
        if(user.isPresent()) {
            this.user = user.get();
        }
    }
}
