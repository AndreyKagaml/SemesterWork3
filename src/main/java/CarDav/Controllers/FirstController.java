package CarDav.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {

    @Autowired
    public FirstController() {
    }

    @GetMapping("/login")
    public String goToLogin(){
        return "client/entrance";
    }
}
