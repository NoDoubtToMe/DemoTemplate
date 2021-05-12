package katanskiy.DemoTemplate.Repositories;

import katanskiy.DemoTemplate.Entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}

