package dev.tanvx.addressservice.service.impl;

import dev.tanvx.addressservice.exception.ServiceException;
import dev.tanvx.addressservice.repository.CountryRepository;
import dev.tanvx.addressservice.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    public static final String COUNTRY_NOT_FOUND = "COUNTRY_NOT_FOUND";

    private final CountryRepository countryRepository;

    @Override
    public void checkCountryById(Integer id) throws ServiceException {
        countryRepository.findById(id)
                .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
    }
}
