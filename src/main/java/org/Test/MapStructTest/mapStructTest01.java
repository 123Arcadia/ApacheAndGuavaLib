package org.Test.MapStructTest;

import cn.hutool.core.bean.BeanUtil;
import org.Test.MapStructTest.Converter.EnumToStr.EnumToStrCon;
import org.Test.MapStructTest.Converter.PersonMapper;
import org.Test.MapStructTest.em.*;
import org.Test.MapStructTest.em.MuitilDB.BasicEntity;
import org.Test.MapStructTest.em.MuitilDB.MutilDBPersonMapper;
import org.Test.MapStructTest.em.MuitilDB.Son;
import org.Test.MapStructTest.em.MuitilDB.SonDTO;
import org.Test.MapStructTest.em.enumEm.EnumTest01;
import org.Test.MapStructTest.em.enunToStr.EnumInSro;
import org.Test.MapStructTest.em.enunToStr.ToStringEm;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * https://github.com/mapstruct/mapstruct-examples
 */
public class mapStructTest01 {



    @Test
    public void test01(){
        Person person = new Person();
        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));

        PersonDTO dto = PersonMapper.INSTANCT.conver(person);

        System.out.println(dto);
        //PersonDTO(describe=测试, id=null, personName=张三, age=18, source=100, height=170.5, crTime=2024-02-17 12:46:02.298)
        System.out.println("-----------------");
        PersonDTO hutoolTarget = new PersonDTO();

        BeanUtil.copyProperties(person, hutoolTarget);
        System.out.println("hutoolTarget = " + hutoolTarget);
        //apacheTarget = PersonDTO(describe=测试, id=null, personName=null, age=18, source=100, height=170.5, crTime=null)
        // 注意: 两者name和userNamen不一样，没有映射
        System.out.println("-----------------");

        PersonDTO springTarget = new PersonDTO();



    }


    /**
     * 测试指定默认值: target值必须，srouce值不是必须
     */
    @Test
    public void test02() {
        Person person = new Person();
//        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));

        PersonDTO dto = PersonMapper.INSTANCT.conver(person);

        System.out.println(dto);
        //PersonDTO(describe=默认值, id=null, personName=张三, age=18, source=100, height=170.5)
    }

    /**
     * 使用表达式
     * 目前java是唯一受支持的语言，达式必须以Java表达式的形式给出
     * 默认表达式@Mapping#defaultExpression()是默认值和表达式的组合。仅当source属性为null时才使用它们
     *
     * 注意：这个属性不能与source()、defaultValue()、defaultExpression()、qualifiedBy()、qualifiedByName()或constant()一起使用。
     *
     *      @Mapping(target = "crTime",expression = "java(dateToStr(new java.util.Date()))") // 表达式必须要引入包名
     */
    @Test
    public void test03() {
        Person person = new Person();
//        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));

        PersonDTO dto = PersonMapper.INSTANCT.conver(person);

        System.out.println(dto);
        //PersonDTO(describe=默认值, id=null, personName=张三, age=18, source=100, height=170.5, crTime=2024-02-17 11:07:59.492)
    }

    /**
     * 指定dateFormat:
     *      @Mapping(target = "crTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss.SSS")
     */
    @Test
    public void test04() {
        Person person = new Person();
//        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));
        person.setCreateTime(new Date());

        PersonDTO dto = PersonMapper.INSTANCT.conver(person);

        System.out.println(dto);
        //PersonDTO(describe=默认值, id=null, personName=张三, age=18, source=100, height=170.5, crTime=2024-02-17 11:07:59.492)
    }

    /**
     * 字符串与枚举类型之间的转换，通过qualifiedByName注解实现
     *
     * 使用: EnumToStrCon映射器
     *
     */
    @Test
    public void test05() {
        EnumInSro source = new EnumInSro();
        source.setName("zzz");
        source.setEn(EnumTest01.第三);
        source.setAge(19);
        source.setAdderss("This is my address");

        ToStringEm target = EnumToStrCon.EnumToStrConverter.converter(source);
        System.out.println("target = " + target);
        //target = ToStringEm(name=zzz, age=19, adderss=This is my address, en=第三)

    }

    /**
     * 使用Spring.BeanUtils.copyProperties()
     */
    @Test
    public void test06() {
        EnumInSro source = new EnumInSro();
        source.setName("zzz");
        source.setEn(EnumTest01.第三);
        source.setAge(19);
        source.setAdderss("This is my address");
        ToStringEm target = new ToStringEm();
//        BeanUtils.copyProperties(target, source);
        System.out.println("target = " + target);
    }

    /**
     * 多个数据源
     */
    @Test
    public void test07() {
        Son son = new Son();
//        person.setDescribe("测试");
        son.setId("3");
        son.setAge(18);
        son.setName("test07");
        son.setHeight(170.5);
        son.setSource(new BigDecimal("100"));
        SonDTO sonDTO = MutilDBPersonMapper.mutilDB_BeanCopy.conver(son);
        System.out.println("sonDTO = " + sonDTO);
        //sonDTO = SonDTO(describe=null, id=3, personName=test07, age=18, source=100, height=170.5, createTime=null)

        // 多源数据
        BasicEntity basic = new BasicEntity();
        basic.setCreateTime(new Date());
        basic.setCreateBy("today");
        basic.setUpdateTime(new Date());
        SonDTO mutilDBSonDto = MutilDBPersonMapper.mutilDB_BeanCopy.combinationConver(son, basic);
        System.out.println("mutilDBSonDto = " + mutilDBSonDto);

        // 指定个别参数s
        SonDTO onlyIdDto = MutilDBPersonMapper.mutilDB_BeanCopy.converOnlyId(son, "123");
        System.out.println("onlyIdDto = " + onlyIdDto);
        //onlyIdDto = SonDTO(describe=null, id=123, personName=test07, age=18, source=100, height=170.5, time=null)
    }


    /**
     *  嵌套映射:
     */
    @Test
    public void test08() {
        Person person = new Person();
        person.setDescribe("嵌套映射");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));
        person.setCreateTime(new Date());

        Company company = new Company();
        company.setAddress("龙岗大道10012");
        company.setName("成宇科技");
        company.setCreateBy("ccc");
        company.setCreateDate(new Date());
        company.setNumberOfPeople(1000);
        person.setCompany(company);

        PersonDTO dto = PersonMapper.INSTANCT.coverNestedProperties(person);

        System.out.println(dto);
        // 没有定义company -> companyDto
        //PersonDTO(describe=嵌套映射, id=null, personName=null, age=18, source=100, height=170.5, crTime=null,
        // dto=CompanyDTO(name=成宇科技, address=龙岗大道10012, numbers=null, currentDate=null, leader=null))

        // 定义company -> companyDto 之后:
        //PersonDTO(describe=嵌套映射, id=null, personName=null, age=18, source=100, height=170.5, crTime=null,
        // dto=CompanyDTO(name=成宇科技, address=龙岗大道10012, numbers=1000, currentDate=Sat Feb 17 16:49:13 CST 2024, leader=ccc))
    }


    /**
     * 集合属性:(一个是为List映射, 一个是为House -> HouseDto)
     *
     * @Mapping(source="person.company", target = "dto")
     * @Mapping(source = "person.houses", target ="houseDTOs")
     * PersonDTO coverNestedColl(Person person);
     *
     * List<HouseDTO> coverColl(List<House> houses);
     *
     * @Mapping(source = "price", target = "house_pay")
     * HouseDTO converHouse(House house);
     *
     */
    @Test
    public void test09() {
        Person person = new Person();
        person.setDescribe("嵌套映射");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));
        person.setCreateTime(new Date());

        Company company = new Company();
        company.setAddress("龙岗大道10012");
        company.setName("成宇科技");
        company.setCreateBy("ccc");
        company.setCreateDate(new Date());
        company.setNumberOfPeople(1000);
        person.setCompany(company);
        List<House>houseList = new ArrayList<>();
        houseList.add(new House("add01",10));
        houseList.add(new House("add02",11));
        person.setHouses(houseList);

        PersonDTO dto = PersonMapper.INSTANCT.coverNestedColl(person);

        System.out.println(dto);
        //PersonDTO(describe=嵌套映射, id=null, personName=null, age=18, source=100, height=170.5, crTime=null,
        // dto=CompanyDTO(name=成宇科技, address=龙岗大道10012, numbers=1000, currentDate=Sat Feb 17 17:14:47 CST 2024, leader=ccc), houseDTOs=[HouseDTO(house_pay=10, address=add01),
        // HouseDTO(house_pay=11, address=add02)])
    }


    /**
     * 逆映射 dto -> PO: @InheritInverseConfiguration 表示方法应继承相应反向方法的反向配置
     */
    @Test
    public void test10() {
        PersonDTO personDto = new PersonDTO();
        personDto.setDescribe("嵌套映射");
        personDto.setAge("18");
        personDto.setPersonName("张三");
        personDto.setHeight("170.5");
        personDto.setSource("100");

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setAddress("龙岗大道10012");
        companyDTO.setName("成宇科技");
        companyDTO.setAddress("address");
        companyDTO.setLeader("leader");
        companyDTO.setNumbers(1000);
        companyDTO.setCurrentDate(new Date());

        personDto.setDto(companyDTO);

        List<HouseDTO>houseDtoList = new ArrayList<>();
        houseDtoList.add(new HouseDTO("add01",10));
        houseDtoList.add(new HouseDTO("add02",11));
        personDto.setHouseDTOs(houseDtoList );

        Person person = PersonMapper.INSTANCT.coverNestedColl(personDto);
        System.out.println("person = " + person);
        //person = Person(describe=嵌套映射, id=null, name=null, age=18, source=100, height=170.5,
        // createTime=null,
        // company=Company(name=成宇科技, address=address, numberOfPeople=null, createDate=null, createBy=null),
        // houses=[House(address=add01, price=null),
        // House(address=add02, price=null)])
    }

    class Node {
        Map<Integer, Node> son = new HashMap<>();
        int cnt;
    };

    /**
     * 构造模式串 pattern 的最大匹配数表
     *
     * 所有要与甲匹配的字符串（称之为模式串），必须先自身匹配：对每个子字符串 [0...i]，算出其「相匹配的真前缀与真后缀中，最长的字符串的长度」。
     * @param pattern
     * @return
     */
    public int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLen = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLen[maxLength - 1];
                // 一直不匹配，就会推到maxMatchLen[0] = 0
            }
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                maxLength++;
            }
            maxMatchLen[i] = maxLength;
        }
        return maxMatchLen;
    }

    /**
     * kmp:
     * @param text 文本
     * @param pattern 模式串
     * @return 返回所有匹配的位置开头
     */
    public List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>(text.length());
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count-1];
                // 不匹配就退回
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count ++;
            }
            if (count == pattern.length()) {
                // 匹配模式串全部
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        // 获取next数组
        int m = pattern.length;
        int[] next = new int[m];
        int cnt = 0;
        for (int i = 1; i < m; i++) {
            int val = pattern[i];
            while (cnt > 0 && val != pattern[cnt]) {
                cnt = next[cnt - 1];
            }
            if (val == pattern[cnt]) {
                cnt++;
            }
            next[i] = cnt;
        }
        // 把nums数组的值映射为1 -1 0
        int ans = 0;
        cnt=0;
        for (int i = 1; i < nums.length; i++) {
            int val = Integer.compare(nums[i], nums[i-1]);
            while (cnt > 0 && val != pattern[cnt]) {
                cnt = next[cnt - 1];
            }
            if (val == pattern[cnt]) {
                cnt ++;
            }
            if (cnt == m) {
                ans++;
                cnt = next[cnt - 1]; // 开始匹配下一个子字符串
            }
        }
        return ans;
    }

    @Test
    public void test12() {
//        String str = "abbaab";
        String str = "abababzababab";
        int[] next = calculateMaxMatchLengths(str);
        System.out.println(Arrays.toString(next));
        //[0, 0, 0, 1, 1, 2]
        //"abababzababab":[0, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 6]


        //**对每个子字符串 [0...i]**，算出其「相匹配的真前缀与真后缀中，最长的字符串的长度」
        // 把[0...i]的每个子字符串的前后缀列出来，找其最大匹配的字符串长度即可
        //a : 0
        //"ab":0
        //"aba":1
            // 前: a ab
            // 后: a ba
        //"abab":2
            // 前: a ab aba
            // 后: a ab bab
        //ababa:3
            // 前: a ab aba abab
            // 后: a ba aba baba
        // ababab:4
            // 前: a ab aba abab ababa
            // 后: a ab bab abab babab
        // abababz:0
            // 前: a ab aba abab ababa ababab
            // 后: z bz abz babz ababz bababz
        //...

        //最后abababzababab:6
            // 前: a ab aba abab ababa ababab abababz abababza abababzab abababzaba abababzabab abababzababa
            // 后: a ab bab abab babab ababab zababab bzababab abzababab babzababab ababzababab bababzababab

        // 所有next: [0, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 6]
    }




}
