package cn.zhx2019.young.portal.controller;

import cn.zhx2019.young.portal.api.CourseService;
import cn.zhx2019.young.portal.pojo.Course;
import cn.zhx2019.young.portal.pojo.PagingData;
import cn.zhx2019.young.portal.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程Controller
 * @author  Young
 */
@Controller
public class CourseController {
    @Autowired
    private CourseService service;

    /**
     * 课程查询
     * @param request
     * @param keywords
     * @return
     */
    @RequestMapping("/findCourseByLike")
    public String findHouseByLike(HttpServletRequest request, String keywords) {
        Boolean flag = null;
        if (!StringUtils.isEmpty(keywords)){
            flag=true;
        }
        request.getSession().setAttribute("key", keywords);
        List<Course> findCourse = service.findCourseByLike(keywords);
        request.getSession().removeAttribute("HotCourse");
        request.getSession().removeAttribute("ha");
        request.getSession().setAttribute("Course", findCourse);
        request.getSession().setAttribute("flag", flag);
        return "indexTo";
    }


    /**
     * 课程分页
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("/myCourseInfo")
    @ResponseBody
    public PagingData<Course> getMyCourseByUid(int page, int limit, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        // 设置uid
        long uidLong = user.getUid();
        return service.getMyCourseByUid((int) uidLong, page, limit);
    }

}
