package dev.tanvx.customer_service.service.impl;

import static dev.tanvx.customer_service.service.CountryService.COUNTRY_NOT_FOUND;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.enums.FieldType;
import dev.tanvx.common_library.specification.enums.Operator;
import dev.tanvx.common_library.specification.request.FilterRequest;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.dto.request.city.CitiesRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CitiesByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.city.CitiesResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CitiesResponseDTO.CountryDTO;
import dev.tanvx.customer_service.dto.response.city.CityByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityUpdateResponseDTO;
import dev.tanvx.customer_service.entity.City;
import dev.tanvx.customer_service.repository.CityRepository;
import dev.tanvx.customer_service.repository.CountryRepository;
import dev.tanvx.customer_service.service.CityService;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

    @Override
    public void checkCityById(Integer id) throws ServiceException {
        cityRepository.findById(id)
                .orElseThrow(() -> new ServiceException(CITY_NOT_FOUND));
    }

    @Override
    public Page<CitiesResponseDTO> getCities(CitiesRequestDTO requestDTO) {
        SearchRequest searchRequest = SearchRequest.builder()
            .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
            .page(requestDTO.getPage())
            .size(requestDTO.getSize())
            .build();
        SearchSpecification<City> specification = new SearchSpecification<>(searchRequest);
        Pageable pageable = SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
        return cityRepository.findAll(specification, pageable)
            .map(city -> CitiesResponseDTO.builder()
                .cityId(city.getCityId())
                .name(city.getName())
                .country(CountryDTO.builder()
                    .countryId(city.getCountry().getCountryId())
                    .countryName(city.getCountry().getName())
                    .build())
                .build());
    }

    @Override
    public CityByIdResponseDTO getCityById(CityByIdRequestDTO requestDTO) throws ServiceException {

        return cityRepository.findById(requestDTO.getCityId())
            .map(entity -> CityByIdResponseDTO.builder()
                .cityId(entity.getCityId())
                .name(entity.getName())
                .country(CountryDTO.builder()
                    .countryId(entity.getCountry().getCountryId())
                    .countryName(entity.getCountry().getName())
                    .build())
                .build())
            .orElseThrow(() -> new ServiceException(CITY_NOT_FOUND));
    }

    @Override
    public Page<CityByCountryResponseDTO> getCitiesByCountry(CitiesByCountryRequestDTO requestDTO) {

        FilterRequest filterRequest = FilterRequest.builder()
            .key("country.countryId")
            .operator(Operator.EQUAL)
            .fieldType(FieldType.INTEGER)
            .value(requestDTO.getCountryId())
            .build();

        SearchRequest searchRequest = SearchRequest.builder()
            .filters(List.of(filterRequest))
            .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
            .page(requestDTO.getPage())
            .size(requestDTO.getSize())
            .build();

        SearchSpecification<City> specification = new SearchSpecification<>(searchRequest);
        Pageable pageable = SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());

        return cityRepository.findAll(specification, pageable)
            .map(city -> CityByCountryResponseDTO.builder()
                .cityId(city.getCityId())
                .name(city.getName())
                .build());
    }

    @Override
    public CityCreateResponseDTO createCity(CityCreateRequestDTO requestDTO)
        throws ServiceException {

        City city = City.builder()
            .name(requestDTO.getName())
            .country(countryRepository.findById(requestDTO.getCountryId())
                .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND)))
            .lastUpdate(OffsetDateTime.now())
            .build();

        cityRepository.save(city);

        return CityCreateResponseDTO.builder()
            .cityId(city.getCityId())
            .lastUpdate(city.getLastUpdate())
            .build();
    }

    @Override
    public CityUpdateResponseDTO updateCity(Integer cityId, CityUpdateRequestDTO requestDTO)
        throws ServiceException {

        City city = cityRepository.findCityByCityId(cityId)
            .orElseThrow(() -> new ServiceException(CITY_NOT_FOUND));

        city.setName(requestDTO.getName());
        city.setCountry(countryRepository.findById(requestDTO.getCountryId())
            .orElseThrow(() -> new ServiceException(COUNTRY_NOT_FOUND)));
        city.setLastUpdate(OffsetDateTime.now());
        cityRepository.save(city);

        return CityUpdateResponseDTO.builder()
            .cityId(city.getCityId())
            .lastUpdate(city.getLastUpdate())
            .build();
    }

    @Override
    public void deleteCity(Integer cityId) throws ServiceException {
        if (!cityRepository.existsById(cityId)) {
            throw new ServiceException(CITY_NOT_FOUND);
        }
        cityRepository.deleteById(cityId);
    }
}
