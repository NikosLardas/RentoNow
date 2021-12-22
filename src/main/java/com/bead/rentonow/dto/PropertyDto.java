package com.bead.rentonow.dto;

import com.bead.rentonow.model.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.math.BigDecimal;

@Data
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

    //mappings - MIGHT BE OBSOLETE
    @JsonIgnore
    public Property getProperty() {
        Property property = new Property();
        property.setId(id);
        property.setTitle(title);
        property.setDescription(description);

        return property;
    }
}