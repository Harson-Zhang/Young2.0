package cn.zhx2019.young.portal.api;

import cn.zhx2019.young.portal.pojo.Admin;

/**
 * @author  young
 */
public interface AdminService {
    Admin adminLogin(String aname, String password);
}
