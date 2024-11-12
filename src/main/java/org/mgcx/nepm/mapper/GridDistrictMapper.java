package org.mgcx.nepm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.mgcx.nepm.entity.GridDistrict;

import java.util.List;

@Mapper
public interface GridDistrictMapper extends BaseMapper<GridDistrict> {
    GridDistrict getGridDistrictByDistrictId(GridDistrict gridDistrict);
    GridDistrict getGridDistrictByDistrictName(GridDistrict gridDistrict);
    List<GridDistrict> getGridDistrictByCityId(GridDistrict gridDistrict);
    List<GridDistrict> listGridDistrict(GridDistrict gridDistrict);
    GridDistrict getGridDistrictById(Integer districtId);

    List<GridDistrict> getAllDistrictByCityId(Integer cityId);
}
