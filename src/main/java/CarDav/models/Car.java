package CarDav.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id_car")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_car;


    @NotEmpty(message = "Brand shouldn\'d be empty")
    @Column(name = "brand")
    private String brand;

    @NotEmpty(message = "Model shouldn\'d be empty")
    @Column(name = "model")
    private String model;

    @Min(value = 1970, message = "Issue date must be greater then 1970")
    @Column(name = "issue_date")
    private int issue_date;

    @Min(value = 0, message = "Price must be greater then 0")
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category category;

    @Pattern(regexp = "^[A-Z]{2} \\d{4} [A-Z]{2}$", message = "Incorrect plate format")
    @Column(name = "plate")
    private String plate;

    @Column(name = "aveliable")
    private boolean aveliable;

    @Column(name = "damage")
    private boolean damage;

    @Column(name = "image")
    private String URLImage;


    @OneToOne(mappedBy = "car")
   // @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Order order;



    public Car() {
    }

    public Car(String brand,
               String model, int issue_date, double price,
               String plate, boolean aveliable,
               boolean damage, String URLImage) {
        this.brand = brand;
        this.model = model;
        this.issue_date = issue_date;
        this.price = price;
        this.plate = plate;
        this.aveliable = aveliable;
        this.damage = damage;
        this.URLImage = URLImage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(int issue_date) {
        this.issue_date = issue_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public int getId_category() {
//        return id_category;
//    }
//
//    public void setId_category(int id_category) {
//        this.id_category = id_category;
//    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isAveliable() {
        return aveliable;
    }

    public void setAveliable(boolean aveliable) {
        this.aveliable = aveliable;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public String getURLImage() {
        return URLImage;
    }

    public void setURLImage(String URLImage) {
        this.URLImage = URLImage;
    }
}
