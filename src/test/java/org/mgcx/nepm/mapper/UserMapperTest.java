package org.mgcx.nepm.mapper;

import org.junit.jupiter.api.Test;
import org.mgcx.nepm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userService;


    @Test
    public void testSaveUser() {
        User newUser = new User();
        // 填充newUser的属性，比如设置用户名、密码等
        newUser.setUserId("123456");
        newUser.setPassword("222");
        newUser.setUsername("22222");
        newUser.setRole(2);
        newUser.setGridProvinceId(1);
        int result = userService.saveUser(newUser);
        System.out.println(result);
    }
    @Test
    public void testGetUserByCodeByPass() {
        User admin = new User();
        admin.setUsername("test");
        admin.setPassword("test");
        User foundUser = userService.getUserByCodeByPass(admin);
        System.out.println(foundUser);
    }

    // 测试listGridMemberByProvinceId方法
    @Test
    public void testListGridMemberByProvinceId() {
        User gridMember = new User();
        gridMember.setGridProvinceId(1); // 假设1是某个省份的ID

        List<User> gridMembers = userService.listGridMemberByProvinceId(gridMember);
        System.out.println(gridMembers);
    }

    // 测试getUserById方法
    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUserId("1234"); // 假设expectedUserId是某个有效的用户ID

        User foundUser = userService.getUserById(user);
        System.out.println(foundUser);
    }
    // 测试findAll方法
    @Test
    public void testFindAll() {
        List<User> allUsers = userService.findAll();
        System.out.println(allUsers);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUserId("1234");
        int result = userService.deleteUser(user);
        System.out.println(result);
    }
    @Test
    public void testUpdateUser() {
        User expectedUser = new User();
        expectedUser.setUserId("1234seffs");
        expectedUser.setUsername("xiugaisdvs");
        expectedUser.setPassword("33333333");
        expectedUser.setRole(2);
        userService.saveUser(expectedUser);
        expectedUser.setUsername("333333");
        // 执行测试方法
        int actualUser = userService.updateUser(expectedUser);

    }

    @Test
    public void testGetUserByUserId() {
        User user = userService.getUserByUserId("uuid-002");
        System.out.println(user);
    }
    @Test
    public void testGetGridManagerByDistrictId() {
        System.out.println(userService.getGridManagerByDistrictId(1101010).get(0));
    }

    @Test
    public void testUserSearch() {
        User user = new User();
        user.setRole(2);
        System.out.println(userService.searchUser(user));
    }
}