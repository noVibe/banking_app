package task.aston.banking_app.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import task.aston.banking_app.pojo.enums.Operation;

import java.time.LocalDateTime;

import static task.aston.banking_app.pojo.enums.Operation.DEPOSIT;
import static task.aston.banking_app.pojo.enums.Operation.WITHDRAW;

@Entity
@Table(name = "transaction_log")
@Data
@NoArgsConstructor
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "operation", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Operation operation;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Column(name = "account_id", nullable = false)
    private long accountId;

    @Column(name = "timestamp")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime dateTime;

    private TransactionLog(long accountId, Operation operation, long amount) {
        this.accountId = accountId;
        this.operation = operation;
        this.amount = amount;
    }
    public static TransactionLog newBalanceWithdrawLog(long accountId, long amount) {
        return new TransactionLog(accountId, WITHDRAW, amount);
    }

    public static TransactionLog newBalanceDepositLog(long accountId, long amount) {
        return new TransactionLog(accountId, DEPOSIT, amount);
    }
}
