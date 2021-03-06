package cn.zhx2019.young.portal.controller;

import cn.zhx2019.young.portal.api.CourseSelectedService;
import cn.zhx2019.young.portal.pojo.Course;
import cn.zhx2019.young.portal.pojo.CourseSelected;
import cn.zhx2019.young.portal.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Map.Entry;

/**
 * 个人推荐Controller
 * @author young
 */
@Controller
public class RecommendController {

    @Autowired
    private CourseSelectedService courseSelected;

    /**
     *用户id
     */
    private Long uid;

    /**
     * 个人推荐
     * @param request
     * @return
     */
    @RequestMapping("/recommend")
    public ModelAndView recommend(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        this.uid=user.getUid();
        CourseSelected[] CS=courseSelected.getInfo();

        Map<Integer, Map<String, Integer>> userPerfMap = new HashMap<Integer, Map<String, Integer>>();
        Map<Integer, Map<String, Integer>> currentUser = new HashMap<Integer, Map<String, Integer>>();

        Map<String, Integer> pref1 = new HashMap<String, Integer>();
        Map<String,Integer>[] persons = new Map[CS.length];
        persons[0] = new HashMap();
        persons[0].put(CS[0].getCourseCode(),CS[0].getMark());
        userPerfMap.put(CS[0].getUid().intValue(),persons[0]);
        int count=1;
        for(int i=1;i<CS.length;i++){
            persons[i] = new HashMap();
            for(int j=count;j<CS.length;j++){
                if(CS[j].getMark()==null){
                    continue;
                }
                persons[i].put(CS[j].getCourseCode(),CS[j].getMark());
                if(!((CS[j].getUid()).equals(CS[j-1].getUid()))){
                    Map<String, Integer> a =userPerfMap.put(CS[j].getUid().intValue(),persons[i]);
                    count=j;
                }
            }
        }
        Map<Integer, Double> simUserSimMap = new HashMap<Integer, Double>();

        for(Entry<Integer, Map<String,Integer>> userperEn:userPerfMap.entrySet()){
            Integer userid=userperEn.getKey();
            if(!uid.equals(userid)){
                double sim = getUserSimilar(userperEn.getValue(), userperEn.getValue());
                simUserSimMap.put(userid, sim);
            }
        }
        Map<Integer, Map<String, Integer>> simUserObjMap = new HashMap<Integer, Map<String, Integer>>();

        for(Entry<Integer,Map<String,Integer>> p:userPerfMap.entrySet()){
            simUserObjMap.put(p.getKey(), p.getValue());
        }
        ModelAndView mv=new ModelAndView();
        Course c=new Course();
        Course d=new Course();
        Course e=new Course();
        Course f=new Course();
        c.setCourseCode(getRecommend(simUserObjMap, simUserSimMap)[0]);
        d.setCourseCode(getRecommend(simUserObjMap, simUserSimMap)[1]);
        e.setCourseCode(getRecommend(simUserObjMap, simUserSimMap)[2]);
        f.setCourseCode(getRecommend(simUserObjMap, simUserSimMap)[3]);

        try{
        Course course1=courseSelected.selectCourseName(c);
        Course course2=courseSelected.selectCourseName(d);
        Course course3=courseSelected.selectCourseName(e);
        Course course4=courseSelected.selectCourseName(f);
        mv.addObject("personnal",course1);
        mv.addObject("personna2",course2);
        mv.addObject("personna3",course3);
        mv.addObject("personna4",course4);
        }catch (Exception error){

        }
        mv.setViewName("recommend");
        return mv;
    }

    /**
     * 相似行为的用户
     * @param pm1
     * @param pm2
     * @return
     */
    public double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        // 数量n
        int n = 0;
        // Σxy=x1*y1+x2*y2+....xn*yn
        int sxy = 0;
        // Σx=x1+x2+....xn
        int sx = 0;
        // Σy=y1+y2+...yn
        int sy = 0;
        // Σx2=(x1)2+(x2)2+....(xn)2
        int sx2 = 0;
        // Σy2=(y1)2+(y2)2+....(yn)2
        int sy2 = 0;
        for (Entry<String, Integer> pme : pm1.entrySet()) {
            String key = pme.getKey();
            Integer x = pme.getValue();
            Integer y = pm2.get(key);
            if (x != null && y != null) {
                n++;
                sxy += x * y;
                sx += x;
                sy += y;
                sx2 += Math.pow(x, 2);
                sy2 += Math.pow(y, 2);
            }
        }
        double sd = sxy - sx * sy / n;
        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
        return Math.abs(sm == 0 ? 1 : sd / sm);
    }

    /**
     * 获取推荐结果
     * @param simUserObjMap
     * @param simUserSimMap
     * @return
     */
    public String[] getRecommend(Map<Integer, Map<String, Integer>> simUserObjMap,
                                 Map<Integer, Double> simUserSimMap) {
        Map<String, Double> objScoreMap = new HashMap<String, Double>();
        for (Entry<Integer, Map<String, Integer>> simUserEn : simUserObjMap.entrySet()) {
            int user = simUserEn.getKey();
            double sim = simUserSimMap.get(user);
            for (Entry<String, Integer> simObjEn : simUserEn.getValue().entrySet()) {
                //加权（相似度*评分）
                double objScore = sim * simObjEn.getValue();
                String objName = simObjEn.getKey();
                if (objScoreMap.get(objName) == null) {
                    objScoreMap.put(objName, objScore);
                } else {
                    double totalScore = objScoreMap.get(objName);
                    //将所有用户的加权评分作为最后的推荐结果数据
                    objScoreMap.put(objName, totalScore + objScore);
                }
            }
        }
        List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(objScoreMap.entrySet());
        Collections.sort(enList, new Comparator<Entry<String, Double>>() {
            //排序
            @Override
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
                Double a = o1.getValue() - o2.getValue();
                if (a == 0) {
                    return 0;
                } else if (a > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        String[]result=new String[4];
        result[0]=enList.get(enList.size() - 1).getKey();
        result[1]=enList.get(enList.size() - 2).getKey();
        result[2]=enList.get(enList.size() - 3).getKey();
        result[3]=enList.get(enList.size() - 4).getKey();
        return result;
    }

}
