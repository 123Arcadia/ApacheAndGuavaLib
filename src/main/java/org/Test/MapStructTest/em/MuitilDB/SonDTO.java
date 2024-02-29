package org.Test.MapStructTest.em.MuitilDB;


import lombok.Data;

import java.util.Date;

@Data
public class SonDTO {

    String describe;

    private Long id;

    private String personName;

    private String age;

    private String source;

    private String height;

    private Date time;

}
