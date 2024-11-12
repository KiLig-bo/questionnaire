package org.mgcx.nepm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mgcx.nepm.entity.User;
import org.mgcx.nepm.mapper.UserMapper;
import org.mgcx.nepm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByCodeByPass(User user) {
        // 实现获取管理员信息的逻辑
        return userMapper.getUserByCodeByPass(user);
    }

    @Override
    public List<User> listGridMemberByProvinceId(User gridMember) {
        // 实现根据省份ID列出网格成员的逻辑
        return userMapper.listGridMemberByProvinceId(gridMember);
    }


    @Override
    public User getUserById(User user) {
        // 实现根据ID获取监管者信息的逻辑
        return userMapper.getUserById(user);
    }


    @Override
    public int deleteUser(User user) {
        // 实现保存监管者信息的逻辑
        return userMapper.deleteUser(user);
    }
    @Override
    public User updateUser(User user) {
        // 调用mapper接口的updateUser方法执行更新操作
        int result = userMapper.updateUser(user);

        // 检查更新操作是否成功（即至少影响了1行）
        if (result > 0) {
            // 如果更新成功，获取更新后的User对象
            User updatedUser = userMapper.getUserById(user);
            return updatedUser;
        } else {
            // 如果更新没有影响任何行，可以抛出异常或返回null
            // 这里我们选择返回null
            return null;
        }
    }

    @Override
    public int updateUserReturnInt(User user){
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }
    @Override
    public List<User> getGridManagerByDistrictId(int districtId){
        return userMapper.getGridManagerByDistrictId(districtId);
    }
    @Override
    public List<User> getAdminData(){
        return userMapper.getAdminData();
    }

    @Override
    public List<User> getGridManagerData(){
        return userMapper.getGridManagerData();
    }



    @Override
    public int insertAdmin(User admin){

        return userMapper.insertAdmin(admin);
    }

    @Override
    public List<User> supervisorSearch(User user) {
        user.setRole(2);
        return userMapper.searchUser(user);
    }

    @Override
    public List<User> gridManagerSearch(User user) {
        user.setRole(1);
        return userMapper.searchUser(user);

    }

    @Override
    public List<User> adminSearch(User user) {
        user.setRole(0);
        return userMapper.searchUser(user);
    }

    @Override
    public User getUserByTel(String tel) {
        return userMapper.getUserByTel(tel);
    }

    @Override
    public List<User> getSupervisorData(){
        return userMapper.getSupervisorData();
    }
}
