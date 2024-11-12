package org.mgcx.nepm.controller;

import org.mgcx.nepm.entity.GridCity;
import org.mgcx.nepm.entity.GridDistrict;
import org.mgcx.nepm.entity.GridProvince;
import org.mgcx.nepm.service.IGridProvinceService;
import org.mgcx.nepm.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/data")
public class GridProvinceController {
    @Autowired
    IGridProvinceService gridProvinceService;
    /**
     * gridData
     */
    @RequestMapping("/getGridData")
        public CommonResult getGridData() {
        List<Map<String, Object>> list = new ArrayList<>();
        if(gridProvinceService.getAllGridProvince().size() == 0){
            return CommonResult.failed();
        }
        for (GridProvince gridProvince : gridProvinceService.getAllGridProvince()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", gridProvince.getProvinceId());
            map.put("label", gridProvince.getProvinceName());
            map.put("children", getDealedWithCity(gridProvince.getChildren()));
            list.add(map);

        }
        return CommonResult.success(list);
    }

    public List<Map<String, Object>>getDealedWithCity(List<GridCity> gridCityList){
        List<Map<String, Object>> list = new ArrayList<>();
        for (GridCity gridCity : gridCityList) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", gridCity.getCityId());
            map.put("label", gridCity.getCityName());
            map.put("children", getDealedWithDistrict(gridCity.getChildren()));
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>>getDealedWithDistrict(List<GridDistrict>gridDistrictList ){
        List<Map<String, Object>> list = new ArrayList<>();
        for (GridDistrict gridDistrict : gridDistrictList) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", gridDistrict.getDistrictId());
            map.put("label", gridDistrict.getDistrictName());
            list.add(map);
        }
        return list;
    }


}
