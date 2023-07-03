package CarDav.models;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status_app")
public class StatusApp {

    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_status;

    @Column(name = "type_status")
    private String type_status;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Order> orders;

    public StatusApp() {
    }

    public StatusApp(String type_status) {
        this.type_status = type_status;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getType_status() {
        return type_status;
    }

    public void setType_status(String type_status) {
        this.type_status = type_status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
