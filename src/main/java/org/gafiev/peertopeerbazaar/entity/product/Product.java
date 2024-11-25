package org.gafiev.peertopeerbazaar.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.user.User;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private PortionUnit portionUnit;
    @Column(name = "portion_unit_count")
    private Integer portionUnitCount;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "volume")
    private Double volume;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "image_uri") //TODO сделать интеграцию с базой фото
    private String imageURI;
    @Column(name = "qr_code")
    private String qrCode; //TODO сделать интеграцию с базой QR

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User author;


}
