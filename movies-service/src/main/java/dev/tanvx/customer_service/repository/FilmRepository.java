package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>,
    JpaSpecificationExecutor<Film> {

}
