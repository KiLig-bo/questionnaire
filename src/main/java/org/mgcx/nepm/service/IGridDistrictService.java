package org.mgcx.nepm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.mgcx.nepm.entity.GridDistrict;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IGridDistrictService  extends IService<GridDistrict> {
    GridDistrict getGridDistrictByDistrictId(GridDistrict gridDistrict);
    GridDistrict getGridDistrictByDistrictName(GridDistrict gridDistrict);
    List<GridDistrict> getGridDistrictByCityId(GridDistrict gridDistrict);
    List<GridDistrict> listGridDistrict(GridDistrict gridDistrict);
}