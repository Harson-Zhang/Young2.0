package cn.zhx2019.young.portal.dao;

import cn.zhx2019.young.api.course.vo.Course;
import cn.zhx2019.young.portal.pojo.CourseExample;
import cn.zhx2019.young.api.common.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  young
 */
public interface CourseMapper {
    long countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Long cid);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Long cid);

    List<Course> selectAllCourseBySchoolName(@Param("schName") String schName);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    /**
     * 条件查询
     * @param keywords
     * @return
     */
    public List<Course> findCourseByLike(String keywords);

    public List<Course> findCourseBySid(Page page);
    /**
     * 通过课程编号获取课程
     * @param cid
     * @return Course
     */
    Course selectByPrimaryKey(long cid);

    /**
     * 选课人数加一
     * @param cid
     * @return
     */
    int addSelectNum(@Param("cid") int cid);

    /**
     * 选课人数减一
     * @param cid
     * @return
     */
    int subSelectNum(@Param("cid") int cid);

    /**
     * 根据cid选course
     * @param cid
     * @return
     */
    Course getCourseByCid(@Param("cid") int cid);

    /**
     * 获取推荐课程
     * @return
     */
    List<Course> getRecommendCourse();

    List<Course> findAllHotCourse();


    Course selectCourseName(Course course);

}