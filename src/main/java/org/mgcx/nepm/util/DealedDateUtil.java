package org.mgcx.nepm.util;

import org.mgcx.nepm.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealedDateUtil {

    void DealedDateUtil() {
    }
    /**
     * 处理回传数据，province、
     **/
    public static Map<String, Object> DealedWithProvince(GridProvince gridProvince) {
        Map<String, Object> map = new HashMap<>();
        if (gridProvince != null) {
            map.put("provinceId", gridProvince.getProvinceId());
            map.put("provinceName", gridProvince.getProvinceName());
        }
        return map;
    }

    /**
     * 处理回传数据，、city、district、estimatedGrade
     */
    public static Map<String, Object> DealedWithCity(GridCity gridCity) {
        Map<String, Object> map = new HashMap<>();
        if (gridCity != null) {
            map.put("cityId", gridCity.getCityId());
            map.put("cityName", gridCity.getCityName());
        }
        return map;
    }

    public static Map<String, Object> DealedWithDistrict(GridDistrict gridDistrict) {
        Map<String, Object> map = new HashMap<>();
        if (gridDistrict != null) {
            map.put("districtId", gridDistrict.getDistrictId());
            map.put("districtName", gridDistrict.getDistrictName());
        }
        return map;
    }

    public static Map<String, Object> DealedWithEstimatedGrade(Aqi aqi) {
        Map<String, Object> map = new HashMap<>();
        if (aqi != null) {
            map.put("grade", aqi.getAqiId());
            map.put("showName", aqi.getLevel());
            map.put("showColor", aqi.getColor());
            map.put("chineseExplain", aqi.getChineseExplain());

        }
        return map;
    }
    public static Map<String,Object> DealedWithGridManager(Statistics statistics){
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

    public static Map<String,Object> DealedWithSupervisor(Statistics statistics){
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



    public static Map<String, Object> DealedWithRole(Integer role) {
        Map<String, Object> map = new HashMap<>();
        if (role == 0) {
            map.put("roleName", "管理员");
            map.put("roleId", role);
        } else if (role == 1) {
            map.put("roleName", "网格员");
            map.put("roleId", role);
        } else {
            map.put("roleName", "公众");
            map.put("roleId", role);
        }

        return map;
    }

    public static Map<String, Object> DealedWithUserState(User user) {
        Map<String, Object> map = new HashMap<>();
        int state = user.getState();
        if(user.getRole() == 0){
            if (state == 0) {
                map.put("stateName", "启用");
                map.put("stateId", state);
            } else {
                map.put("stateName", "禁用");
                map.put("stateId", state);
            }
        }else if(user.getRole() == 1){
            if (state == 0) {
                map.put("showName", "空闲");
                map.put("status", state);
            } else if (state == 1) {
                map.put("showName", "工作中");
                map.put("status", state);
            } else if (state == 2) {
                map.put("showName", "休假中");
                map.put("status", state);
            } else {
                map.put("showName", "其他");
                map.put("status", state);
            }
        }

        return map;
    }

    public static Map<String, Object> DealedWithAqiFeedbackState(Integer state) {
        Map<String, Object> map = new HashMap<>();
        if (state == 0) {
            map.put("showName", "未指派");
            map.put("status", state);
        } else if( state == 1) {
            map.put("showName", "已指派");
            map.put("status", state);
        }else{
            map.put("showName", "指派失败");
        }
        return map;
    }


    public static Map<String, Object> DealedWithSex(Integer sex) {
        Map<String, Object> map = new HashMap<>();
        if (sex == 0) {
            map.put("sexName", "女");
            map.put("sexId", 0);
        } else {
            map.put("sexName", "男");
            map.put("sexId", 1);
        }
        return map;
    }

    public static Integer calculateAge(String birthday) {
        if (birthday == null) {
            return null;
        }
        try {
            LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ISO_LOCAL_DATE);
            int currentYear = LocalDate.now().getYear();
            return currentYear - birthDate.getYear();
        } catch (DateTimeParseException e) {
            // 处理生日格式错误的情况
            // 返回 null 或者返回默认年龄
            return null;
        }
    }

}
