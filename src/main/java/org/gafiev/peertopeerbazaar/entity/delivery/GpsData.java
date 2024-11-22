package org.gafiev.peertopeerbazaar.entity.delivery;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gps_data")
public class GpsData {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "latitude")
   private double latitude;
   @Column(name = "longtitude")
   private double longitude;
   @Column(name = "attitude")
   private double altitude;
   @Column(name = "accuracy")
   private float accuracy;
   @Column(name = "time_stamp")
   private long timeStamp;
   @Column(name = "speed")
   private float speed;
   @Column(name = "bearing")
   private float bearing;

}
//@Entity public class GPSData { @PrimaryKey(autoGenerate = true) private Long id;
// private double latitude; private double longitude; private float accuracy;
// В метрах private long timestamp; // Unix-время private double altitude;
// В метрах private float speed;
// В м/с private float bearing;
// Угол в градусах }