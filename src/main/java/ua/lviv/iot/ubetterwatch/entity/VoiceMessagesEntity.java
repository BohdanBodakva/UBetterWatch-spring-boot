package ua.lviv.iot.ubetterwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bracelet_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoiceMessagesEntity {

    private Long id;

    private String fileName;


    private LocalDateTime time;
}
