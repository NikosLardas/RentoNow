package com.bead.rentonow.dto;

import com.bead.rentonow.model.Person;
import com.bead.rentonow.model.Property;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class PropertyDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;

    public PropertyDto(Property property) {
        id = property.getId();
        title = property.getTitle();
        price = property.getPrice();
        description = property.getDescription();
    }

    //mappings
    public Property getProperty() {
        Property property = new Property();
        property.setId(id);
        property.setTitle(title);
        property.setDescription(description);

        return property;
    }
}
