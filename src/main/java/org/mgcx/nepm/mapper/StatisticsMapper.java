package org.mgcx.nepm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mgcx.nepm.entity.Statistics;

import java.util.List;

/**
* @author 赵福强
* @description 针对表【statistics(空气质量检测统计表)】的数据库操作Mapper
* @createDate 2024-05-21 11:18:37
* @Entity org.mgcx.nepm.entity.Statistics
*/
@Mapper
public interface StatisticsMapper extends BaseMapper<Statistics> {
    // 保存网格员提交的AQI确认信息
    int saveStatistics(Statistics statistics);

    // 查询确认AQI信息列表，并有模糊查询和分页功能
    List<Statistics> listStatistics(Statistics statistics);

    Statistics getStatisticsById(Statistics statistics);

    int modifyStatistics(Statistics statistics);

    List<Statistics> getConfirmedStatsData();

    Statistics getStatsById(Integer sId);

    List<Statistics> confirmedStatsSearch(Statistics statistics);
}




