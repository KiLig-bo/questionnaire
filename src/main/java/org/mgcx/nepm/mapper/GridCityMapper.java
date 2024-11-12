package org.mgcx.nepm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mgcx.nepm.entity.GridCity;

import java.util.List;

/**
* @author 赵福强
* @description 针对表【grid_city(系统网格覆盖市区域表)】的数据库操作Mapper
* @createDate 2024-05-21 11:18:28
* @Entity org.mgcx.nepm.entity.GridCity
*/

@Mapper
public interface GridCityMapper extends BaseMapper<GridCity> {
    GridCity getGridCityByCityId(GridCity gridCity);
    GridCity getGridCityByCityName(GridCity gridCity);
    List<GridCity> getGridCityByProvinceId(GridCity gridCity);
    List<GridCity> listGridCity(GridCity gridCity);

    GridCity getGridCityById(Integer cityId);

    List<GridCity> getAllCityByProvinceId(Integer provinceId);
}




