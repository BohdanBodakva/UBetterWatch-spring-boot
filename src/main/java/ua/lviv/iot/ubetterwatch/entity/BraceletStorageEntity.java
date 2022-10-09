package ua.lviv.iot.ubetterwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bracelet_storage")@Data
@AllArgsConstructor
@NoArgsConstructor
public class BraceletStorageEntity {
    @Id
    @Column(name = "serial_number", length = 50)
    private String serialNumber;
}
