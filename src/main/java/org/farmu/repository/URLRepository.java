package org.farmu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.farmu.model.Url;

public interface URLRepository extends JpaRepository<Url,Integer> {

    Url findByShortUrl(String url);
}