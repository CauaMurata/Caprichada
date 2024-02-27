package com.picpayCrud.picpayCrud.model.transaction;

import com.picpayCrud.picpayCrud.model.user.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
