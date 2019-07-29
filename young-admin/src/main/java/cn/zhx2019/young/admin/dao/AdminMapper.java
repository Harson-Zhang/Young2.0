package cn.zhx2019.young.admin.dao;

import cn.zhx2019.young.api.manager.vo.Admin;

/**
 * @author  young
 */
public interface AdminMapper {
    Admin adminLogin(String aname, String password);
}
