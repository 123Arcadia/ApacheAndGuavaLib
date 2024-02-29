package org.Test.MapStructTest.Converter.EnumToStr;

import org.Test.MapStructTest.em.enumEm.EnumTest01;
import org.Test.MapStructTest.em.enunToStr.EnumInSro;
import org.Test.MapStructTest.em.enunToStr.ToStringEm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnumToStrCon {

    EnumToStrCon EnumToStrConverter = Mappers.getMapper(EnumToStrCon.class);

    @Mappings({
            @Mapping(target = "en", source = "en", qualifiedByName = "mapEnumToString")
    })
    ToStringEm converter(EnumInSro srouce);

    @Named("mapEnumToString")
    static String mapEnumToString(EnumTest01 souEnum) {
        return souEnum.toString();
    }
}
