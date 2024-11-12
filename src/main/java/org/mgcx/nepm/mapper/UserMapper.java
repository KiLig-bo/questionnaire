package org.mgcx.nepm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mgcx.nepm.entity.User;

import java.util.List;

/**
* @author 赵福强
* @description 针对表【users(用户信息表)】的数据库操作Mapper
* @createDate 2024-05-21 11:18:40
* @Entity org.mgcx.nepm.entity.Users
*/

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名或者手机号码查询用户信息，需自己判断哪一个参数,同时passwwd也要判断 if role不为空，查询role=role的用户
     * @param user
     * @return
     */
    @Select("select * from user where (username = #{username} or tel = #{tel}) and password = #{password}")
    User getUserByCodeByPass(User user);

    //根据省和市查询可工作状态的所属网格员信息列表
    List<User> listGridMemberByProvinceId(User gridMember);

    //根据主键查询公众监督员，用于注册时手机号码是否存在验证
    User getUserById(User supervisor);
    @Select("select * from user where user_id = #{userId}")
    User getUserByUserId(String userId);

    List<User> getGridManagerByDistrictId(int districtId);

    //保存公众监督员信息，用于注册
    int saveUser(User supervisor);

    int deleteUser(User user);

    int updateUser(User user);

    List<User> findAll();

    List<User> getAdminData();

    int insertAdmin(User admin);

    List<User> getGridManagerData();

    List<User> searchUser(User user);

    User getUserByTel(String tel);
    @Select("select * from user where role = 2")
    List<User> getSupervisorData();
}




