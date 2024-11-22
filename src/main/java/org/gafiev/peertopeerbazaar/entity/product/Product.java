package org.example.entity.product;

import lombok.*;
import org.example.entity.user.User;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long id;
    private String name;
    private String description;
    // TODO категория продукта ENTITY fragile хрупкое жидкое
    private PortionUnit portionUnit;
    private Integer portionUnitCount;
    private BigDecimal price;
    private String imageURI; //TODO сделать интеграцию с базой фото
    private User author;
    private String qrCode; //TODO сделать интеграцию с базой QR
  //TODO вес и объём
}
