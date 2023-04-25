package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.CourierMapper;
import ru.yandex.yandexlavka.mapper.RegionMapper;
import ru.yandex.yandexlavka.mapper.WorkingHoursMapper;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;
import ru.yandex.yandexlavka.model.entity.WorkingHoursEntity;
import ru.yandex.yandexlavka.repository.CourierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<CreateCourierDto> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(courierEntity -> courierMapper.courierToDto(courierEntity))
                .collect(Collectors.toList());
    }

    public CreateCourierDto getCourierByID(int courierID) {
        CourierEntity courierEntity = courierRepository.findById(courierID).orElseThrow();
        return courierMapper.courierToDto(courierEntity);
    }

    public List<CourierEntity> saveCouriers(CreateCourierDto[] createCourierDto) {
        List<CourierEntity> courierEntityList = new ArrayList<>();
        for (CreateCourierDto courierDto : createCourierDto) {
            List<RegionEntity> regionEntityList = courierDto.getRegions().stream()
                    .map(regionNumber -> regionMapper.regionNumberToRegionEntity(regionNumber))
                    .toList();
            List<WorkingHoursEntity> workingHoursEntityList = courierDto.getWorkingHours().stream()
                    .map(shift -> workingHoursMapper.stringToWorkingHoursEntity(shift))
                    .toList();
            CourierEntity courierEntity = courierMapper.courierDtoToEntity(courierDto, regionEntityList,
                    workingHoursEntityList);
            courierEntityList.add(courierEntity);
        }
        courierRepository.saveAll(courierEntityList);
        return courierEntityList;

    }
}
