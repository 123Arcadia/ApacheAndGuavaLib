/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.Test.MapStructTest.lombok;

import org.Test.MapStructTest.lombok.dto.Source;
import org.Test.MapStructTest.lombok.entities.Target;
import org.Test.MapStructTest.lombok.mapper.SourceTargetMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SourceTargetMapperTest {

    @Test
    public void testMapping() {
        Source s = new Source();
        s.setTest( "5" );

        Target t = SourceTargetMapper.MAPPER.toTarget( s );
        assertEquals( 5, (long) t.getTesting() );
    }
}
