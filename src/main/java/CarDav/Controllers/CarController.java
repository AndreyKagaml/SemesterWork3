package CarDav.Controllers;

import CarDav.DAO.CarDAO;
import CarDav.DAO.UserDAO;
import CarDav.models.Car;
import CarDav.models.Category;
import CarDav.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarDAO carDAO;
    private UserDAO userDAO;

    @Autowired
    public CarController(CarDAO carDAO, UserDAO userDAO) {
        this.carDAO = carDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/all")
    public String showAllCars(Model model) throws SQLException {
        model.addAttribute("cars", carDAO.index());
        return "car/index";
    }

    @GetMapping("/available")
    public String showAvailableCars(Model model) throws SQLException {
        model.addAttribute("cars", carDAO.indexAvailable());
        return "car/index";
    }



    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) throws SQLException {
        model.addAttribute("car", carDAO.show(id));

        return "car/show";
    }
/*
    Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        if(bookOwner.isPresent()){
        model.addAttribute("owner", bookOwner.get());
    }
        else
    {
        model.addAttribute("people", peopleDAO.allPersons());
    }

*/
    @GetMapping("/new")
    public String addNewCar(Model model, Model modelCat, @ModelAttribute("category") Category selectedCategory) throws SQLException {
            Car newCar = new Car();
        model.addAttribute("NewCar", newCar);
        modelCat.addAttribute("Categories", carDAO.indexCategories());
       // carDAO.setNewCarCategory(newCar.getId_car(), selectedCategory);
      //  model2.addAttribute("Category", carDAO.showCategory())
        return "car/new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("NewCar") @Valid Car car,
                       BindingResult bindingResult){
        carDAO.addCar(car);
        return "redirect:/cars/all";
    }



    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable("id") int id, Model model,
                          Model modelCat, @ModelAttribute("category") Category selectedCategory) throws SQLException {
        model.addAttribute("car", carDAO.show(id));
        modelCat.addAttribute("Categories", carDAO.indexCategories());

        return "car/edit";
    }

    @PatchMapping("/{id}")
    public String updateCar(@PathVariable("id") int id, @ModelAttribute("car") Car car){
        carDAO.updateCar(id, car);
        return "redirect:/cars/all";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") int id){
        carDAO.deleteCar(id);
        return "redirect:/cars/all";
    }


}
