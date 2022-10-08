package ua.lviv.iot.ubetterwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bracelet_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BraceletDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sim_name")
    private String simName;

    @Column(name = "apn")
    private String apn;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
