package org.mgcx.nepm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mgcx.nepm.entity.Statistics;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */

public interface IStatisticsService extends IService<Statistics> {

    // 保存网格员提交的AQI确认信息
    int saveStatistics(Statistics statistics);

    // 查询确认AQI信息列表，并有模糊查询和分页功能
    List<Statistics> listStatistics(Statistics statistics);

    Statistics getStatisticsById(Statistics statistics);

    Statistics modifyStatistics(Statistics statistics);


    List<Statistics> getConfirmedStatsData();

    Statistics getStatsById(Integer param);

    List<Statistics> confirmedStatsSearch(Statistics statistics);
}
