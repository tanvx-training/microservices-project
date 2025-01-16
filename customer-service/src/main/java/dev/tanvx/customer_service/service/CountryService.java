package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;

public interface CountryService {

    String COUNTRY_NOT_FOUND = "COUNTRY_NOT_FOUND";

    void checkCountryById(Integer id) throws ServiceException;
}
