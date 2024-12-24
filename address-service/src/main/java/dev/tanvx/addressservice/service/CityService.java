package dev.tanvx.addressservice.service;

import dev.tanvx.addressservice.exception.ServiceException;

public interface CityService {

    String CITY_NOT_FOUND = "CITY_NOT_FOUND";

    void checkCityById(Integer id) throws ServiceException;
}
