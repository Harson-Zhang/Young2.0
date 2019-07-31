package cn.zhx2019.young.user.service;

import cn.zhx2019.young.api.common.EasyUIDataGrid;
import cn.zhx2019.young.api.common.YoungResult;
import cn.zhx2019.young.api.user.UserService;
import cn.zhx2019.young.api.user.vo.User;
import cn.zhx2019.young.user.dao.UserMapper;
import cn.zhx2019.young.user.pojo.UserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户service
 * @author young
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        return userMapper.login(user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public int regist(User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        return userMapper.regist(user);
    }

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    @Override
    public int updateUserPwd(User user) {
        return userMapper.updateUserPwd(user);
    }

    /**
     * 检查旧密码
     * @param user
     * @return
     */
    @Override
    public User checkOldPwd(User user) {
        return userMapper.checkOldPwd(user);
    }

    /**
     * 根据ID查询用户
     * @param uid
     * @return
     */
    @Override
    public User getUserById(long uid) {

        return userMapper.selectByPrimaryKey(uid);
    }

    /**
     * 获取所有用户
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGrid getUserList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo(userList);
        System.out.println();
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(userList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @Override
    public YoungResult deleteUser(@RequestParam(value="ids") long uid) {
        userMapper.deleteByPrimaryKey(uid);
        return YoungResult.ok();
    }

    /**
     * 通过uid获取用户名和用户头像
     *
     * @param uid
     * @return
     */
    @Override
    public User getUnameAndUimage(int uid) {
        return userMapper.getUnameAndUimage(uid);
    }

    /**
     * 认证个人信息
     * @param user
     * @return
     */
    @Override
    public int validationInfo(User user){
        return userMapper.validationInfo(user);
    }


}
