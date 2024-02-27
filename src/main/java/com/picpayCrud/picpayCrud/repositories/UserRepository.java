package com.picpayCrud.picpayCrud.repositories;

import com.picpayCrud.picpayCrud.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findUserByDocument(String document); //buscar usuario por cpf, pode ou nao retornar(optional)

    Optional<UserModel> findUserById(Long id);
}
