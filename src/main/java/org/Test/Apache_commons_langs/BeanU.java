package org.Test.Apache_commons_langs;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

public class BeanU {

    public static void main(String[] args) {
        SourcePoJo sourcePoJo = new SourcePoJo();
        sourcePoJo.setUsername("joy");
        TargetPoJo targetPoJo = new TargetPoJo();
        try {
            BeanUtils.copyProperties(sourcePoJo,targetPoJo);
        } catch (Exception e) {
            System.out.println("error！");
            e.printStackTrace();
        }
        System.out.println(targetPoJo);
        //TargetPoJo(username=null, id=0)
        System.out.println(sourcePoJo);
        //SourcePoJo(username=joy, id=null)
        // 一个(Long)是null，一个(long)是0
    }
}
@Data
class SourcePoJo{
    private String username;
    private Long id;
}

@Data
class TargetPoJo{
    private String username;
    private long id;
}

