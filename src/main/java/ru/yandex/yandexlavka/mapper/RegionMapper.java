package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.entity.RegionEntity;

@Mapper
public interface RegionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "regionNumber", source = "regionNumber")
    RegionEntity regionNumberToRegionEntity(Integer regionNumber);
}
