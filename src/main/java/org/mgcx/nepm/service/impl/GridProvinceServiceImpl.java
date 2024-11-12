package org.mgcx.nepm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mgcx.nepm.entity.GridProvince;
import org.mgcx.nepm.mapper.GridProvinceMapper;
import org.mgcx.nepm.service.IGridProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridProvinceServiceImpl extends ServiceImpl<GridProvinceMapper, GridProvince> implements IGridProvinceService {

    @Autowired
    GridProvinceMapper gridProvinceMapper;

    @Override
    public GridProvince getGridProvinceById(GridProvince gridProvince) {
        return gridProvinceMapper.getGridProvinceById(gridProvince);
    }

    @Override
    public int saveGridProvince(GridProvince gridProvince) {
        return gridProvinceMapper.saveGridProvince(gridProvince);
    }

    @Override
    public int updateGridProvince(GridProvince gridProvince) {
        return gridProvinceMapper.updateGridProvince(gridProvince);
    }

    @Override
    public GridProvince getGridProvinceByName(GridProvince gridProvince) {
        return gridProvinceMapper.getGridProvinceByName(gridProvince);
    }

    @Override
    public GridProvince getGridProvinceByAbbr(GridProvince gridProvince) {
        return gridProvinceMapper.getGridProvinceByAbbr(gridProvince);
    }

    @Override
    public List<GridProvince> listGridProvince(GridProvince gridProvince) {
        return gridProvinceMapper.listGridProvince(gridProvince);
    }

    @Override
    public List<GridProvince> getAllGridProvince() {
        return gridProvinceMapper.getAllGridProvince();
    }

    @Override
    public List<GridProvince> getAllProvince() {
        return gridProvinceMapper.getAllProvince();
    }
}
