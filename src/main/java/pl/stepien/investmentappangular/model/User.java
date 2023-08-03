package pl.stepien.investmentappangular.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    @OneToMany
    private List<Investment> investments;
    // TODO
//    private String password; private Role role;



}
