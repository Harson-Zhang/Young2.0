package cn.zhx2019.young.portal.service;

import cn.zhx2019.young.portal.api.AdminService;
import cn.zhx2019.young.portal.dao.AdminMapper;
import cn.zhx2019.young.portal.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 管理员service
 * @author young
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证用户名
     * @param aname
     * @param password
     * @return
     */
    @Override
    public Admin adminLogin(String aname, String password) {
        Admin admin = adminMapper.adminLogin(aname, password);
        return admin;
    }
}
