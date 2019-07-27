package cn.zhx2019.young.portal.service;

import cn.zhx2019.young.portal.api.SchoolService;
import cn.zhx2019.young.portal.dao.SchoolMapper;
import cn.zhx2019.young.portal.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 学校service
 * @author young
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;


    /**
     * 根据学校id查询学校
     * @param sid
     * @return
     */
    @Override
    public School getSchoolById(Long sid) {
        return schoolMapper.selectByPrimaryKey(sid);
    }


}
