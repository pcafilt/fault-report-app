package gr.aueb.cf.project8.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<ElectricityFault> faults;


}
