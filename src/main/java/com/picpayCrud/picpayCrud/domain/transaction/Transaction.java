package com.picpayCrud.picpayCrud.domain.transaction;

import com.picpayCrud.picpayCrud.domain.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount; //valor da transação
    @ManyToOne
    @JoinColumn(name="sender_id")
    private UserModel sender;
    @ManyToOne
    @JoinColumn(name="receiver_id")
    private UserModel receiver;
    private LocalDateTime timeStamp;

}
