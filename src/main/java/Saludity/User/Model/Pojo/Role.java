package Saludity.User.Model.Pojo;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Entity
@Table(name="roles")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name="description")
    private String description;

    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id")
    )
    private List<Permission> permissions;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
}
