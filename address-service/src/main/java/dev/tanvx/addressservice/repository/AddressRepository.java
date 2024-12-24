package dev.tanvx.addressservice.repository;

import dev.tanvx.addressservice.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address a JOIN FETCH a.city c WHERE c.country.countryId = :countryId")
    Page<Address> findByCountryId(Integer countryId, Pageable pageable);

    @Query("SELECT a FROM Address a JOIN FETCH a.city c WHERE c.cityId = :cityId")
    Page<Address> findByCityId(Integer cityId, Pageable pageable);
}
