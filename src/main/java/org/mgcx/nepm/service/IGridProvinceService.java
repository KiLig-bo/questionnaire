package org.mgcx.nepm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.mgcx.nepm.entity.GridProvince;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IGridProvinceService extends IService<GridProvince> {
    GridProvince getGridProvinceById(GridProvince gridProvince);
    int saveGridProvince(GridProvince gridProvince);
    int updateGridProvince(GridProvince gridProvince);
    GridProvince getGridProvinceByName(GridProvince gridProvince);
    GridProvince getGridProvinceByAbbr(GridProvince gridProvince);

    List<GridProvince> listGridProvince(GridProvince gridProvince);

    List<GridProvince> getAllGridProvince();

    List<GridProvince> getAllProvince();
}
