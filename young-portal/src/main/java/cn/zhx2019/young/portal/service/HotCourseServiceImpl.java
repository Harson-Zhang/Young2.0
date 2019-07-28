package cn.zhx2019.young.portal.service;


import cn.zhx2019.young.api.portal.HotCourseService;
import cn.zhx2019.young.portal.dao.CourseMapper;
import cn.zhx2019.young.portal.dao.HotCourseMapper;
import cn.zhx2019.young.api.course.vo.Course;
import cn.zhx2019.young.api.portal.vo.HotCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 热门课程service
 * @author young
 */
@Service
public class HotCourseServiceImpl implements HotCourseService {
	@Autowired
	private HotCourseMapper dao;
	@Autowired
	private CourseMapper courseMapper;

	/**
	 * 获取所有的热门课程
	 * @return
	 */
	@Override
	public List<HotCourse> findAllHotCourse() {
		return dao.findAllHotCourse();
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

