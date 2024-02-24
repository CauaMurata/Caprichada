package com.picpayCrud.picpayCrud.repositories;

import com.picpayCrud.picpayCrud.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDocument(String document); //buscar usuario por cpf, pode ou nao retornar(optional)

    Optional<User> findUserById(Long id);
}
