package dev.tanvx.addressservice.service;

import dev.tanvx.addressservice.exception.ServiceException;

public interface CountryService {

    String COUNTRY_NOT_FOUND = "COUNTRY_NOT_FOUND";

    void checkCountryById(Integer id) throws ServiceException;
}
