package cn.zhx2019.young.api.portal;

import cn.zhx2019.young.api.course.vo.Course;
import cn.zhx2019.young.api.portal.vo.HotCourse;

import java.util.List;

/**
 * @author  young
 */
public interface HotCourseService {

	/**
	 * 查询所有热门课程信息
	 *
	 * @return
	 */
	public List<HotCourse> findAllHotCourse();

	/**
	 * 首页推荐课程
	 * @return
	 */
	List<Course> getRecommendCourse();
}
