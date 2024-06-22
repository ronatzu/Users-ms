package Saludity.User.Model.Pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name="permission")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @Column(name="element")
    private String element;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}
