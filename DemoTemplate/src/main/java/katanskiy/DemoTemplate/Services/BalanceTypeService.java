package katanskiy.DemoTemplate.Services;

import katanskiy.DemoTemplate.Entities.BalanceType;
import katanskiy.DemoTemplate.Repositories.BalanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceTypeService {
    private BalanceTypeRepository balanceTypeRepository;
    @Autowired
    public void setBalanceTypeRepository(BalanceTypeRepository balanceTypeRepository) {
        this.balanceTypeRepository = balanceTypeRepository;
    }

    public List<BalanceType> findAll() {
        return balanceTypeRepository.findAll();
    }

    public Optional<BalanceType> findById(Long id){ return balanceTypeRepository.findById(id);}
}
