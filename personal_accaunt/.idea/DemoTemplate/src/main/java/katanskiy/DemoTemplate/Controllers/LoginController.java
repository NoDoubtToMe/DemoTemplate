package katanskiy.DemoTemplate.Controllers;

import katanskiy.DemoTemplate.Entities.User;
import katanskiy.DemoTemplate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "autorizationPage";
    }

    @GetMapping("/autorize_confirm")
    public String autorizeConfirm(@Valid @ModelAttribute(name = "user") User user,
                                  BindingResult bindingResult,
                                  @RequestParam Map<String, String> params,
                                  Model model){

        if (bindingResult.hasErrors()) { return "autorizationPage"; }
        if(userService.isUserExists(params.get("login"))){
            bindingResult.rejectValue("login", "error.login", "Данный логин занят. Укажите другой уникальный логин.");
            return "autorizationPage";
        }
        String bcryptPass = bCryptPasswordEncoder.encode(params.get("password"));
        User saveUser = new User(params.get("login"), bcryptPass);
        userService.saveUser(saveUser);
        return "autorizeConfirm";
    }
}
