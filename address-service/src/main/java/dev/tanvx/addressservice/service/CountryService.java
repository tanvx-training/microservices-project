package dev.tanvx.addressservice.service;

import dev.tanvx.addressservice.entity.Country;
import dev.tanvx.addressservice.exception.ServiceException;

public interface CountryService {

    void checkCountryById(Integer id) throws ServiceException;
}
