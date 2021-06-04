package cz.czechitas.java2webapps.lekce10.repository;

import cz.czechitas.java2webapps.lekce10.entity.Rodic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface RodicRepository extends JpaRepository<Rodic, Integer> {
    Page<Rodic> findByDetiIdOrderByJmeno(Integer id, Pageable pageable);
}
