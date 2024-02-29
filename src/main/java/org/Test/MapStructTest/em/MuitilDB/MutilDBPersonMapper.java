package org.Test.MapStructTest.em.MuitilDB;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MutilDBPersonMapper {

    MutilDBPersonMapper mutilDB_BeanCopy = Mappers.getMapper(MutilDBPersonMapper.class);

    //    @Mapping(target = "", source = "createTime", ignore = true)
    @Mapping(target = "personName",source = "name")
    SonDTO conver(Son son);

    @Mappings({
            @Mapping(target = "personName",source = "son.name"),
            @Mapping(target = "time",source = "basicEntity.createTime", dateFormat = "yyyy-MM-dd HH:mm:ss.SSS")
    })
    SonDTO combinationConver(Son son, BasicEntity basicEntity);

    @Mapping(target = "personName",source = "son.name")
    @Mapping(target = "id", source = "id")
    SonDTO converOnlyId(Son son, String id);
}