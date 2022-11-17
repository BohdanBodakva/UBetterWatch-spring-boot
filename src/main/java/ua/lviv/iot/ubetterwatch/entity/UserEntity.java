package ua.lviv.iot.ubetterwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getStartUseDate() {
        return startUseDate;
    }

    public void setStartUseDate(LocalDate startUseDate) {
        this.startUseDate = startUseDate;
    }

    public SupervisorEntity getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SupervisorEntity supervisor) {
        this.supervisor = supervisor;
    }

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "start_use_date")
    private LocalDate startUseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisor_username")
    private SupervisorEntity supervisor;
}
