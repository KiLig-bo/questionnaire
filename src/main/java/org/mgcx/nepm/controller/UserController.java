package org.mgcx.nepm.controller;
import com.aliyuncs.utils.StringUtils;
import org.mgcx.nepm.entity.User;
import org.mgcx.nepm.service.IUserService;
import org.mgcx.nepm.service.RedisService;
import org.mgcx.nepm.util.CommonResult;
import org.mgcx.nepm.util.DealedDateUtil;
import org.mgcx.nepm.util.ResponseResult;
import org.mgcx.nepm.util.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    RedisService redisService;
    private  String tokenId="TOKEN-USER-";
    private static final String PHONE_NUMBER_PATTERN = "^\\d{10,15}$";

    //查找网格员
    @GetMapping("/findUserByid")
    public User findUserByTid(@RequestBody User user) {
        return userService.getUserById(user);
    }

    @PostMapping("/updateUser")
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }


    /**
     * 一般用户登录
     * @return CommonResult
     */
    // TODO: 添加token鉴权
    @PostMapping("/login")
    public CommonResult login(@RequestBody Map<String, String> request) {
        String param = request.get("username");//获取用户名或者手机号
        String passwd = request.get("passwd");//获取密码
        User user = new User();
        //判断param是手机号还是用户名
        if (param.matches(PHONE_NUMBER_PATTERN)) {
            user.setTel(param);
        } else {
            user.setUsername(param);
        }
        user.setPassword(passwd);
        User user1 = userService.getUserByCodeByPass(user);
        if (user1 != null) {
            Map<String, String> payload = new HashMap<>();
            String username = user1.getUsername();
            payload.put("username", user1.getUsername());
            payload.put("password", user1.getPassword());
            String token = JWTUtil.getToken(payload);
            //存入redis
            redisService.set(username,token);//存入redis
            redisService.expire(username,60*5);//设置过期时间
            return CommonResult.success(user1,token);
        } else {
            return CommonResult.failed();
        }
    }


    /**
     * 返回所有管理员信息
     */
    @RequestMapping("/getAdminData")
    public CommonResult getAdminData(){
        List<Map<String, Object>> list = new ArrayList<>();
        List<User> adminList = userService.getAdminData();
        if (adminList.size() == 0) {
            return CommonResult.failed();
        }
        for (User admin : adminList) {

            Map<String, Object> map = new HashMap<>();
            map.put("userId", admin.getUserId());
            map.put("username", admin.getUsername());
            map.put("tel", admin.getTel());
            map.put("realName", admin.getRealName());
            map.put("role", DealedDateUtil.DealedWithRole(admin.getRole()));
            //if state是空的话，会报错，将其先设置为3
            if (admin.getState() == null) {
                admin.setState(3);
            }
            map.put("state", DealedDateUtil.DealedWithUserState(admin));
            list.add(map);
        }
        return CommonResult.success(list);
    }

    /**
     * 管理员登录
     *
     * @param {username,password}
     * @return user
     * @throws IOException
     */

    // TODO: 重构这个方法，将返回值改为 CommonResult 类型，同时验证管理员身份后再返回，以及添加 token 鉴权
    @PostMapping("/admin/login")
    public ResponseResult adminLogin(@RequestBody Map<String, String> request) throws IOException {
        String param = request.get("username");
        String password = request.get("passwd");
        if (param == null || password == null) {
            ResponseResult result = new ResponseResult(false, "用户名或密码不能为空", null);
            return result;
        } else {
            User user = new User();
            //判断param是手机号还是用户名,若是手机号，一定是0~9的数字
            if (param.matches(PHONE_NUMBER_PATTERN)) {
                user.setTel(param);
            } else {
                user.setUsername(param);
            }
            user.setPassword(password);
            ResponseResult result = new ResponseResult(true, userService.getUserByCodeByPass(user), null);
            return result;
        }

    }

    /**
     * 更新管理员信息
     */
    @RequestMapping("/updateAdminInfo")
    public CommonResult updateAdminInfo(@RequestBody Map<String, Object> request) {
        User admin = new User();
        Map<String, Object> map = new HashMap<>();
        if (request.get("userId") != "") {
            admin.setUserId((String) request.get("userId"));
            admin.setUsername((String) request.get("username"));
            admin.setRealName((String) request.get("realName"));
            admin.setTel((String) request.get("tel"));
            admin.setPassword((String) request.get("password"));
            admin.setState((Integer) request.get("state"));
            if (userService.updateUser(admin) != null) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } else {
            // 生成一个随机的 UUID
            UUID uuid = UUID.randomUUID();
            // 提取 UUID 的前 3 个十六进制数字，并转换为十进制数字
            int randomNumber = Integer.parseInt(uuid.toString().substring(0, 3), 16);
            admin.setUserId(String.format("uuid-%03d", randomNumber));
            admin.setUsername((String) request.get("username"));
            admin.setRealName((String) request.get("realName"));
            admin.setTel((String) request.get("tel"));
            admin.setPassword((String) request.get("password"));
            admin.setState((Integer) request.get("state"));
            if (userService.insertAdmin(admin) != 0) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }

        }
        return CommonResult.success(map);
    }



    /**
     * 根据区域id获取可用的网格员信息
     * @param {districtId, isCrossGrid}
     * @return
     * @throws ParseException
     */
    @PostMapping("/getGridManager")
    public CommonResult getGridManagerByDistrictId(@RequestBody Map<String, Object> request) throws ParseException {
        int districtId = (int) request.get("districtId");
        boolean isCrossGrid = (boolean) request.get("isCrossGrid");
        List<Map<String, Object>> list = new ArrayList<>();
        List<User> userList;
        if (isCrossGrid) {//如果是跨网格
            userList = userService.getGridManagerData();
            if (userList.size() == 0) {
                return CommonResult.failed();
            }
        } else {
            userList = userService.getGridManagerByDistrictId(districtId);
            //System.out.println(userList);
            if (userList.size() == 0) {
                return CommonResult.failed();
            }
        }
        for (User user : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("gmId", user.getUserId());
            map.put("gmName", user.getUsername());
            map.put("gmAge", DealedDateUtil.calculateAge(user.getBirthday()));
            map.put("gmSex", user.getSex());
            map.put("gmTel", user.getTel());
            map.put("gmProvince", user.getGridProvince());
            map.put("gmCity", user.getGridCity());
            map.put("gmDistrict", user.getGridDistrict());
            map.put("gmStatus", DealedDateUtil.DealedWithUserState(user));
            list.add(map);
        }
        return CommonResult.success(list);
    }


    /**
     * 获取网格员信息，get
     */
    @GetMapping("/getGridManagerData")
    public CommonResult getGridManagerData() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<User> userList = userService.getGridManagerData();
        if (userList.size() == 0) {
            return CommonResult.failed();
        }
        for (User user : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("role", DealedDateUtil.DealedWithRole(user.getRole()));
            map.put("realName", user.getRealName());
            map.put("birthday", user.getBirthday());
            map.put("tel", user.getTel());
            map.put("state", DealedDateUtil.DealedWithUserState(user));
            map.put("age", DealedDateUtil.calculateAge(user.getBirthday()));
            map.put("sex", DealedDateUtil.DealedWithSex(user.getSex()));
            map.put("gmProvince", DealedDateUtil.DealedWithProvince(user.getGridProvince()));
            map.put("gmCity", DealedDateUtil.DealedWithCity(user.getGridCity()));
            map.put("gmDistrict", DealedDateUtil.DealedWithDistrict(user.getGridDistrict()));
            list.add(map);
        }
        return CommonResult.success(list);
    }




    @PostMapping("/updateGridManagerInfo")
    public CommonResult updateGridManagerInfo(@RequestBody Map<String, Object> request){
        User gridManager = new User();
        // 从请求中获取需要更新的信息
        String userId = (String) request.get("userId");
        boolean isNew = (boolean) userId.isEmpty();
        if (isNew) {
            UUID uuid = UUID.randomUUID();
            int randomNumber = Integer.parseInt(uuid.toString().substring(0, 3), 16);
            gridManager.setUserId(String.format("uuid-%03d", randomNumber));
            gridManager.setRole(1);
            gridManager.setPassword((String) request.get("password"));
        } else {
            gridManager = userService.getUserByUserId(userId);
        }
        String passwd = (String) request.get("password");
          if(passwd != null && !passwd.isEmpty()){
                gridManager.setPassword(passwd);
            }
        gridManager.setUsername((String) request.get("username"));
        gridManager.setRealName((String) request.get("realName"));
        gridManager.setTel((String) request.get("tel"));
        gridManager.setSex((Integer) request.get("sex"));
        String dateStr = (String) request.get("birthday");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gridManager.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(date));
        gridManager.setState((Integer) request.get("state"));
        List<Integer> gridArea = (List<Integer>) request.get("gridArea");
        gridManager.setGridProvinceId(gridArea.get(0));
        gridManager.setGridCityId(gridArea.get(1));
        gridManager.setGridDistrictId(gridArea.get(2));
        String password = (String) request.get("password");

        if (!password.isEmpty()){
            gridManager.setPassword(password);
        }

        if (isNew) {
            if (userService.insertAdmin(gridManager) != 1) {
                CommonResult.failed("添加网格员失败");
            }
        } else {
            if (userService.updateUser(gridManager) == null) {
                CommonResult.failed("更新网格员信息失败");
            }
        }
        return CommonResult.success(null, "更新网格员信息成功");
    }

    /**
     * 处理回传数据，、city、district、estimatedGrade
     */

    @GetMapping("/adminSearch")
    public CommonResult adminSearch(@RequestBody Map<String, Object> request){
        return CommonResult.success(adminMap(userService.adminSearch(searchUserByRequest(request))));
    }

    @GetMapping("/gridManagerSearch")
    public CommonResult gridManagerSearch(@RequestBody Map<String, Object> request){
        return CommonResult.success(griManagerMap(userService.gridManagerSearch(searchUserByRequest(request))));
    }
    @GetMapping("/supervisorSearch")
    public CommonResult supervisorSearch(@RequestBody Map<String, Object> request){
        return CommonResult.success(supervisorMap(userService.supervisorSearch(searchUserByRequest(request))));
    }

    private User searchUserByRequest(Map<String, Object> request) {
        User user = new User();
        user.setUserId((String) request.get("userId"));
        user.setUsername((String) request.get("username"));
        user.setRealName((String) request.get("realName"));
        user.setState((Integer) request.get("state"));
        user.setRole((Integer) request.get("role"));
        user.setSex((Integer) request.get("sex"));
        user.setGridProvinceId((Integer) request.get("gridProvinceId"));
        user.setGridCityId((Integer) request.get("gridCityId"));
        user.setGridDistrictId((Integer) request.get("gridDistrictId"));
        return user;
    }


    public List<Map<String, Object>> adminMap(List<User> userList){
        List<Map<String, Object>> list = new ArrayList<>();
        for (User admin : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", admin.getUserId());
            map.put("username", admin.getUsername());
            map.put("tel", admin.getTel());
            map.put("realName", admin.getRealName());
            map.put("role", DealedDateUtil.DealedWithRole(admin.getRole()));
            map.put("state", DealedDateUtil.DealedWithUserState(admin));
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> griManagerMap(List<User> userList){
        List<Map<String, Object>> list = new ArrayList<>();
        for (User user : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("role", DealedDateUtil.DealedWithRole(user.getRole()));
            map.put("realName", user.getRealName());
            map.put("birthday", user.getBirthday());
            map.put("tel", user.getTel());
            map.put("state", DealedDateUtil.DealedWithUserState(user));
            map.put("age", DealedDateUtil.calculateAge(user.getBirthday()));
            map.put("sex", DealedDateUtil.DealedWithSex(user.getSex()));
            map.put("gmProvince", DealedDateUtil.DealedWithProvince(user.getGridProvince()));
            map.put("gmCity", DealedDateUtil.DealedWithCity(user.getGridCity()));
            map.put("gmDistrict", DealedDateUtil.DealedWithDistrict(user.getGridDistrict()));
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> supervisorMap(List<User> userList){
        List<Map<String, Object>> list = new ArrayList<>();
        for (User user : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("role", DealedDateUtil.DealedWithRole(user.getRole()));
            map.put("realName", user.getRealName());
            map.put("sex", DealedDateUtil.DealedWithSex(user.getSex()));
            map.put("birthday", user.getBirthday());
            map.put("age", DealedDateUtil.calculateAge(user.getBirthday()));
            map.put("tel", user.getTel());
            list.add(map);
        }
        return list;
    }

    /**
     * 得到所有监督员数据
     * getSupervisorData
     * @return CommonResult
     */
    @GetMapping("/getSupervisorData")
    public CommonResult getSupervisorData() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<User> userList = userService.getSupervisorData();
        if (userList.size() == 0) {
            return CommonResult.failed("没有监督员数据");
        }
     list = supervisorMap(userList);
        return CommonResult.success(list);
    }

    /**
     * 更新监督员数据
     * @param request
     * @return CommonResult
     */
    @PostMapping("/updateSupervisorInfo")
    public CommonResult updateSupervisorInfo(@RequestBody Map<String, Object> request) throws ParseException {
        User supervisor = new User();
        // 从请求中获取需要更新的信息
        String userId = (String) request.get("userId");
        boolean isNew = (boolean) userId.isEmpty();
        if (isNew) {
            UUID uuid = UUID.randomUUID();
            int randomNumber = Integer.parseInt(uuid.toString().substring(0, 3), 16);
            supervisor.setUserId(String.format("uuid-%03d", randomNumber));
        } else {
            supervisor = userService.getUserByUserId(userId);
        }
        supervisor.setRole(2);
        supervisor.setPassword((String) request.get("password"));
        supervisor.setUsername((String) request.get("username"));
        supervisor.setRealName((String) request.get("realName"));
        supervisor.setTel((String) request.get("tel"));
        supervisor.setSex((Integer) request.get("sex"));
        supervisor.setState((Integer) request.get("state"));
        supervisor.setRemarks((String) request.get("avatarImg"));
        if(request.get("birthday")!=null){
            Date date = null;
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.get("birthday"));
            supervisor.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
        if (isNew) {
            if (userService.insertAdmin(supervisor) != 1) {
                return CommonResult.failed("添加监督员失败");
            }
            else
                return CommonResult.success(null, "添加监督员成功");
        } else {
            if (userService.updateUser(supervisor) == null) {
                return CommonResult.failed("更新监督员信息失败");
            }
            return CommonResult.success(null, "success");
        }
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePassword")
    public CommonResult changePassword(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            return CommonResult.failed("用户不存在");
        }
        if (!user.getPassword().equals(oldPassword)) {
            return CommonResult.failed("原密码错误");
        }
        user.setPassword(newPassword);
        if (userService.updateUser(user) == null) {
            return CommonResult.failed("修改密码失败");
        }
        return CommonResult.success(userService.updateUser(user), "修改密码成功");
    }

    /**
     * 修改手机号
     */
    @PostMapping("/changePhone")
    public CommonResult changePhone(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String newPhone = request.get("newPhone");
        User user = userService.getUserByUserId(userId);
        String verifyCode = request.get("verifyCode").toString();//获取手机验证码
        //首先比对验证码是否失效
        String redisauthcode= (String)redisService.get(tokenId+newPhone); //传入tonkenId返回redis中的value
        if(StringUtils.isEmpty(redisauthcode)){
            //如果未取到则过期验证码已失效"
            return CommonResult.failed("验证码过期");
        }else  if(!"".equals(redisauthcode)&&!verifyCode.equals(redisauthcode)){
            //验证码错误
            return CommonResult.failed("验证码错误");
        }else{
            //用户注册成功
            redisService.remove(tokenId+newPhone);//验证成功后立即删除验证码
            user.setTel(newPhone);
            if (userService.updateUser(user) == null) {
                return CommonResult.failed("修改手机号失败");
            }
            return CommonResult.success(userService.updateUser(user), "修改手机号成功");
        }
    }


}