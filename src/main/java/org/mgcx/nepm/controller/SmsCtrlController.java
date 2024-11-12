package org.mgcx.nepm.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.mgcx.nepm.entity.User;
import org.mgcx.nepm.service.IUserService;
import org.mgcx.nepm.service.RedisService;
import org.mgcx.nepm.util.CodeUtil;
import org.mgcx.nepm.util.CommonResult;
import org.mgcx.nepm.util.SmsTool;
import org.mgcx.nepm.util.smsCode.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class SmsCtrlController {


    /***
     * 注入redis模版
     */
    @Autowired
    RedisService redisService;

    @Autowired
    IUserService userService;
    private  String tokenId="TOKEN-USER-";


    /**
     * 发送短信
     * @ResponseBody 返回json数据
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     * @throws ClientException 抛出异常
     * 2024/6/11 功能复用，当前端传过来参数为userId和newPhone时，发送验证码,修改手机号
     */
    @ResponseBody
    @RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST, headers = "Accept=application/json")
    public CommonResult smsXxs(@RequestBody Map<String,Object> requestMap, HttpServletRequest request) throws ClientException {
        Map<String,Object> map = new HashMap<>();
        String phone;
        if(requestMap.get("newPhone") != null){
             phone = requestMap.get("newPhone").toString();
        }
        else {
        //此为注册服务
         phone = requestMap.get("tel").toString();
        }
        //验证手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 无效手机号，返回错误信息
            return CommonResult.failed("手机号无效，请重新输入");
        }
        User user = userService.getUserByTel(phone);
        if(user != null){
            return CommonResult.failed("手机号码已经注册");
        }
        // 调用工具栏中生成验证码方法（指定长度的随机数）
        String code = CodeUtil.generateVerifyCode(6);
        //填充验证码
        String TemplateParam = "{\"code\":\""+code+"\"}";
        map.put("verifyCode",code);
        map.put("phone",phone);
        //验证码绑定手机号并存储到redis
        redisService.set(tokenId+phone,code);
        redisService.expire(tokenId+phone,620);//调用reids工具类中存储方法设置超时时间
        return CommonResult.success(map);
//       SendSmsResponse response = SmsTool.sendSms(phone,TemplateParam);//传入手机号码及短信模板中的验证码占位符
//        map.put("verifyCode",code);
//        map.put("phone",phone);
//        request.getSession().setAttribute("CodePhone",map);//将验证码和手机号码存入session
//        if( response.getCode().equals("OK")) {
//            map.put("isOk","OK");
//            //验证码绑定手机号并存储到redis
//            redisService.set(tokenId+phone,code);
//            redisService.expire(tokenId+phone,620);//调用reids工具类中存储方法设置超时时间
//            return CommonResult.success(map);
//        }
//        return CommonResult.failed();

    }


    /**
     * 注册验证
     * @ResponseBody 返回json数据
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     * @throws ClientException 抛出异常
     * @return user的id，
     */
    @ResponseBody
    @RequestMapping(value = "/phoneRegister", method = RequestMethod.POST, headers = "Accept=application/json")
    public CommonResult validateNum(@RequestBody Map<String,Object> requestMap) throws ClientException {

        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("tel").toString();//获取注册手机号码
        String verifyCode = requestMap.get("verifyCode").toString();//获取手机验证码
        //首先比对验证码是否失效
        String redisauthcode= (String)redisService.get(tokenId+phone); //传入tonkenId返回redis中的value
        if(StringUtils.isEmpty(redisauthcode)){
            //如果未取到则过期验证码已失效"
            return CommonResult.failed("验证码过期");
        }else  if(!"".equals(redisauthcode)&&!verifyCode.equals(redisauthcode)){
            //验证码错误
            return CommonResult.failed("验证码错误");
        }else{
            //用户注册成功
            redisService.remove(tokenId+phone);//验证成功后立即删除验证码
            // 生成一个随机的 UUID
            UUID uuid = UUID.randomUUID();
            // 提取 UUID 的前 3 个十六进制数字，并转换为十进制数字
            int randomNumber = Integer.parseInt(uuid.toString().substring(0, 3), 16);
            return CommonResult.success(String.format("uuid-%03d", randomNumber),"register successfully");
        }
    }

    /**
     * 注册补充信息
     * @ResponseBody 接受前台传入的json数据 接受类型为Map
     */
    @RequestMapping("/loginInfoComplement")
    public CommonResult loginInfoComplement(@RequestBody Map<String,Object> requestMap) {
        Map<String, Object> map = new HashMap<>();;
        String userId = requestMap.get("userId").toString();
        String username = requestMap.get("username").toString();
        String password = requestMap.get("passwd").toString();
        System.out.println(userId + username + password);
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        //缓存到redis
        redisService.set(tokenId + userId, user);
        //设置过期时间
        redisService.expire(tokenId + userId, 600);
        return CommonResult.success("success","登录成功");
    }

    /**
     * 登录补充信息
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequsetBody 接受前台传入的json数据 接受类型为Map
     */
    @RequestMapping("/userInfoComplement")
    public CommonResult userInfoComplement(@RequestBody Map<String,Object> requestMap) {
     String userId = requestMap.get("userId").toString();
     //从redis中获取用户信息
        User user =  (User) redisService.get(tokenId + userId);
        if(user == null){
            return CommonResult.failed("404");
        }
        user.setRealName(requestMap.get("realName").toString());
        user.setBirthday(requestMap.get("birthday").toString());
        user.setSex(Integer.parseInt(requestMap.get("sex").toString()));
        //开始写入MySQL
        userService.insertAdmin(user);
        return CommonResult.success("success");
    }
}