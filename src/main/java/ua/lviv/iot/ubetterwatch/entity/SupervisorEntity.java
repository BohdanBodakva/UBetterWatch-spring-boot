package ua.lviv.iot.ubetterwatch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "supervisor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 20, message = "First name must be in range from 2 to 20 symbols")
    @Column(name = "first_name", length = 20)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 20, message = "Last name must be in range from 2 to 20 symbols")
    @Column(name = "last_name", length = 20)
    private String lastName;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 20, message = "Username must be in range from 2 to 20 symbols")
    @Column(name = "username", length = 20)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 2, max = 20, message = "Password must be in range from 2 to 20 symbols")
    @Column(name = "password", length = 20)
    @JsonIgnore
    private String password;

    @NotBlank(message = "Role cannot be empty")
    @Size(min = 1, max = 100, message = "Role must be in range from 1 to 20 symbols")
    @Column(name = "role", length = 100)
    @JsonIgnore
    private String role;

    @Override
    public String toString() {
        return "SupervisorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
