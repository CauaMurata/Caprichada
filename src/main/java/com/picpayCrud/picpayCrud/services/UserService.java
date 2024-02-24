package com.picpayCrud.picpayCrud.services;

import com.picpayCrud.picpayCrud.domain.user.UserModel;
import com.picpayCrud.picpayCrud.domain.user.UserType;
import com.picpayCrud.picpayCrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    public void saveUser(UserModel userModel){
        this.repository.save(userModel);
    }
}


