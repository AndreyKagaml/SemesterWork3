package CarDav.Controllers;

import CarDav.DAO.CarDAO;
import CarDav.DAO.OrderDAO;
import CarDav.DAO.UserDAO;
import CarDav.models.Car;
import CarDav.models.Category;
import CarDav.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private CarDAO carDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO, UserDAO userDAO, CarDAO carDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.carDAO = carDAO;
    }

    @GetMapping()
    public String showAllOrders(Model model, Model admin) throws SQLException {
        model.addAttribute("orders", orderDAO.index());
        admin.addAttribute("admin", userDAO.indexAdmin());
        return "order/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("order", orderDAO.show(id));
        return "order/show";
    }

    @GetMapping("/new")
    public String addNewOrder(Model model, Model modelCat, Model modelUser) throws SQLException {

        Order newOrder = new Order();
        model.addAttribute("NewOrder", newOrder);
        modelCat.addAttribute("Cars", carDAO.indexAvailable());
        modelUser.addAttribute("Clients", userDAO.indexClient());

        return "order/new";
    }

    @PostMapping()
    public String saveToDB(@ModelAttribute("NewOrder") Order order){
        orderDAO.addOrder(order);
        return "redirect:/users/client";
    }

    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable("id") int id, Model model, Model statuses) throws SQLException {
        model.addAttribute("order", orderDAO.show(id));
        statuses.addAttribute("types", orderDAO.indexStatusTypes());
        return "order/edit";
    }

    @PatchMapping("/{id}")
    public String updateOrder(@PathVariable("id") int id, @ModelAttribute("car") Order order){
        orderDAO.updateOrder(id, order);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderDAO.deleteOrder(id);
        return "redirect:/orders";
    }
/*
    @PostMapping()
    public String saveToDB(@ModelAttribute("NewCar") Car car){
        carDAO.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("car", carDAO.show(id));
        return "car/edit";
    }

    @PatchMapping("/{id}")
    public String updateCar(@PathVariable("id") int id, @ModelAttribute("car") Car car){
        carDAO.updateCar(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") int id){
        carDAO.deleteCar(id);
        return "redirect:/cars";
    }
*/

}
