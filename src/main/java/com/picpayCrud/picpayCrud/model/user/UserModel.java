package com.picpayCrud.picpayCrud.model.user;

import com.picpayCrud.picpayCrud.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users") //nome da entidade
@Table(name = "users") //nome da tabela
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //criar o construdor que receba todos os parametros da classe
@EqualsAndHashCode(of = "id") //chave primaria da tabela
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerando de forma incremental
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true) //coluna unica
    private String document; //cpf
    @Column(unique = true) //coluna unica
    private String email;

    private String password;

    private BigDecimal balance; //saldo
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserModel(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.document = data.document();
        this.email = data.email();
    }
}
