package dev.tanvx.addressservice.service.impl;

import dev.tanvx.addressservice.exception.ServiceException;
import dev.tanvx.addressservice.repository.CityRepository;
import dev.tanvx.addressservice.service.CityService;
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
