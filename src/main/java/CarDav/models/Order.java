package CarDav.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "order_form")
public class Order {
    @Id
    @Column(name = "Id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;



    @OneToOne
    @JoinColumn(name = "Id_car", referencedColumnName = "Id_car")
    private Car car;





    @OneToOne
    @JoinColumn(name = "Id_user", referencedColumnName = "Id_user")
    private User user;

    @Column(name = "rent_term")
    private int rent_term;

    @Column(name = "amount_pay")
    private double amount_pay;

    @Column(name = "date_order")
    private String date_order;

    @Column(name = "date_start_use")
    private String date_start_use;

    @Column(name = "date_end_use")
    private String date_end_use;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id_status")
    private StatusApp status;

    public Order() {
    }

    public Order( int rent_term,
                 double amount_pay, String date_order,
                  String date_start_use, String date_end_use) {
        this.rent_term = rent_term;
        this.amount_pay = amount_pay;
        this.date_order = date_order;
        this.date_start_use = date_start_use;
        this.date_end_use = date_end_use;
    }


    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getRent_term() {
        return rent_term;
    }

    public void setRent_term(int rent_term) {
        this.rent_term = rent_term;
    }

    public double getAmount_pay() {
        return amount_pay;
    }

    public void setAmount_pay(double amount_pay) {
        this.amount_pay = amount_pay;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public String getDate_start_use() {
        return date_start_use;
    }

    public void setDate_start_use(String date_start_use) {
        this.date_start_use = date_start_use;
    }

    public String getDate_end_use() {
        return date_end_use;
    }

    public void setDate_end_use(String date_end_use) {
        this.date_end_use = date_end_use;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusApp getStatus() {
        return status;
    }

    public void setStatus(StatusApp status) {
        this.status = status;
    }
}
