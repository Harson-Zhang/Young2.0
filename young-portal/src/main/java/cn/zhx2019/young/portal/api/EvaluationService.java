package cn.zhx2019.young.portal.api;

import cn.zhx2019.young.portal.pojo.EasyUIDataGrid;
import cn.zhx2019.young.portal.pojo.Evaluation;

/**
 * @author  young
 */
public interface EvaluationService {

    EasyUIDataGrid getEvaluation(int page, int rows);

    /**
     * 根据课程编码，获取他的全部课程评价信息
     * @param course_code
     * @return com.harson.entities.Evaluation
     */
    Evaluation getEvaluationByCourse_code(String course_code);

    /**
     * 插入新的课程评价
     * @param evaluation
     * @return
     */
    int insertEvaluation(Evaluation evaluation);

    int updateEvaluation(Evaluation evaluation);
}
