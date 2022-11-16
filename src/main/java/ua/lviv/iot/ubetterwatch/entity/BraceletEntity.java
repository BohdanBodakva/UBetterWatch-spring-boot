package ua.lviv.iot.ubetterwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bracelet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BraceletEntity {
    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bracelet_data_id")
    private BraceletDataEntity braceletData;
}
