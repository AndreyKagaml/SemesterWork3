package CarDav.DAO;

import CarDav.models.Car;
import CarDav.models.Category;
import CarDav.models.Order;
import CarDav.models.StatusApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<Order> index(){
      //  return jdbcTemplate.query("SELECT * FROM `order`", new OrderMapper());

        Session session = sessionFactory.getCurrentSession();
      //  TypedQuery<Order> query = session.createQuery("select p from Order p", Order.class);

        List<Order> orders = session.createQuery("select p from Order p", Order.class).getResultList();

       // List<Order> orders = session.createSQLQuery("SELECT * FROM `order_form`").getResultList();
        return orders;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<Car> indexCarsAvailable() throws SQLException {
        //   return jdbcTemplate.query("SELECT * FROM Car", new CarMapper());
        Session session = sessionFactory.getCurrentSession();

        List<Car> cars = session.createQuery("select p from Car p where aveliable = true", Car.class).getResultList();

        return cars;
    }





    @Transactional(readOnly = true)
    public Order show(int id)  {
//        return jdbcTemplate.query("SELECT * FROM `order` WHERE id_order=?", new Object[]{id},
//                new OrderMapper()).stream().findAny().orElse(null);

        Session session = sessionFactory.getCurrentSession();

        return session.get(Order.class, id);
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<StatusApp> indexStatusTypes() throws SQLException {
        //   return jdbcTemplate.query("SELECT * FROM Car", new CarMapper());
        Session session = sessionFactory.getCurrentSession();

        List<StatusApp> statusApps = session.createQuery("select p from StatusApp p", StatusApp.class).getResultList();

        return statusApps;
    }

    @Transactional
    public void addOrder(Order order){
//        jdbcTemplate.update("INSERT INTO `order`( id_car, id_user, rent_term, amount_pay, date_order, date_start_use, date_end_use) VALUES (?, ?, ?, calc_pay(?, ?), ?, ?, ?)",
//                order.getId_car(), order.getId_client(),
//                order.getRent_term(), order.getId_car(), order.getRent_term(),
//                order.getDate_order(), order.getDate_start_use(),
//                order.getDate_end_use());
        Session session = sessionFactory.getCurrentSession();

        String[] a = order.getDate_start_use().split("-");
        double b = order.getCar().getPrice() * order.getRent_term();
        order.setDate_end_use(LocalDate.of(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]))
                .plusDays(order.getRent_term()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        order.setDate_order(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        order.setAmount_pay(session.get(Car.class, order.getCar().getId_car()).getPrice() * order.getRent_term());

        order.setStatus(session.get(StatusApp.class, 1));
       // order.getCar().setAveliable(false);
        session.save(order);
    }

    @Transactional
    public void updateOrder(int id, Order updatedOrder){
//        jdbcTemplate.update("UPDATE `order` SET id_car = ?, " +
//                        "id_user = ?, rent_term = ?, amount_pay = ?, date_order =? , date_start_use = ?, " +
//                        "date_end_use = ?, id_status = ? WHERE id_order = ?",
//                order.getId_car(), order.getId_client(),
//                order.getRent_term(), order.getAmount_pay(),
//                order.getDate_order(), order.getDate_start_use(),
//                order.getDate_end_use(), order.getId_status(), id);
        Session session = sessionFactory.getCurrentSession();
        Order orderToBeUpdated = session.get(Order.class, id);
        orderToBeUpdated.setStatus(updatedOrder.getStatus());
    }

    @Transactional
    public void deleteOrder(int id){
        //jdbcTemplate.update("DELETE FROM `order` WHERE id_order = ?", id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Order.class, id));
    }


}
