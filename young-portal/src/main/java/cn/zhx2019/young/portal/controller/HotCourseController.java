package cn.zhx2019.young.portal.controller;

import cn.zhx2019.young.api.course.CourseService;
import cn.zhx2019.young.api.course.vo.Course;
import cn.zhx2019.young.api.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页Controller
 * @author young
 */
@Controller
public class HotCourseController {
    @Autowired
    private CourseService service;

    /**
     * 前往个人中心
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
     * 首页热门课程与推荐课程
     * @param request
     * @return
     */
    @RequestMapping(value = {"/toIndexPage", "/"})
    public String toIndexPage(HttpServletRequest request) {
        List<Course> findHotCourse = service.findAllHotCourse();
        List<Course> recommendCourse = service.getRecommendCourse();

        request.getSession().setAttribute("HotCourse",findHotCourse );
        request.getSession().setAttribute("RecommendCourse",recommendCourse );

        return "index";
    }



    /**
     * 对应页面的跳转
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String getPage(@PathVariable String page, HttpServletRequest request){
        List<Course> findHotCourse = service.findAllHotCourse();
        List<Course> recommendCourse = service.getRecommendCourse();

        request.getSession().setAttribute("HotCourse",findHotCourse );
        request.getSession().setAttribute("RecommendCourse",recommendCourse );
        return page;
    }


}
