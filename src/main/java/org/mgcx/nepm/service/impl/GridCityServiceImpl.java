package org.mgcx.nepm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mgcx.nepm.entity.GridCity;
import org.mgcx.nepm.mapper.GridCityMapper;
import org.mgcx.nepm.service.IGridCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCityServiceImpl extends ServiceImpl<GridCityMapper, GridCity> implements IGridCityService{

    @Autowired
    GridCityMapper gridCityMapper;
    @Override
    public GridCity getGridCityByCityId(GridCity gridCity) {
        return gridCityMapper.getGridCityByCityId(gridCity);
    }

    @Override
    public GridCity getGridCityByCityName(GridCity gridCity) {
        return gridCityMapper.getGridCityByCityName(gridCity);
    }

    @Override
    public List<GridCity> getGridCityByProvinceId(GridCity gridCity) {
        return gridCityMapper.getGridCityByProvinceId(gridCity);
    }

    @Override
    public List<GridCity> listGridCity(GridCity gridCity) {
        return gridCityMapper.listGridCity(gridCity);
    }
}
