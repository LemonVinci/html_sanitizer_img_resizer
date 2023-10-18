package org.farmu.repository;

import org.farmu.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer> {

}