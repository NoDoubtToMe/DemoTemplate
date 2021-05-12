package katanskiy.DemoTemplate.Entities;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "balance")
//@NamedQuery(name = "Author.findById", query ="SELECT b from Balance b where b.userId = :id")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "cash")
    @NotNull
    private Integer cash;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bal_type_id")
    private BalanceType balanceType;

    @Column(name = "date")
    @NotNull
    private Date date;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "balance_balance_type",
//            joinColumns = @JoinColumn(name = "balance"),
//            inverseJoinColumns = @JoinColumn(name = "balance_type"))
//    private Collection<BalanceType> balanceType;

    public Balance(){}

    public Balance(User user, Integer cash, BalanceType balanceType, Date date){
        this.user = user;
        this.cash = cash;
        this.balanceType = balanceType;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
