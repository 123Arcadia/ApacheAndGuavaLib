/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.Test.MapStructTest.lombok.mapper;

import org.Test.MapStructTest.lombok.dto.Source;
import org.Test.MapStructTest.lombok.entities.Target;

public class Main {

    public static void main( String[] args ) {
        Source s = new Source();
        s.setTest( "5" );

        Target t = SourceTargetMapper.MAPPER.toTarget( s );
        System.out.println( t.getTesting() );
    }
}
