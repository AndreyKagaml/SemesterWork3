package CarDav.Controllers;

import CarDav.DAO.CarDAO;
import CarDav.DAO.OrderDAO;
import CarDav.DAO.UserDAO;
import CarDav.Services.UserService;
import CarDav.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/users")
public class UserController {

   // private UserService userService;

    private UserDAO userDAO;
    private CarDAO carDAO;

    private OrderDAO orderDAO;

    @Autowired
    public UserController(UserDAO userDAO, CarDAO carDAO, OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.carDAO = carDAO;
        this.orderDAO = orderDAO;
    }




//    @GetMapping()
//    public String showAllUsers(Model model) throws SQLException {
//        model.addAttribute("users", userDAO.indexUser());
//        return "client/index";
//    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model, Model car) throws SQLException {
        model.addAttribute("user", userDAO.findOne(id));
      //  model.addAttribute("order", userDAO.showClientsOrder(id));
        model.addAttribute("cars", carDAO.index());
        return "client/show";
    }

    @GetMapping("/client")
    public String showClients(Model model) throws SQLException {
        model.addAttribute("users", userDAO.indexClient());
        return "client/index";
    }

    @GetMapping("/admin")
    public String showAdmin(Model model) throws SQLException {
        model.addAttribute("users", userDAO.indexAdmin());
        return "client/index";
    }




/*
    @GetMapping("/new")
    public String addNewCar(Model model){
        model.addAttribute("NewCar", new Car());
        return "car/new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("NewCar") @Valid Car car,
                       BindingResult bindingResult){
        carDAO.addCar(car);
        return "redirect:/cars";
    }
    */

}
