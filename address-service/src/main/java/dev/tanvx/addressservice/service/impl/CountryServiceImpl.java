package dev.tanvx.addressservice.service.impl;

import dev.tanvx.addressservice.entity.Country;
import dev.tanvx.addressservice.exception.ServiceException;
import dev.tanvx.addressservice.repository.CityRepository;
import dev.tanvx.addressservice.repository.CountryRepository;
import dev.tanvx.addressservice.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRY_NOT_FOUND = "Country not found.";

    private final CountryRepository countryRepository;

    @Override
    public Country getCountryById(Integer id) throws ServiceException {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
    }
}
