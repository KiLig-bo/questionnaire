package org.mgcx.nepm.controller;

import org.mgcx.nepm.entity.*;
import org.mgcx.nepm.service.IAqiFeedbackService;
import org.mgcx.nepm.service.IUserService;
import org.mgcx.nepm.util.CommonResult;
import org.mgcx.nepm.util.DealedDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.mgcx.nepm.util.CommonResult.success;

@CrossOrigin
@RestController
@RequestMapping("/data")
public class AqiFeedbackController {

    @Autowired
    IAqiFeedbackService aqiFeedbackService;
    @Autowired
    IUserService userService;

    @RequestMapping("/getAfData")
    public CommonResult getAfData() throws ParseException {

        List<Map<String, Object>> result = DealedWithData(aqiFeedbackService.sellectAllAqiFeedBack());
        if(result.size() == 0){
            return CommonResult.failed();
        }
        return success(result);

    }

    /**
     * feedback整体信息
     */
    @RequestMapping("/getAfInfoById")
    public CommonResult getAfInfoById(@RequestBody Map<String, Integer> request) throws ParseException {
        Integer param = request.get("afId");
        AqiFeedback aqiFeedback = aqiFeedbackService.selectAqiFeedbackById(param);
        if(aqiFeedback == null){
            return CommonResult.failed();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("afId", aqiFeedback.getAfId());
        map.put("supervisor", DealedWithSupervisor(aqiFeedback));
        map.put("afProvince", DealedDateUtil.DealedWithProvince(aqiFeedback.getGridProvince()));
        map.put("afCity", DealedDateUtil.DealedWithCity(aqiFeedback.getGridCity()));
        map.put("afDistrict", DealedDateUtil.DealedWithDistrict(aqiFeedback.getGridDistrict()));
        map.put("afAddress", aqiFeedback.getAddress());
        map.put("estimatedGrade", DealedDateUtil.DealedWithEstimatedGrade(aqiFeedback.getAqi()));
        map.put("afStatus", DealedDateUtil.DealedWithAqiFeedbackState(aqiFeedback.getState()));
        map.put("afDescription", aqiFeedback.getInformation());
        String[] afPics = aqiFeedback.getRemarks().split(",");
        map.put("afPics", afPics);
        map.put("gridManager", DealedWithGridManager(aqiFeedback));
        map.put("afTimestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(aqiFeedback.getAfTimestamp()));
        return success(map);

    }


    /**
     * 封装方法，用来对回传数据进行规定格式的处理
     */
    public List<Map<String, Object>> DealedWithData(List<AqiFeedback> aqiFeedbacks) throws ParseException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AqiFeedback aqiFeedback : aqiFeedbacks) {
            Map<String, Object> map = new HashMap<>();
            map.put("afId", aqiFeedback.getAfId());
            map.put("spName", aqiFeedback.getUser().getUsername());
            map.put("afProvince", DealedDateUtil.DealedWithProvince(aqiFeedback.getGridProvince()));
            map.put("afCity", DealedDateUtil.DealedWithCity(aqiFeedback.getGridCity()));
            map.put("afDistrict", DealedDateUtil.DealedWithDistrict(aqiFeedback.getGridDistrict()));
            map.put("afAddress", aqiFeedback.getAddress());
            map.put("estimatedGrade", DealedDateUtil.DealedWithEstimatedGrade(aqiFeedback.getAqi()));
            map.put("afStatus", DealedDateUtil.DealedWithAqiFeedbackState(aqiFeedback.getState()));
            map.put("afDate", new SimpleDateFormat("yyyy-MM-dd").format(aqiFeedback.getAfTimestamp()));
            map.put("afTime", new SimpleDateFormat("HH:mm").format(aqiFeedback.getAfTimestamp()));
            list.add(map);
        }
        return list;


    }

    public Map<String, Object> DealedWithGridManager(AqiFeedback aqiFeedback) {
        Map<String, Object> map = new HashMap<>();
        if (aqiFeedback.getGridManager() != null) {
            map.put("gmId", aqiFeedback.getGmId());
            map.put("gmName", aqiFeedback.getGridManager().getUsername());
            map.put("gmTel", aqiFeedback.getGridManager().getTel());
            map.put("gmAssignTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(aqiFeedback.getAssignTimestamp()));
            // map.put("gmState", new AfStatus(aqiFeedback.getState()));
        }
        return map;
    }

    public Map<String, Object> DealedWithSupervisor(AqiFeedback aqiFeedback) {
        Map<String, Object> map = new HashMap<>();
        if (aqiFeedback.getUser() != null) {
            map.put("spId", aqiFeedback.getUser().getUserId());
            map.put("spName", aqiFeedback.getUser().getUsername());
            map.put("spTel", aqiFeedback.getUser().getTel());
            map.put("spAge", LocalDate.now().getYear() - LocalDate.parse(aqiFeedback.getUser().getBirthday()).getYear());
            map.put("spSex", aqiFeedback.getUser().getSex());
        }
        return map;
    }

    @PostMapping("/assignGridManager")
    public CommonResult assignGridManager(@RequestBody Map<String, String> request) {
        int afId = Integer.parseInt(request.get("afId"));
        String gmId = request.get("gmId");
        //System.out.println("afId:" + afId);
        User gm = userService.getUserByUserId(gmId);
        AqiFeedback aqiFeedback1 = new AqiFeedback();
        aqiFeedback1.setAfId(afId);
        AqiFeedback aqiFeedback = aqiFeedbackService.getAqiFeedbackById(aqiFeedback1);
        int afState = aqiFeedback.getState();
        int gmState = gm.getState();
        Map<String, Object> returnMap = new HashMap<>();
        if (afState == 0 && gmState == 0) {
            aqiFeedback.setState(1);
            aqiFeedback.setAssignTimestamp(new Date());
            gm.setState(1);
            aqiFeedback.setGmId(gmId);
            aqiFeedbackService.modifyAqiFeedback(aqiFeedback);
            userService.updateUser(gm);
            AqiFeedback aqiFeedback2 = aqiFeedbackService.selectAqiFeedbackById(afId);
            if (aqiFeedback2 == null) {
                returnMap.put("success", false);
                return success(returnMap);
            }
            User gm2 = userService.getUserByUserId(gmId);
            System.out.println(aqiFeedback2);
            System.out.println(gm2.getState());
            returnMap.put("success", true);
            return success(returnMap);
        } else if (afState == 1) {
            System.out.println("指派失败,任为已经指派");
            returnMap.put("success", false);

        } else if (gmState == 1) {
            System.out.println("指派失败,网格员已经被指派了");
            returnMap.put("success", false);

        } else {
            System.out.println("指派失败,未知错误");
            returnMap.put("success", false);

        }
        return success(returnMap);
    }

    @GetMapping("/aqiFeedbackSearch")
    public CommonResult aqiFeedbackSearch(@RequestBody Map<String, Object> request){
        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setAfId((Integer)request.get("afId"));
        aqiFeedback.setSpId((String)request.get("spId"));
        aqiFeedback.setProvinceId((Integer)request.get("provinceId"));
        aqiFeedback.setCityId((Integer)request.get("cityId"));
        aqiFeedback.setDistrictId((Integer)request.get("districtId"));
        aqiFeedback.setAddress((String)request.get("address"));
        aqiFeedback.setInformation((String)request.get("information"));
        aqiFeedback.setEstimatedGrade((Integer)request.get("estimatedGrade"));
        aqiFeedback.setAfTimestamp((Date)request.get("afTimestamp"));
        aqiFeedback.setGmId((String)request.get("gmId"));
        aqiFeedback.setAssignTimestamp((Date)request.get("assignTimestamp"));
        aqiFeedback.setEstimatedGrade((Integer)request.get("state"));
        aqiFeedback.setRemarks((String)request.get("remarks"));

        List<AqiFeedback> aqiFeedbacks =aqiFeedbackService.searchAqiFeedBack(aqiFeedback);

        List<Map<String, Object>> list = new ArrayList<>();
        for (AqiFeedback aqiFeedback1 : aqiFeedbacks) {
            Map<String, Object> map = new HashMap<>();
            map.put("afId", aqiFeedback1.getAfId());
            map.put("spName", aqiFeedback1.getUser().getRealName());
            map.put("afProvince", DealedDateUtil.DealedWithProvince(aqiFeedback1.getGridProvince()));
            map.put("afCity", DealedDateUtil.DealedWithCity(aqiFeedback1.getGridCity()));
            map.put("afDistrict", DealedDateUtil.DealedWithDistrict(aqiFeedback1.getGridDistrict()));
            map.put("afAddress", aqiFeedback1.getAddress());
            map.put("information", aqiFeedback1.getInformation());
            map.put("remarks", aqiFeedback1.getRemarks());
            map.put("estimatedGrade", DealedDateUtil.DealedWithEstimatedGrade(aqiFeedback1.getAqi()));
            map.put("afStatus", DealedDateUtil.DealedWithAqiFeedbackState(aqiFeedback1.getState()));
            map.put("afDate", new SimpleDateFormat("yyyy-MM-dd").format(aqiFeedback1.getAfTimestamp()));
            map.put("afTime", new SimpleDateFormat("HH:mm").format(aqiFeedback1.getAfTimestamp()));
            map.put("assignDate", new SimpleDateFormat("yyyy-MM-dd").format(aqiFeedback1.getAssignTimestamp()));
            map.put("assignTime", new SimpleDateFormat("HH:mm").format(aqiFeedback1.getAssignTimestamp()));
            list.add(map);
        }
        return CommonResult.success(list);
    }

    /**
     * 写入feedback
     * @param request
     * @return commonResult 200
     */
    @RequestMapping("/submitFeedback")
    public CommonResult submitFeedback(@RequestBody Map<String, Object> request){


        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setSpId((String)request.get("userId"));
        aqiFeedback.setProvinceId(Integer.parseInt((String) request.get("provinceId")));
        aqiFeedback.setCityId(Integer.parseInt((String)request.get("cityId")));
        aqiFeedback.setDistrictId(Integer.parseInt((String)request.get("districtId")));
        aqiFeedback.setAddress((String)request.get("address"));
        aqiFeedback.setInformation((String) request.get("description"));
        aqiFeedback.setEstimatedGrade((Integer)request.get("estimatedGrade"));
        //获取当前系统时间，timestamp
        aqiFeedback.setAfTimestamp(new Date());
        //输出
        //System.out.println(aqiFeedback.getAfTimestamp());
        aqiFeedback.setState(0);
        aqiFeedback.setRemarks(request.get("images").toString().substring(1,request.get("images").toString().length()-1));
        int condition = aqiFeedbackService.saveAqiFeedbackId(aqiFeedback);
        if(condition == 1){
            return CommonResult.success("write successfully");
        }
        return CommonResult.failed();
    }

    /**
     * 根据spid 返回feedback
     */
    @RequestMapping("/getAfDataBySpId")
    public CommonResult getAfDataBySpId(@RequestBody Map<String, String> request) throws ParseException {
        String spId = request.get("spId");
        List<AqiFeedback> aqiFeedbacks = aqiFeedbackService.selectAqiFeedbackBySpId(spId);
        if(aqiFeedbacks.size() == 0){
            return CommonResult.failed();
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (AqiFeedback aqiFeedback : aqiFeedbacks) {
            Map<String, Object> map = new HashMap<>();
            map.put("afId", aqiFeedback.getAfId());
            map.put("supervisor", DealedWithSupervisor(aqiFeedback));
            map.put("afProvince", DealedDateUtil.DealedWithProvince(aqiFeedback.getGridProvince()));
            map.put("afCity", DealedDateUtil.DealedWithCity(aqiFeedback.getGridCity()));
            map.put("afDistrict", DealedDateUtil.DealedWithDistrict(aqiFeedback.getGridDistrict()));
            map.put("afAddress", aqiFeedback.getAddress());
            map.put("estimatedGrade", DealedDateUtil.DealedWithEstimatedGrade(aqiFeedback.getAqi()));
            map.put("afStatus", DealedDateUtil.DealedWithAqiFeedbackState(aqiFeedback.getState()));
            map.put("afDescription", aqiFeedback.getInformation());
            String[] afPics = aqiFeedback.getRemarks().split(",");
            map.put("afPics", afPics);
            map.put("gridManager", DealedWithGridManager(aqiFeedback));
            map.put("afTimestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(aqiFeedback.getAfTimestamp()));
            result.add(map);
        }
        return success(result);
    }
}


