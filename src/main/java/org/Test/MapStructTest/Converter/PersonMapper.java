package org.Test.MapStructTest.Converter;

import org.Test.MapStructTest.em.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@Mapper(componentModel = "spring")
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCT = Mappers.getMapper(PersonMapper.class);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Mappings({
            @Mapping(target = "personName", source = "name"),
            @Mapping(target = "id", ignore = true), // 忽略id，不进行映射, 则是null
            @Mapping(target = "describe", source = "describe", defaultValue = "默认值"), //指定默认值:target() 必须添加，source()可以不添加，则直接使用defaultValue,(则直接使用defaultValue不设置默认为null)
//            @Mapping(target = "crTime",expression = "java(dateToStr(new java.util.Date()))") // 表达式必须要引入包名
            @Mapping(target = "crTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss.SSS", defaultExpression = "java(now())")
    })
    PersonDTO conver(Person person);



    public default String dateToStr(Date date) {
        return format.format(date);
    }

    /**
     * 今天
     */
    public default String now() {
        return format.format(new Date());
    }

    /**
     * 嵌套属性映射(定义下面的CompanyDTO converDto(Company company);)，接下来会自动实现
     */
    @Mapping(source="person.company", target = "dto")
    PersonDTO coverNestedProperties(Person person);

    @Mapping(source = "createBy", target = "leader")
    @Mapping(source = "createDate", target = "currentDate")
    @Mapping(source = "numberOfPeople", target = "numbers")
    CompanyDTO converDto(Company company);

    @Mapping(source="person.company", target = "dto")
    @Mapping(source = "person.houses", target ="houseDTOs")
    PersonDTO coverNestedColl(Person person);

    List<HouseDTO> coverColl(List<House> houses);

    @Mapping(source = "price", target = "house_pay")
    HouseDTO converHouse(House house);

    /**
     * 逆映射
     */
    @InheritInverseConfiguration(name = "coverNestedColl")
    Person coverNestedColl(PersonDTO personDTO);
}
