package com.picpayCrud.picpayCrud.services;

import com.picpayCrud.picpayCrud.model.user.UserModel;
import com.picpayCrud.picpayCrud.model.user.UserType;
import com.picpayCrud.picpayCrud.dtos.UserDTO;
import com.picpayCrud.picpayCrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service //indicar para o spring que é uma classe de serviço para fazer as injeçoes de dependencias
public class UserService {
    @Autowired
    private UserRepository repository; //acesso ao UserRepository para manipular

    public void validateTransaction(UserModel sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public UserModel findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public UserModel createUser(UserDTO data) {
        UserModel newUser = new UserModel(data);
        this.saveUser(newUser);
        return newUser;
    }

    public String inactivateUserById(Long id) throws Exception {
        Optional<UserModel> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            user.setUserType(UserType.INACTIVE);
            repository.save(user);
            return "Usuário com ID " + id + " foi inativado com sucesso.";
        } else {
            throw new Exception("Usuário não encontrado");
        }
    }



    public UserModel updateUser(Long id, UserModel newUser) throws Exception {
        Optional<UserModel> existingUserOptional = repository.findById(id);
        if (existingUserOptional.isPresent()) {
            UserModel existingUser = existingUserOptional.get();

            if (newUser.getFirstName() != null) {
                existingUser.setFirstName(newUser.getFirstName());
            }
            if (newUser.getLastName() != null) {
                existingUser.setLastName(newUser.getLastName());
            }
            if (newUser.getDocument() != null) {
                existingUser.setDocument(newUser.getDocument());
            }
            if (newUser.getEmail() != null) {
                existingUser.setEmail(newUser.getEmail());
            }
            if (newUser.getPassword() != null) {
                existingUser.setPassword(newUser.getPassword());
            }
            if (newUser.getBalance() != null) {
                existingUser.setBalance(newUser.getBalance());
            }

            // Salvar usuário atualizado
            return repository.save(existingUser);
        } else {
            throw new Exception("Usuário não encontrado");
        }
    }

    public List<UserModel> getAllUsers() {
        return this.repository.findAll();
    }

    public void saveUser(UserModel userModel) {
        this.repository.save(userModel);
    }
}


