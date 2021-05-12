package katanskiy.DemoTemplate.Services;

import katanskiy.DemoTemplate.Dto.BalanceRequest;
import katanskiy.DemoTemplate.Entities.Balance;
import katanskiy.DemoTemplate.Entities.BalanceType;
import katanskiy.DemoTemplate.Entities.User;
import katanskiy.DemoTemplate.Repositories.BalanceRepository;
import katanskiy.DemoTemplate.Repositories.BalanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {
    private BalanceTypeRepository balanceTypeRepository;
    private BalanceRepository balanceRepository;

    @Autowired
    public void setBalanceTypeRepository(BalanceTypeRepository balanceTypeRepository) {
        this.balanceTypeRepository = balanceTypeRepository;
    }
    @Autowired
    public void setBalanceRepository(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public  List<BalanceRequest> findByUserId(User user) throws SQLException {
        List<BalanceRequest> balance = new ArrayList<BalanceRequest>();
        List<BalanceType> balanceTypes = balanceTypeRepository.findAll();
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=personalarea", "postgres", "admin");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM balance WHERE user_id = ?");
        ps.setLong(1, user.getId());
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            BalanceRequest balanceDto = new BalanceRequest();
            balanceDto.setUser(user);
            balanceDto.setCash(resultSet.getInt("cash"));
            balanceDto.setBalanceType(balanceTypes.get(resultSet.getInt("bal_type_id")-1));
            balanceDto.setDate(resultSet.getString("date"));
            balance.add(balanceDto);
        }
        return balance;
    }

    public void saveBalance(Balance balance){
        balanceRepository.save(balance);
    }
}
