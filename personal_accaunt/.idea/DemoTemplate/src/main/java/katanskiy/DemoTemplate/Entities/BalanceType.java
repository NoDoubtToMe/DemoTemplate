package katanskiy.DemoTemplate.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "balance_type")
public class BalanceType {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @OneToMany(mappedBy = "balanceType", fetch = FetchType.EAGER)
        private List<Balance> balances;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public List<Balance> getBalances() {
                return balances;
        }

        public void setBalances(List<Balance> balances) {
                this.balances = balances;
        }

//        @ManyToMany(fetch = FetchType.EAGER)
//        @JoinTable(name = "balance_balance_type",
//                joinColumns = @JoinColumn(name = "balance_type"),
//                inverseJoinColumns = @JoinColumn(name = "balance"))
//        private Collection<Balance> balances;
}
