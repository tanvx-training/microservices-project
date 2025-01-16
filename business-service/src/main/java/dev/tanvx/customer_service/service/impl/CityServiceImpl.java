package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.repository.CityRepository;
import dev.tanvx.customer_service.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public void checkCityById(Integer id) throws ServiceException {
        cityRepository.findById(id)
                .orElseThrow(() -> new ServiceException(CITY_NOT_FOUND));
    }
}
