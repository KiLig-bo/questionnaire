package org.mgcx.nepm.mapper;

import org.junit.jupiter.api.Test;
import org.mgcx.nepm.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StatisticsMapperTest {
        @Autowired
        StatisticsMapper statisticsMapper;

        @Test
        public void testSaveStatistics() {

            Statistics statistics = new Statistics();
            statistics.setSId(8);
            // Arrange
            Statistics statistics1 = statisticsMapper.getStatisticsById(statistics);
            statistics1.setSId(333);
            int result = statisticsMapper.saveStatistics(statistics1);
            System.out.println(result);
        }

        @Test
        public void testListStatistics() {
            // Arrange
            Statistics statistics = new Statistics();
            List<Statistics> expectedList = new ArrayList<>();
            expectedList.add(statistics);
            List<Statistics> resultList = statisticsMapper.listStatistics(statistics);
            System.out.println(resultList);
        }

        @Test
        public void testGetStatisticsById() {
            // Arrange
            Statistics statistics = new Statistics();
            statistics.setSId(8);
            // Act
            Statistics getStatistics = statisticsMapper.getStatisticsById(statistics);
            System.out.println(getStatistics);

            getStatistics.setRemarks("修改测试");
            // Act
            int modStatistics = statisticsMapper.modifyStatistics(getStatistics);
            Statistics getStatistics1 = statisticsMapper.getStatisticsById(statistics);
            System.out.println(getStatistics1.getRemarks());


        }

        @Test
        public void testSearchStatistics() {
            Statistics statistics = new Statistics();
            statistics.setSId(8);

            List<Statistics> list = statisticsMapper.confirmedStatsSearch(statistics);
            System.out.println(list);
            System.out.println(list.get(0).getConfirmTimestamp());
            System.out.println(list.get(0).getFdId());
            System.out.println(list.get(0).getGmId());
            System.out.println(list.get(0).getFeedbacker());
            System.out.println(list.get(0).getGridManager());
        }

}
