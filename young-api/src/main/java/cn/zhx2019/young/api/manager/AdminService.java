package cn.zhx2019.young.api.manager;

import cn.zhx2019.young.api.manager.vo.Admin;

/**
 * @author  young
 */
public interface AdminService {
    Admin adminLogin(String aname, String password);
}
