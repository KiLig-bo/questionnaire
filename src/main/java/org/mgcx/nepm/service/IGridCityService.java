package org.mgcx.nepm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mgcx.nepm.entity.GridCity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IGridCityService  extends IService<GridCity> {
    GridCity getGridCityByCityId(GridCity gridCity);
    GridCity getGridCityByCityName(GridCity gridCity);
    List<GridCity> getGridCityByProvinceId(GridCity gridCity);
    List<GridCity> listGridCity(GridCity gridCity);
}
