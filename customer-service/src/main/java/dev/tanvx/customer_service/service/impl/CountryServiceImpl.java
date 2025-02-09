package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.dto.request.country.CountriesRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.country.CountriesResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryUpdateResponseDTO;
import dev.tanvx.customer_service.entity.Address;
import dev.tanvx.customer_service.entity.City;
import dev.tanvx.customer_service.entity.Country;
import dev.tanvx.customer_service.repository.AddressRepository;
import dev.tanvx.customer_service.repository.CityRepository;
import dev.tanvx.customer_service.repository.CountryRepository;
import dev.tanvx.customer_service.service.CountryService;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;

  private final CityRepository cityRepository;

  private final AddressRepository addressRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public void checkCountryById(Integer id) throws ServiceException {
    countryRepository.findById(id)
        .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
  }

  @Override
  public Page<CountriesResponseDTO> getCountries(CountriesRequestDTO requestDTO) {

    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Country> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return countryRepository.findAll(specification, pageable)
        .map(country -> CountriesResponseDTO.builder()
            .countryId(country.getCountryId())
            .name(country.getName())
            .build());
  }

  @Override
  public CountryByIdResponseDTO getCountryById(CountryByIdRequestDTO requestDTO)
      throws ServiceException {
    return countryRepository.findById(requestDTO.getCountryId())
        .map(country -> CountryByIdResponseDTO.builder()
            .countryId(country.getCountryId())
            .name(country.getName())
            .build())
        .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
  }

  @Override
  public CountryCreateResponseDTO createCountry(CountryCreateRequestDTO requestDTO)
      throws ServiceException {
    Optional<Country> countryOptional = countryRepository.findByName(requestDTO.getName());
    if (countryOptional.isPresent()) {
      throw new ServiceException(COUNTRY_ALREADY_EXISTS);
    }
    Country country = Country.builder()
        .name(requestDTO.getName())
        .lastUpdate(ZonedDateTime.now())
        .build();
    countryRepository.save(country);
    return CountryCreateResponseDTO.builder()
        .countryId(country.getCountryId())
        .lastUpdate(country.getLastUpdate())
        .build();
  }

  @Override
  public CountryUpdateResponseDTO updateCountry(Integer countryId,
      CountryUpdateRequestDTO requestDTO) throws ServiceException {
    Country country = countryRepository.findCountryByCountryId(countryId)
        .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
    country.setName(requestDTO.getName());
    country.setLastUpdate(ZonedDateTime.now());
    countryRepository.save(country);
    return CountryUpdateResponseDTO.builder()
        .countryId(country.getCountryId())
        .lastUpdate(country.getLastUpdate())
        .build();
  }

  @Override
  @Transactional
  public CountryDeleteResponseDTO deleteCountry(Integer countryId) throws ServiceException {
    // Check if country is existed
    Country country = countryRepository.findCountryByCountryId(countryId)
        .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND));
    // Get all cities of the country
    List<City> cities = cityRepository.findAllByCountry(country);
    // Delete address linked to city
    cities.forEach(city -> {
      // Set delete flag for address
      List<Address> addresses = addressRepository.findAllByCity(city);
      addresses.forEach(address -> {
        address.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
        addressRepository.save(address);
      });
      // Set delete flag for city
      city.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
      cityRepository.save(city);
    });
    country.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    countryRepository.save(country);
    return CountryDeleteResponseDTO.builder()
        .countryId(country.getCountryId())
        .deleteFlg(country.isDeleteFlg())
        .build();
  }
}
