package org.mgcx.nepm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mgcx.nepm.entity.Statistics;
import org.mgcx.nepm.mapper.StatisticsMapper;
import org.mgcx.nepm.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements IStatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;


    @Override
    public int saveStatistics(Statistics statistics) {
        return statisticsMapper.saveStatistics(statistics);
    }

    @Override
    public List<Statistics> listStatistics(Statistics statistics) {
        return statisticsMapper.listStatistics(statistics);
    }

    @Override
    public Statistics getStatisticsById(Statistics statistics) {
        return statisticsMapper.getStatisticsById(statistics);
    }

    @Override
    public Statistics modifyStatistics(Statistics statistics) {
        statisticsMapper.modifyStatistics(statistics);
        return statisticsMapper.getStatisticsById(statistics);
    }

    @Override
    public List<Statistics> getConfirmedStatsData() {
        return statisticsMapper.getConfirmedStatsData();
    }

    @Override
    public Statistics getStatsById(Integer sId) {
        return statisticsMapper.getStatsById(sId);
    }

    @Override
    public List<Statistics> confirmedStatsSearch(Statistics statistics) {
        return statisticsMapper.confirmedStatsSearch(statistics);
    }

}
