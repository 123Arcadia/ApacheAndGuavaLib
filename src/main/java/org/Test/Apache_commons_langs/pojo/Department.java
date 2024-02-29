package org.Test.Apache_commons_langs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Test.pojo.Person;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Integer id;
    private String name;
    private List<Person> personList;
    private Map<String, Object> dataMap;

    public Department(Integer id, String name, List<Person> personList) {
        this.id = id;
        this.name = name;
        this.personList = personList;
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
