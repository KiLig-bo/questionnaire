package org.mgcx.nepm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.mgcx.nepm.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cheng Wang
 * @since 2024-05-07
 */
public interface IUserService extends IService<User> {

    //根据编码和密码进行查询，用于登录
    User getUserByCodeByPass(User admin);

    //根据省和市查询可工作状态的所属网格员信息列表
    List<User> listGridMemberByProvinceId(User gridMember);

    //根据主键查询公众监督员，用于注册时手机号码是否存在验证
    User getUserById(User user);

    //保存公众监督员信息，用于注册

    int deleteUser(User user);
    User updateUser(User user);
    int updateUserReturnInt(User user);
    List<User> findAll();

    User getUserByUserId(String userId);
    List<User> getGridManagerByDistrictId(int districtId);
    List<User> getAdminData();

    int insertAdmin(User admin);

    List<User> getGridManagerData();

    List<User> supervisorSearch(User user);

    List<User> gridManagerSearch(User user);

    List<User> adminSearch(User user);

    /**
     * 根据用户tel查询用户是否存在
     * @param tel
     */
    User getUserByTel(String tel);

    List<User> getSupervisorData();
}
