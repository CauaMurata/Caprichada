package com.picpayCrud.picpayCrud.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "users") //nome da entidade
@Table(name = "users") //nome da tabela
@Getter
@Setter
@AllArgsConstructor //criar o construdor que receba todos os parametros da classe
@EqualsAndHashCode(of = "id") //chave primaria da tabela
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerando de forma incremental
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true) //coluna unica
    private String document; //cpf
    @Column(unique = true) //coluna unica
    private String email;
    @Column
    private String password;
    @Column
    private BigDecimal balance; //saldo
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
