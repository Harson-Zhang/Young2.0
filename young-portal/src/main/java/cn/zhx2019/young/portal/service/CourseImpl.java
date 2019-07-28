package cn.zhx2019.young.portal.service;

import cn.zhx2019.young.api.common.EasyUIDataGrid;
import cn.zhx2019.young.api.common.Page;
import cn.zhx2019.young.api.common.PagingData;
import cn.zhx2019.young.api.course.vo.Course;
import cn.zhx2019.young.api.course.CourseService;
import cn.zhx2019.young.portal.dao.CourseMapper;
import cn.zhx2019.young.portal.dao.CourseSelectedMapper;
import cn.zhx2019.young.portal.dao.SchoolMapper;
import cn.zhx2019.young.portal.pojo.CourseExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程service
 * @author young
 */
@Service
public class CourseImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private CourseSelectedMapper courseSelectedMapper;

    /**
     *根据id查询课程
     * @param id
     * @return
     */
    @Override
    public Course getCourseinfoById(long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    /**
     *获取所有课程
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGrid getCourseList(int page, int rows) {
        PageHelper.startPage(page,rows);
        //执行查询
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("course_selectnum");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo(courseList);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(courseList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据课程名获取课程
     * @param sname
     * @return
     */
    @Override
    public  List<Course> selectAllCourseBySchoolName(String sname) {
        List<Course> courses = courseMapper.selectAllCourseBySchoolName(sname);
        return courses;
    }

    /**
     * 根据学校获取所有的课程
     * @param sname
     * @return
     */
    @Override
    public EasyUIDataGrid selectAllCourseBySchool(String sname) {
        PageHelper.startPage(30,8);
        //执行查询
        List<Course> courses = courseMapper.selectAllCourseBySchoolName(sname);
        PageInfo<Course> pageInfo = new PageInfo(courses);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(courses);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据课程名关键字搜索课程
     * @param keywords
     * @return
     */
    @Override
    public List<Course> findCourseByLike(String keywords) {
        return courseMapper.findCourseByLike(keywords);
    }

    /**
     * 通过课程id获取课程信息
     * @param cid
     * @return
     */
    @Override
    public Course getCourseByCid(int cid) {

        return courseMapper.selectByPrimaryKey((long) cid);
    }

    /**
     * 通过课程id获取课程信息
     * @param page
     * @return
     */
    @Override
    public List<Course> findCourseBySid(Page page){
        List<Course> courses = courseMapper.findCourseBySid(page);
        return courses;

    }

    /**
     * 根据用户ID查询选课
     * @param uid
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PagingData<Course> getMyCourseByUid(int uid, int page, int limit) {

       //根据uid，查询所选课程的cid
        int index = (page - 1) * limit;
        List<Integer> cidList = courseSelectedMapper.getLimitedCidByUid(uid, index, limit);

        //for循环，根据cid，查询所有课程信息
        List<Course> courseList = new ArrayList<>();
        for (Integer cid : cidList) {
            Course course = courseMapper.getCourseByCid(cid);
            courseList.add(course);
        }

        //各参数封装到pageBean中
        int total = courseSelectedMapper.getSelectedCountByUid(uid);

        PagingData<Course> pagingData = new PagingData<>();
        pagingData.setCode(0);
        pagingData.setMsg("200");
        pagingData.setData(courseList);
        pagingData.setCount(total);
        return pagingData;
    }

    /**
     * 获取所有热门课程
     * @return
     */
    @Override
    public List<Course> findAllHotCourse() {
        return courseMapper.findAllHotCourse();
    }

    /**
     * 首页推荐课程
     *
     * @return
     */
    @Override
    public List<Course> getRecommendCourse() {
        return courseMapper.getRecommendCourse();
    }
}
