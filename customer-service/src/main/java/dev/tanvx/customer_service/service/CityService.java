package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;

public interface CityService {

    String CITY_NOT_FOUND = "CITY_NOT_FOUND";

    void checkCityById(Integer id) throws ServiceException;
}
