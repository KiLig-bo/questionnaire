package org.mgcx.nepm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mgcx.nepm.entity.Aqi;

import java.util.List;

/**
* @author 赵福强
* @description 针对表【aqi】的数据库操作Mapper
* @createDate 2024-05-21 11:18:09
* @Entity org.mgcx.nepm.entity.Aqi
*/
@Mapper
public interface AqiMapper extends BaseMapper<Aqi> {
    Aqi getAqiById(Aqi aqi);
    List<Aqi> listAqi(Aqi aqi);

    Aqi getAqiByAqiId(Integer aqiId);
}




