package katanskiy.DemoTemplate.Controllers;

import katanskiy.DemoTemplate.Dto.BalanceRequest;
import katanskiy.DemoTemplate.Dto.BalanceResponce;
import katanskiy.DemoTemplate.Entities.Balance;
import katanskiy.DemoTemplate.Entities.BalanceType;
import katanskiy.DemoTemplate.Entities.User;
import katanskiy.DemoTemplate.Services.BalanceService;
import katanskiy.DemoTemplate.Services.BalanceTypeService;
import katanskiy.DemoTemplate.Services.UserService;
import katanskiy.DemoTemplate.objects.BalanceComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RootController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    BalanceService balanceService;
    @Autowired
    BalanceTypeService balanceTypeService;

    @GetMapping("/")
    public String index(Model model, Principal principal) throws SQLException {
        if(principal == null){ return "index"; }
        User user = userService.findOneByLogin(principal.getName());
        List<BalanceRequest> allBalancies = balanceService.findByUserId(user);
        Stream<BalanceRequest> stream = allBalancies.stream();
        List<BalanceRequest> balancies = stream.sorted(new BalanceComparator()).limit(5).filter((s) -> s.getBalanceType().getName().contains("FREE")).collect(Collectors.toList());
        model.addAttribute("balance", balancies);
        return "index";
    }

    @GetMapping("/edit")
    public String edit(Model model){
        BalanceRequest balance = new BalanceRequest();
        List<BalanceType> balanceTypes = balanceTypeService.findAll();
        model.addAttribute("balanceTypes", balanceTypes);
        model.addAttribute("balance", balance);
        return "edit_balance";
    }

    @PostMapping("/edit")
    public String saveBalance(@ModelAttribute(name = "balance") BalanceResponce balanceResponce, @RequestParam Map<String, String> params, Principal principal){

        System.out.println(balanceResponce.getCash());
        System.out.println(balanceResponce.getBalanceType());
        Date date = new Date();
        User user = userService.findOneByLogin(principal.getName());
        BalanceType balanceType = balanceResponce.getBalanceType();

        Balance balance = new Balance(user, balanceResponce.getCash(), balanceResponce.getBalanceType(), date);
        balanceService.saveBalance(balance);
        return "balanceSuccess";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) throws SQLException{
        if(principal == null){ return "redirect:/"; }
        User user = userService.findOneByLogin(principal.getName());
        List<BalanceRequest> balancies = balanceService.findByUserId(user);
        model.addAttribute("balance", balancies);
        return "profile";
    }
}
