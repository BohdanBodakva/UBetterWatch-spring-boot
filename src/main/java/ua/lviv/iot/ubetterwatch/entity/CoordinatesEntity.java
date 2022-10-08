package ua.lviv.iot.ubetterwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coordinates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesEntity {

    @Column(name = "x")
    private String x;


    @Column(name = "y")
    private String y;


    @Column(name = "time")
    private LocalDateTime time;
}
