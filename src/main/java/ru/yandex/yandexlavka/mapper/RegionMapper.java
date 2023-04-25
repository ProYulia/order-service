package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.entity.RegionEntity;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "regionNumber", source = "regionNumber")
    @Mapping(target = "completeTime", ignore = true)
    RegionEntity regionNumberToRegionEntity(Integer regionNumber);
}
