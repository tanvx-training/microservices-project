package dev.tanvx.addressservice.service;

import dev.tanvx.addressservice.entity.Country;
import dev.tanvx.addressservice.exception.ServiceException;

public interface CountryService {

    Country getCountryById(Integer id) throws ServiceException;
}
