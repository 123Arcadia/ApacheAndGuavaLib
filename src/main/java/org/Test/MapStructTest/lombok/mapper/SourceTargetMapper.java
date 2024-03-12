/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.Test.MapStructTest.lombok.mapper;

import org.Test.MapStructTest.lombok.dto.Source;
import org.Test.MapStructTest.lombok.entities.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SourceTargetMapper {

    SourceTargetMapper MAPPER = Mappers.getMapper( SourceTargetMapper.class );

    @Mapping( source = "test", target = "testing" )
    Target toTarget(Source s );
}
