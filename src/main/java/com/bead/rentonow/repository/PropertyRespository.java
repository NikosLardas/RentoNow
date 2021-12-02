package com.bead.rentonow.repository;

import com.bead.rentonow.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRespository extends JpaRepository<Property, Long> {

}
