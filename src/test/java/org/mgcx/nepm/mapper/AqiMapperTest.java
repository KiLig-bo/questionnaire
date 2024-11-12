package org.mgcx.nepm.mapper;

import org.junit.jupiter.api.Test;
import org.mgcx.nepm.entity.Aqi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AqiMapperTest {
    @Autowired
    AqiMapper aqiMapper;
    @Test
    public void testGetAqiById() {
        Aqi aqi = new Aqi();
        aqi.setAqiId(1);
        aqi = aqiMapper.getAqiById(aqi);
        System.out.println(aqi);
    }
    @Test
    public void testListAqi() {
        Aqi aqi = new Aqi();
        aqi.setAqiId(1);
        System.out.println(aqiMapper.listAqi(aqi));
    }

}
