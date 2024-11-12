package org.mgcx.nepm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mgcx.nepm.entity.GridProvince;
import org.mgcx.nepm.entity.Statistics;

import java.util.List;

/**
* @author 赵福强
* @description 针对表【grid_province】的数据库操作Mapper
* @createDate 2024-05-21 11:18:33
* @Entity org.mgcx.nepm.entity.GridProvince
*/

@Mapper
public interface GridProvinceMapper extends BaseMapper<GridProvince> {
    GridProvince getGridProvinceById(GridProvince gridProvince);
    int saveGridProvince(GridProvince gridProvince);
    int updateGridProvince(GridProvince gridProvince);
    GridProvince getGridProvinceByName(GridProvince gridProvince);
    GridProvince getGridProvinceByAbbr(GridProvince gridProvince);

    List<GridProvince> listGridProvince(GridProvince gridProvince);

    GridProvince getGridProvinceByProvinceId(Integer provinceId);

    List<GridProvince> getAllGridProvince();

    List<GridProvince> getAllProvince();
}




