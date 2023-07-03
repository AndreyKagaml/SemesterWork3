package CarDav.DAO;

import CarDav.models.Order;
import CarDav.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {
 //   private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public UserDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<User> indexUser(){
      //  return jdbcTemplate.query("SELECT * FROM user WHERE id_user=2" , new UserMapper());

        Session session = sessionFactory.getCurrentSession();

        List<User> users = session.createQuery("select p from User p", User.class).getResultList();

        return users;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public List<User> indexAdmin(){
      //  return jdbcTemplate.query("SELECT * FROM user WHERE id_user=1" , new UserMapper());
        Session session = sessionFactory.getCurrentSession();
        List<User> admins = session.createQuery("select p from User p where userRole.id_role = 2 ", User.class).getResultList();
        return admins;
    }



    @Transactional(readOnly = true) // только для чтения из БД
    public List<User> indexClient(){
        //  return jdbcTemplate.query("SELECT * FROM user WHERE id_user=1" , new UserMapper());
        Session session = sessionFactory.getCurrentSession();
        List<User> clients = session.createQuery("select p from User p where userRole.id_role = 1", User.class).getResultList();
        return clients;
    }

    @Transactional(readOnly = true) // только для чтения из БД
    public Order showClientsOrder(int id_user){
        //  return jdbcTemplate.query("SELECT * FROM user WHERE id_user=1" , new UserMapper());
        Session session = sessionFactory.getCurrentSession();
        Order order = session.createQuery("select p from Order p where p.user.id_user =:param and p.user.userRole.id_role = 1", Order.class)
                .setParameter("param", id_user).getSingleResult();
        return order;
    }

    @Transactional(readOnly = true)
    public User findOne(int id){
//        return jdbcTemplate.query("SELECT * FROM user WHERE id_user=?", new Object[]{id} , new UserMapper())
//                .stream().findAny().orElse(null);
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);

      //  session.get(Order.class);
        return user;
    }




    public Optional<User> showWithEmail(String email){
//        return jdbcTemplate.query("SELECT * FROM `user` WHERE email=?", new Object[]{email},
//                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
        return null;
    }

    public Optional<User> showWithPassport(String passport_id){
//        return jdbcTemplate.query("SELECT * FROM `user` WHERE passport_id=?", new Object[]{passport_id},
//                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
        return null;
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void update(int id, User updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        User personToBeUpdated = session.get(User.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }
}
