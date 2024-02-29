package org.Test.MapStructTest.em;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {

    private String describe;

    private Long id;

    private String personName;

    private String age;

    private String source;

    private String height;

    private String crTime;

    private CompanyDTO dto;

    private List<HouseDTO> houseDTOs;


}
