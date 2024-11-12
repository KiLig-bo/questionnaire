package org.mgcx.nepm.mapper;

import org.junit.jupiter.api.Test;
import org.mgcx.nepm.entity.GridCity;
import org.mgcx.nepm.entity.GridDistrict;
import org.mgcx.nepm.entity.GridProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GridMapperTest {

    @Autowired
    GridProvinceMapper gridProvinceMapper;
    @Autowired
    GridCityMapper gridCityMapper;
    @Autowired
    GridDistrictMapper gridDistrictMapper;



    @Test
    public void testGetGridProvinceByName() {
        GridProvince gridProvince = new GridProvince();
        gridProvince.setProvinceName("北京市");
        gridProvince = gridProvinceMapper.getGridProvinceByName(gridProvince);
        System.out.println(gridProvince);
    }
    @Test
    public void testGetGridProvinceByAbbr() {
        GridProvince gridProvince = new GridProvince();
        gridProvince.setProvinceAbbr("京");
        gridProvince = gridProvinceMapper.getGridProvinceByAbbr(gridProvince);
        System.out.println(gridProvince);
    }
    @Test
    public void testListGridProvince() {
        GridProvince gridProvince = new GridProvince();
        List<GridProvince> gridProvince1 = gridProvinceMapper.listGridProvince(gridProvince);
        System.out.println(gridProvince1);
    }
    @Test
    public void testGetGridProvinceById() {
        GridProvince gridProvince = new GridProvince();
        gridProvince.setProvinceId(1100000);
        gridProvince = gridProvinceMapper.getGridProvinceById(gridProvince);
        System.out.println(gridProvince);
    }


    @Test
    public void testGetGridCityByCityName() {
        GridCity gridCity = new GridCity();
        gridCity.setCityName("包头市");
        gridCity = gridCityMapper.getGridCityByCityName(gridCity);
        System.out.println(gridCity);
    }
    @Test
    public void testGetGridCityByCityId() {
        GridCity gridCity = new GridCity();
        gridCity.setCityId(1101000);
        gridCity = gridCityMapper.getGridCityByCityId(gridCity);
        System.out.println(gridCity);
    }
    @Test
    public void testListGridCity() {
        GridCity gridCity = new GridCity();
        List<GridCity> gridCity1 = gridCityMapper.listGridCity(gridCity);
        System.out.println(gridCity1);
    }
    @Test
    public void testGetGridCityByProvinceId() {
        GridCity gridCity = new GridCity();
        gridCity.setProvinceId(1100000);
        List<GridCity> gridCity1 = gridCityMapper.getGridCityByProvinceId(gridCity);
        System.out.println(gridCity1);
    }




    @Test
    public void testGetGridDistrictByDistrictName() {
        GridDistrict gridDistrict = new GridDistrict();
        gridDistrict.setDistrictName("东城区");
        gridDistrict = gridDistrictMapper.getGridDistrictByDistrictName(gridDistrict);
        System.out.println(gridDistrict);
    }
    @Test
    public void testGetGridDistrictByDistrictId() {
        GridDistrict gridDistrict = new GridDistrict();
        gridDistrict.setDistrictId(1101010);
        gridDistrict = gridDistrictMapper.getGridDistrictByDistrictId(gridDistrict);
        System.out.println(gridDistrict);
    }
    @Test
    public void testListGridDistrict() {
        GridDistrict gridDistrict = new GridDistrict();
        List<GridDistrict> gridDistrict1 = gridDistrictMapper.listGridDistrict(gridDistrict);
        System.out.println(gridDistrict1);
    }
    @Test
    public void testGetGridDistrictByCityId() {
        GridDistrict gridDistrict = new GridDistrict();
        gridDistrict.setCityId(1101000);
        List<GridDistrict> gridDistrict1 = gridDistrictMapper.getGridDistrictByCityId(gridDistrict);
        System.out.println(gridDistrict1);
    }
}
