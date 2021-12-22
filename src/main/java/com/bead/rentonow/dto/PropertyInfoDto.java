package com.bead.rentonow.dto;

import com.bead.rentonow.model.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

@Data
public class PropertyInfoDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String location;
    private String contactInfo;
    private Date availableStartDate;
    private Date availableEndDate;
    private byte[] image;
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
        image = convertBlobToByteArray(property.getImageBlob());
        hostName = property.getHost().getFullName();
    }

    //mappings
    @JsonIgnore
    public Property getProperty() {
        Property property = new Property();
        property.setId(id);
        property.setTitle(title);
        property.setDescription(description);
        property.setLocation(location);
        property.setContactInfo(contactInfo);
        property.setAvailableStartDate(availableStartDate);
        property.setAvailableEndDate(availableEndDate);
        property.setImageBlob(convertByteArrayToBlob(image));

        return property;
    }

    private byte[] convertBlobToByteArray(Blob blob) {
        try {
            return blob.getBytes(1, (int) blob.length());
        }
        catch (SQLException e) {
            return null;
        }
    }

    private Blob convertByteArrayToBlob(byte[] image) {
        try {
            return new SerialBlob(image);
        }
        catch (SQLException e) {
            return null;
        }
    }
}