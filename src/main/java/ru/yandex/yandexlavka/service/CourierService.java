package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.CourierMapper;
import ru.yandex.yandexlavka.mapper.RegionMapper;
import ru.yandex.yandexlavka.mapper.WorkingHoursMapper;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;
import ru.yandex.yandexlavka.model.entity.WorkingHoursEntity;
import ru.yandex.yandexlavka.model.response.CreateCourierResponse;
import ru.yandex.yandexlavka.model.response.GetCouriersResponse;
import ru.yandex.yandexlavka.repository.CourierRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourierService {


    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private CourierMapper courierMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private WorkingHoursMapper workingHoursMapper;

    @Autowired
    private CreateCourierResponse createCourierResponse;

    @Autowired
    private GetCouriersResponse getCouriersResponse;


    public GetCouriersResponse getAllCouriers(Integer offset, Integer limit) {

//        int pageNumber = offset/limit;
//        Pageable pageable = PageRequest.of(pageNumber, limit);

        if (offset == null) {
            getCouriersResponse.setOffset(0);
            offset = 0;
        } else
            getCouriersResponse.setOffset(offset);

        if (limit == null) {
            getCouriersResponse.setLimit(1);
            limit = 1;
        } else
            getCouriersResponse.setLimit(limit);

        List<CourierDto> courierDtoList = courierRepository
                .findAll(offset, limit)
                .stream()
                .map(entity -> courierMapper.entityToCreateResponse(entity))
                .toList();

        getCouriersResponse.setCouriers(courierDtoList);

        return getCouriersResponse;
    }

    public CreateCourierDto getCourierByID(int courierID) {
        CourierEntity courierEntity = courierRepository.findById(courierID).orElseThrow();
        return courierMapper.courierToDto(courierEntity);
    }

    public CreateCourierResponse saveCouriers(List<CreateCourierDto> createCourierDto) {

        List<CourierEntity> courierEntityList = new ArrayList<>();

        for (CreateCourierDto courierDto : createCourierDto) {

            List<RegionEntity> regionEntityList = courierDto.getRegions().stream()
                    .map(regionNumber -> regionMapper.regionNumberToRegionEntity(regionNumber))
                    .toList();

            List<WorkingHoursEntity> workingHoursEntityList = courierDto.getWorkingHours().stream()
                    .map(shift -> workingHoursMapper.stringToWorkingHoursEntity(shift.substring(0,5), shift.substring(6,11)))
                    .toList();

            CourierEntity courierEntity = courierMapper.courierDtoToEntity(courierDto, regionEntityList,
                    workingHoursEntityList);

            courierEntityList.add(courierEntity);
        }
        courierRepository.saveAll(courierEntityList);


        List<CourierDto> courierDtoList = courierEntityList
                .stream()
                .map(entity -> courierMapper.entityToCreateResponse(entity))
                .toList();
        createCourierResponse.setCouriers(courierDtoList);

        return createCourierResponse;
    }




}
