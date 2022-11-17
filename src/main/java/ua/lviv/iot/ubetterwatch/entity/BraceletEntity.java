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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BraceletDataEntity getBraceletData() {
        return braceletData;
    }

    public void setBraceletData(BraceletDataEntity braceletData) {
        this.braceletData = braceletData;
    }
}
