package cn.zhx2019.young.portal.service;

import cn.zhx2019.young.portal.api.EvaluationService;
import cn.zhx2019.young.portal.dao.EvaluationMapper;
import cn.zhx2019.young.portal.pojo.Course;
import cn.zhx2019.young.portal.pojo.EasyUIDataGrid;
import cn.zhx2019.young.portal.pojo.Evaluation;
import cn.zhx2019.young.portal.pojo.EvaluationExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评价service
 * @author young
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EvaluationMapper evaluationMapper;

    /**
     * 获取所有的评价
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGrid getEvaluation(int page, int rows) {
        PageHelper.startPage(page,rows);
        //执行查询
        EvaluationExample evaluationExample = new EvaluationExample();
        List<Evaluation> evaluationList = evaluationMapper.selectByExample(evaluationExample);
        PageInfo<Course> pageInfo = new PageInfo(evaluationList);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(evaluationList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据课程编码，获取他的全部课程评价信息
     *
     * @param course_code
     * @return com.harson.entities.Evaluation
     */
    @Override
    public Evaluation getEvaluationByCourse_code(String course_code) {
        Evaluation evaluation = evaluationMapper.getEvaluationByCourse_code(course_code);

        if (evaluation == null){
            return new Evaluation(course_code);
        }
        return evaluation;
    }

    /**
     * 插入新的课程评价
     *
     * @param evaluation
     * @return
     */
    @Override
    public int insertEvaluation(Evaluation evaluation) {
        return evaluationMapper.insert(evaluation);
    }

    /**
     * 更新评价
     * @param evaluation
     * @return
     */
    @Override
    public int updateEvaluation(Evaluation evaluation) {
        return evaluationMapper.updateByCourse_code(evaluation);
    }
}
