package CarDav.DAO;

import CarDav.models.Car;
import CarDav.models.Category;
import CarDav.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
public class CarDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public CarDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private SessionFactory sessionFactory;

    @Autowired
    public CarDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<Car> index() throws SQLException {
     //   return jdbcTemplate.query("SELECT * FROM Car", new CarMapper());
         Session session = sessionFactory.getCurrentSession();

        List<Car> cars = session.createQuery("select p from Car p", Car.class).getResultList();

        return cars;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<Car> indexAvailable() throws SQLException {
        //   return jdbcTemplate.query("SELECT * FROM Car", new CarMapper());
        Session session = sessionFactory.getCurrentSession();

        List<Car> cars = session.createQuery("select p from Car p where aveliable = true", Car.class).getResultList();

        return cars;
    }


    @Transactional(readOnly = true) // только для чтения из БД
    public List<Category> indexCategories() throws SQLException {
        //   return jdbcTemplate.query("SELECT * FROM Car", new CarMapper());
        Session session = sessionFactory.getCurrentSession();

        List<Category> categories = session.createQuery("select p from Category p", Category.class).getResultList();

        return categories;
    }

    @Transactional(readOnly = true)
    public Car show(int id) throws SQLException{
//        return jdbcTemplate.query("SELECT * FROM car WHERE id_car=?", new Object[]{id},
//                new CarMapper()).stream().findAny().orElse(null);
        Session session = sessionFactory.getCurrentSession();
        return session.get(Car.class, id);
    }

    @Transactional(readOnly = true)
    public Category showCategory(int id) throws SQLException{
//        return jdbcTemplate.query("SELECT * FROM car WHERE id_car=?", new Object[]{id},
//                new CarMapper()).stream().findAny().orElse(null);
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, id);
    }

//    @Transactional
//    public void setNewCarCategory(int id, Category category){
//        Session session = sessionFactory.getCurrentSession();
//        session.get(Car.class, id).setCategory(category);
//
//    }

    @Transactional
    public void addCar(Car car){
//        jdbcTemplate.update("INSERT INTO car( brand, Model, Issue_date, price, id_category, Plate, image) VALUES (?, ?, ?, ?, ?, ?, ?)",
//                car.getBrand(), car.getModel(),
//                car.getIssue_date(), car.getPrice(), car.getId_category(),
//                car.getPlate(), car.getURLImage());
        Session session = sessionFactory.getCurrentSession();
        car.setAveliable(true);
        session.save(car);
    }

    @Transactional
    public void updateCar(int id, Car updatedCar){
//        jdbcTemplate.update("UPDATE car SET brand = ?, " +
//                        "Model = ?, Issue_date = ?, price = ?, id_category =? , Plate = ?, " +
//                        "Aveliable = ?, Damage = ?, image = ? WHERE id_car = ?",
//                car.getBrand(), car.getModel(),
//                car.getIssue_date(), car.getPrice(), car.getId_category(),
//                car.getPlate(), car.isAveliable(), car.isDamage(),
//                car.getURLImage(), id);

        Session session = sessionFactory.getCurrentSession();
        Car carToBeUpdated = session.get(Car.class, id);

        carToBeUpdated.setBrand(updatedCar.getBrand());
        carToBeUpdated.setModel(updatedCar.getModel());
        carToBeUpdated.setPlate(updatedCar.getPlate());
        carToBeUpdated.setCategory(updatedCar.getCategory());
        carToBeUpdated.setIssue_date(updatedCar.getIssue_date());
        carToBeUpdated.setPrice(updatedCar.getPrice());
        carToBeUpdated.setURLImage(updatedCar.getURLImage());
    }

    @Transactional
    public void deleteCar(int id){
      //  jdbcTemplate.update("DELETE FROM car WHERE id_car = ?", id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Car.class, id));
    }

}
