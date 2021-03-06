package com.bead.rentonow.service;

import com.bead.rentonow.dto.PropertyDto;
import com.bead.rentonow.dto.PropertyInfoDto;
import com.bead.rentonow.exception.PersonNotFoundException;
import com.bead.rentonow.exception.PropertyNotFoundException;
import com.bead.rentonow.front.ApiResponse;
import com.bead.rentonow.model.Person;
import com.bead.rentonow.model.Property;
import com.bead.rentonow.repository.PersonRepository;
import com.bead.rentonow.repository.PropertyRepository;
import com.bead.rentonow.util.FileSaveUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    PropertyRepository propertyRepository;
    PersonRepository personRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PersonRepository personRepository) {
        this.propertyRepository = propertyRepository;
        this.personRepository = personRepository;
    }

    // get all properties (with fewer details)
    @Override
    public ApiResponse<List<PropertyDto>> read() {
        return new ApiResponse<List<PropertyDto>>(200, "ok", propertyRepository
                .findAll()
                .stream()
                .map(PropertyDto::new)
                .collect(Collectors.toList()));
    }

    // get all filtered properties (with fewer details)
    @Override
    public ApiResponse<List<PropertyDto>> readWithFilters(String location, BigDecimal price, LocalDate startDate, LocalDate endDate) {

        if(endDate.isAfter(startDate)) {
            return new ApiResponse<List<PropertyDto>>(200, "ok", propertyRepository
                    .getPropertiesFiltered(location, price, startDate, endDate)
                    .stream()
                    .map(PropertyDto::new)
                    .collect(Collectors.toList()));
        } else {
            return new ApiResponse<List<PropertyDto>>(400, "wrong dates inserted", null);
        }
    }

    // get one property (with all details)
    @Override
    public ApiResponse<PropertyInfoDto> read(Long id){
        Optional<Property> oProperty = propertyRepository.findById(id);
        if (oProperty.isPresent()) {
            return new ApiResponse<PropertyInfoDto>(200, "ok", new PropertyInfoDto(oProperty.get()));
        }
        return new ApiResponse<PropertyInfoDto>(400, "not found", null);
    }

    // create one property
    @Override
    public ApiResponse<PropertyInfoDto> create(PropertyInfoDto propertyInfoDto, Long personId) throws PersonNotFoundException {
        Optional<Person> oPerson = personRepository.findById(personId);
        if (!oPerson.isPresent()) {
            throw new PersonNotFoundException("Person with ID: " + personId + " not found !");
        }

        ApiResponse<PropertyInfoDto> responseProperty;

        if (propertyInfoDto == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(400, "property is null", null);
        } else if(propertyInfoDto.getTitle() == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(401, "property title is null", null);
        } else if(propertyInfoDto.getPrice() == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(401, "property price is null", null);
        } else if(propertyInfoDto.getDescription() == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(401, "property description is null", null);
        } else if(propertyInfoDto.getLocation() == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(401, "property location is null", null);
        } else if(propertyInfoDto.getContactInfo() == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(401, "property contact info is null", null);
        } else if(propertyInfoDto.getAvailableStartDate() == null) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(401, "property start date is null", null);
        } else if(propertyInfoDto.getAvailableEndDate() == null) {
            responseProperty = new ApiResponse<PropertyInfoDto>(401, "property end date is null", null);
        } else if(!propertyInfoDto.getAvailableEndDate().isAfter(propertyInfoDto.getAvailableStartDate())) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(402, "property end date needs to be bigger than property start date", null);
        } else if(propertyInfoDto.getPrice().compareTo(BigDecimal.ZERO) == 0 ) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(402, "property price can not be zero", null);
        } else if(!isEmail(propertyInfoDto.getContactInfo())) {
            responseProperty =  new ApiResponse<PropertyInfoDto>(402, "property contact info is not an email", null);
        }
        else {
            try {
                Property property = propertyInfoDto.getProperty();
                property.setHost(oPerson.get());
                property.setImage("no image");

                responseProperty = new ApiResponse<PropertyInfoDto>(201, "ok", new PropertyInfoDto(propertyRepository.save(property)));
            } catch (Exception e) {
                responseProperty = new ApiResponse<PropertyInfoDto>(403,
                        "the property was not saved", null);
            }
        }

        return responseProperty;
    }

    // Update the image of a property
    public ApiResponse<PropertyInfoDto> update(MultipartFile file, Long propertyId, Long personId) throws PropertyNotFoundException {
        Property property;
        Optional<Property> oProperty = propertyRepository.findById(propertyId);

        if (!oProperty.isPresent()) {
            throw new PropertyNotFoundException("Property with ID: "+propertyId +" not found !");
        }
        else {
            property = oProperty.get();
        }

        ApiResponse<PropertyInfoDto> responseProperty;

        if (file == null) {
            responseProperty = new ApiResponse<PropertyInfoDto>(402, "no image selected", null);
        }
        else {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                property.setImage(fileName);

                responseProperty = new ApiResponse<PropertyInfoDto>(201, "ok", new PropertyInfoDto(propertyRepository.save(property)));

                String saveDir = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\" +property.getId().toString();
                FileSaveUtil.saveFile(saveDir, fileName, file);
            } catch (Exception e) {
                responseProperty = new ApiResponse<PropertyInfoDto>(403,
                        "the property was not updated", null);
            }
        }

        return responseProperty;
    }

    // Email pattern matching using regular expression
    public static boolean isEmail(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}