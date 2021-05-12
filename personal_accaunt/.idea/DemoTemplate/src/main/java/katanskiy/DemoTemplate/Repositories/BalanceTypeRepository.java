package katanskiy.DemoTemplate.Repositories;

import katanskiy.DemoTemplate.Entities.BalanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceTypeRepository extends JpaRepository<BalanceType, Long> {
}
