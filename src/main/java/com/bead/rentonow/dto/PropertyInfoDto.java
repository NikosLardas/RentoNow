package com.bead.rentonow.dto;

import com.bead.rentonow.model.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PropertyInfoDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String location;
    private String contactInfo;
    private LocalDate availableStartDate;
    private LocalDate availableEndDate;
    private String image;
    private String hostName;

    public PropertyInfoDto(Property property) {
        id = property.getId();
        title = property.getTitle();
        price = property.getPrice();
        description = property.getDescription();
        location = property.getLocation();
        contactInfo = property.getContactInfo();
        availableStartDate = property.getAvailableStartDate();
        availableEndDate = property.getAvailableEndDate();
        image = property.getImage();
        hostName = property.getHost().getFullName();
    }

    //mappings
    @JsonIgnore
    public Property getProperty() {
        Property property = new Property();
        property.setId(id);
        property.setTitle(title);
        property.setPrice(price);
        property.setDescription(description);
        property.setLocation(location);
        property.setContactInfo(contactInfo);
        property.setAvailableStartDate(availableStartDate);
        property.setAvailableEndDate(availableEndDate);
        property.setImage(image);

        return property;
    }
}