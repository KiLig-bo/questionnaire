package org.mgcx.nepm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mgcx.nepm.entity.GridDistrict;
import org.mgcx.nepm.mapper.GridDistrictMapper;
import org.mgcx.nepm.service.IGridDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridDistrictServiceImpl extends ServiceImpl<GridDistrictMapper, GridDistrict> implements IGridDistrictService{

    @Autowired
    GridDistrictMapper gridDistrictMapper;
    @Override
    public GridDistrict getGridDistrictByDistrictId(GridDistrict gridDistrict) {
        return gridDistrictMapper.getGridDistrictByDistrictId(gridDistrict);
    }

    @Override
    public GridDistrict getGridDistrictByDistrictName(GridDistrict gridDistrict) {
        return gridDistrictMapper.getGridDistrictByDistrictName(gridDistrict);
    }

    @Override
    public List<GridDistrict> getGridDistrictByCityId(GridDistrict gridDistrict) {
        return gridDistrictMapper.getGridDistrictByCityId(gridDistrict);
    }

    @Override
    public List<GridDistrict> listGridDistrict(GridDistrict gridDistrict) {
        return gridDistrictMapper.listGridDistrict(gridDistrict);
    }

}
