package CarDav.models;



import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @NotEmpty(message = "Name shouldn\'d be empty")
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Surname shouldn\'d be empty")
    private String surname;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should contain properly format")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Column(name = "keyword")
    private String keyword;

    @NotEmpty(message = "Passport id should not be empty")
    @Pattern(regexp = "^[A-Z]{2} \\d{6}$")
    @Column(name = "passport_number")
    private String passport_id;

    @Column(name = "authorization")
    private String authorization;

//    @Column(name = "id_role")
//    private int id_role;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    private UserRole userRole;


    @ManyToMany
    @JoinTable(name = "client_category",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categories;

    @OneToOne(mappedBy = "user")
 //   @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Order order;


    public User() {
    }

    public User(String name, String surname, String email,
                String keyword, String passport_id,
                String authorization) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keyword = keyword;
        this.passport_id = passport_id;
        this.authorization = authorization;
    //    this.id_role = id_role;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPassport_id() {
        return passport_id;
    }

    public void setPassport_id(String passport_id) {
        this.passport_id = passport_id;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

//    public int getId_role() {
//        return id_role;
//    }
//
//    public void setId_role(int id_role) {
//        this.id_role = id_role;
//    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


}
