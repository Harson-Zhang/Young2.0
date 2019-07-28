package cn.zhx2019.young.portal.dao;

import cn.zhx2019.young.api.portal.vo.School;
import cn.zhx2019.young.portal.pojo.SchoolExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  young
 */
public interface SchoolMapper {
    long countByExample(SchoolExample example);

    int deleteByExample(SchoolExample example);

    int deleteByPrimaryKey(Long sid);

    int insert(School record);

    int insertSelective(School record);

    List<School> selectByExampleWithBLOBs(SchoolExample example);

    List<School> selectByExample(SchoolExample example);

    School selectByPrimaryKey(Long sid);

    int updateByExampleSelective(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByExampleWithBLOBs(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByExample(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKeyWithBLOBs(School record);

    int updateByPrimaryKey(School record);
}