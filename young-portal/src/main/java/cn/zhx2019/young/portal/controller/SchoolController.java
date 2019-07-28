package cn.zhx2019.young.portal.controller;

import cn.zhx2019.young.api.course.CourseService;
import cn.zhx2019.young.api.portal.SchoolService;
import cn.zhx2019.young.api.course.vo.Course;
import cn.zhx2019.young.api.common.Page;
import cn.zhx2019.young.api.portal.vo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 学校Controller
 * @author young
 */
@Controller
public class SchoolController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    CourseService courseService;

    /**
     * 学校页面
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/schooldetail1")
    public String findCourseBySname(HttpServletRequest request,int page,int limit){

        //设置school编号并选出school数据
        long sid = 1;
        School school = schoolService.getSchoolById(sid);

        //定义page对象，设置limit、page和schname
        Page p = new Page();
        p.setLimit(limit);
        p.setPage((page - 1) * limit);
        p.setSchName(school.getSchName());

        //选出学校名为schname的course数据
        List<Course> list = courseService.findCourseBySid(p);
        //返回school和course数据
        request.setAttribute("schoolname",school);
        request.setAttribute("courses",list);
        request.setAttribute("page",page);
        return "schooldetail1";

    }

    /**
     * 学校页面
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/schooldetail2")
    public String findCourseBySname2(HttpServletRequest request,int page,int limit){

        long sid = 2;
        School school = schoolService.getSchoolById(sid);

        Page p = new Page();
        p.setLimit(limit);
        p.setPage((page - 1) * limit);

        p.setSchName(school.getSchName());
        List<Course> list = courseService.findCourseBySid(p);
        request.setAttribute("schoolname",school);
        request.setAttribute("courses",list);
        request.setAttribute("page",page);
        return "schooldetail2";
    }




}
