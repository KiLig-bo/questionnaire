package org.mgcx.nepm.controller;


import org.mgcx.nepm.entity.Statistics;
import org.mgcx.nepm.service.IStatisticsService;
import org.mgcx.nepm.service.RedisService;
import org.mgcx.nepm.util.CommonResult;
import org.mgcx.nepm.util.DealedDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/data")
public class StatisticsController {

    @Autowired
    IStatisticsService statisticsService;
    @Autowired
    RedisService rateLimiterService;

    // 在Controller中使用改进的RateLimiterService


    @GetMapping("/listStatistics")
    public List<Statistics> ListStatistics(){
        return statisticsService.listStatistics(new Statistics());
    }
    @GetMapping("/getStatisticsById")
    public Statistics GetStatisticsById(@RequestBody Statistics statistics){
        return statisticsService.getStatisticsById(statistics);
    }
    @GetMapping("/modifyStatistics")
    public Statistics ModifyStatistics(@RequestBody Statistics statistics){
        return statisticsService.modifyStatistics(statistics);
    }

    @GetMapping("/getConfirmedStatsData")
    public CommonResult GetConfirmedStatsData(){
        List <Map<String,Object>> confirmedStatsData= new ArrayList<>();
        List<Statistics> statisticsList= statisticsService.getConfirmedStatsData();
        if(statisticsList.size()==0){
            return CommonResult.failed();
        }
        for (Statistics statistics:statisticsList){
            Map<String,Object> map=new HashMap<>();
            map.put("sId",statistics.getSId());
            map.put("confirmationDate",new SimpleDateFormat("yyyy-MM-dd").format(statistics.getConfirmTimestamp()));
            map.put("confirmationTime",new SimpleDateFormat("HH:mm").format(statistics.getConfirmTimestamp()));
            map.put("gmName",statistics.getGridManager().getUsername());
            map.put("spName",statistics.getFeedbacker().getUsername());
            map.put("sProvince", DealedDateUtil.DealedWithProvince(statistics.getProvince()));
            map.put("sCity",DealedDateUtil.DealedWithCity(statistics.getCity()));
            map.put("sDistrict",DealedDateUtil.DealedWithDistrict(statistics.getDistrict()));
            map.put("confirmedGrade",DealedDateUtil.DealedWithEstimatedGrade(statistics.getAqi()));
            confirmedStatsData.add(map);

        }
        return CommonResult.success(confirmedStatsData);
    }


    /**
     * 根据sId,返回对应数据
     */
    @RequestMapping("/getStatsById")
    public CommonResult GetStatsById(@RequestBody Map<String,Integer> request){
        Integer param=request.get("sId");
        Statistics statistics=statisticsService.getStatsById(param);
        if(statistics==null){
            return CommonResult.failed();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sId", statistics.getSId());
        map.put("supervisor", DealedWithSupervisor(statistics));
        map.put("sProvince", DealedDateUtil.DealedWithProvince(statistics.getProvince()));
        map.put("sCity", DealedDateUtil.DealedWithCity(statistics.getCity()));
        map.put("sDistrict",DealedDateUtil.DealedWithDistrict(statistics.getDistrict()));
        map.put("sAddress", statistics.getAddress());
        map.put("confirmedGrade", DealedDateUtil.DealedWithEstimatedGrade(statistics.getAqi()));
//       // statistics.setRemarks("https://cdn.jsdelivr.net/gh/Mouse-Bill/PicPicGo/uPic/static/xZecUT.jpg");
        String[] confirmedPics = statistics.getRemarks().split(",");
        map.put("confirmedPics", confirmedPics);
        map.put("gridManager",DealedWithGridManager(statistics));
        map.put("confirmedTimestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(statistics.getConfirmTimestamp()));
        return CommonResult.success(map);



    }
    public Map<String,Object> DealedWithGridManager(Statistics statistics){
        Map<String, Object> map = new HashMap<>();
        if (statistics.getGridManager() != null) {
            map.put("gmId", statistics.getGmId());
            map.put("gmName", statistics.getGridManager().getUsername());
            map.put("gmTel", statistics.getGridManager().getTel());
            map.put("gmAssignTimestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(statistics.getConfirmTimestamp()));
            map.put("gmFeedbackInfo", statistics.getInformation());
        }
        return map;
    }

    public Map<String,Object> DealedWithSupervisor(Statistics statistics){
        Map<String, Object> map = new HashMap<>();
        if (statistics.getFeedbacker() != null) {
            map.put("spId",statistics.getFeedbacker().getUserId());
            map.put("spName", statistics.getFeedbacker().getUsername());
            map.put("spTel", statistics.getFeedbacker().getTel());

            map.put("spAge", LocalDate.now().getYear()-LocalDate.parse(statistics.getFeedbacker().getBirthday()).getYear());
            map.put("spSex", statistics.getFeedbacker().getSex());
        }
        return map;
    }

    @GetMapping("/confirmedStatsSearch")
    public CommonResult ConfirmedStatsSearch(@RequestBody Map<String, Object> request){
        Statistics statistics=new Statistics();
        statistics.setSId((Integer)request.get("sId"));
        statistics.setProvinceId((Integer)request.get("provinceId"));
        statistics.setCityId((Integer)request.get("cityId"));
        statistics.setDistrictId((Integer)request.get("districtId"));
        statistics.setAddress((String)request.get("address"));
        statistics.setAqiId((Integer)request.get("aqiId"));
        statistics.setGmId((String)request.get("gmId"));
        statistics.setFdId((String)request.get("fdId"));
        statistics.setConfirmTimestamp((Date)request.get("confirmTimestamp"));

        List<Map<String,Object>> list=new ArrayList<>();

        List<Statistics> confirmedStatsData=statisticsService.confirmedStatsSearch(statistics);
        for(Statistics statistics1:confirmedStatsData){
            Map<String, Object> map = new HashMap<>();
            map.put("sId", statistics1.getSId());
            map.put("sProvince", DealedDateUtil.DealedWithProvince(statistics1.getProvince()));
            map.put("sCity", DealedDateUtil.DealedWithCity(statistics1.getCity()));
            map.put("sDistrict",DealedDateUtil.DealedWithDistrict(statistics1.getDistrict()));
            map.put("sAddress", statistics1.getAddress());
            map.put("confirmedGrade", DealedDateUtil.DealedWithEstimatedGrade(statistics1.getAqi()));
            System.out.println(1111111111);
            System.out.println(statistics1.getGridManager());
            System.out.println(statistics1.getFeedbacker());
            System.out.println(statistics1.getConfirmTimestamp());
            System.out.println(1111111111);
            map.put("gmName",statistics1.getGridManager().getRealName());
            map.put("spName",statistics1.getFeedbacker().getRealName());
            map.put("confirmationDate",new SimpleDateFormat("yyyy-MM-dd").format(statistics1.getConfirmTimestamp()));
            map.put("confirmationTime",new SimpleDateFormat("HH:mm").format(statistics1.getConfirmTimestamp()));


            list.add(map);
        }
        return CommonResult.success(list);
    }
}
