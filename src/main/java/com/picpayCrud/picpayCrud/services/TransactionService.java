package com.picpayCrud.picpayCrud.services;

import com.picpayCrud.picpayCrud.domain.transaction.Transaction;
import com.picpayCrud.picpayCrud.domain.user.UserModel;
import com.picpayCrud.picpayCrud.dtos.TransactionDTO;
import com.picpayCrud.picpayCrud.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate; // classe que o spring oferece para fazer comunicações http entre serviços

    public void createTransaction(TransactionDTO transaction) throws Exception {
        UserModel sender = this.userService.findUserById(transaction.senderId());
        UserModel receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!this.authorizeTransaction(sender, transaction.value())) {
            throw new Exception("Transação não autorizada");

        }

        Transaction newtransaction = new Transaction();
        newtransaction.setAmount(transaction.value());
        newtransaction.setSender(sender);
        newtransaction.setReceiver(receiver);
        newtransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value())); //balance = balance - valor
        receiver.setBalance(receiver.getBalance().add(transaction.value())); // balance = balance + valor

        //salvar os dados
        repository.save(newtransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(UserModel sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }

}
