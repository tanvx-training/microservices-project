package dev.tanvx.movies_service.service.impl;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.movies_service.repository.CountryRepository;
import dev.tanvx.movies_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public void checkCountryById(Integer id) throws ServiceException {
        countryRepository.findById(id)
                .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
    }
}
