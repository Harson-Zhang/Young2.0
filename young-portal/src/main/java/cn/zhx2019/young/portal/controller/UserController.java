package cn.zhx2019.young.portal.controller;


import cn.zhx2019.young.portal.api.UserService;
import cn.zhx2019.young.portal.dao.UserMapper;
import cn.zhx2019.young.portal.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * 用户Controller
 * @author young
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param req
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String toUserPage(String username, String password, HttpServletRequest req ) {
        User user = new User();
        user.setUname(username);
        user.setPassword(password);
        User loginUser = userService.login(user);
        if(loginUser!=null) {
            req.getSession().setAttribute("loginUser", loginUser);
            return "OK";
        }
        return "FAIL";
    }

    /**
     * 用户注销
     * @param session
     * @return
     */
    @RequestMapping(value = {"/signout", "/logout"})
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:toIndexPage";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/regist")
    @ResponseBody
    public String regist(User user) {
        String[] split = user.getPassword().split(",");
        user.setPassword(split[0]);
        Random random = new Random();
        int num = random.nextInt(1000);
        user.setUimage("https://images.nowcoder.com/head/"+ num +"m.png");
        int regist;
        try {
            regist = userService.regist(user);
            if(regist>0) {
                return "OK";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL";
    }

    /**
     * 个人中心
     * @param request
     * @return
     */
    @RequestMapping("/toUserSystem")
    public String toUserSystemPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null){
            return "customer";
        }else {
            return "redirect:index";
        }
    }

    /**
     * 修改密码
     * @param id
     * @param newPwd
     * @param oldPwd
     * @return
     */
    @RequestMapping("/updateUserPwd")
    @ResponseBody
    public String updateUserPwd(String id,String newPwd,String oldPwd) {
        User oldUser = new User();
        oldUser.setUid(Long.parseLong(id));
        oldPwd = DigestUtils.md5DigestAsHex(oldPwd.getBytes());
        oldUser.setPassword(oldPwd);
        User checkUser = userService.checkOldPwd(oldUser);
        if(checkUser!=null) {
            User newUser = new User();
            newUser.setUid(Long.parseLong(id));
            newPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
            newUser.setPassword(newPwd);
            int n = userService.updateUserPwd(newUser);
            if(n>0) {
                return "OK";
            }
        }
        return "FAIL";
    }

    /**
     * 查看我的选课
     * @param request
     * @return
     */
    @RequestMapping("/myorder")
    public String myCourseInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null){
            return "myorder";
        }else {
            return "redirect:index";
        }

    }

    /**
     * 个人信息认证
     * @param request
     * @return
     */
    @RequestMapping("/addInfo")
    public String addHouse(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        User userInfo = userService.getUserById(user.getUid());
        request.setAttribute("userInfo", userInfo);
        return "addInfo";
    }

    /**
     * 修改信息
     * @param id
     * @param school
     * @param stu_number
     * @param stu_name
     * @param telephone
     * @param email
     * @param sex
     * @return
     */
    @RequestMapping("/addHouseRecord")
    @ResponseBody
    public String validationInfo(String id,String school,int stu_number,String stu_name,String telephone,String email,int sex) {
        User oldUser = new User();
        oldUser.setUid(Long.parseLong(id));
        User newUser = new User();
        newUser.setUid(Long.parseLong(id));
        newUser.setSchool(school);
        newUser.setStuName(stu_name);
        newUser.setStuNumber((long)stu_number);
        newUser.setTelephone(telephone);
        newUser.setEmail(email);
        newUser.setSex(sex);
        int n = userService.validationInfo(newUser);
        if(n>0) {
            return "OK";
        }
        //}
        return "FAIL";
    }

}
