package cn.zhx2019.young.portal.dao;

import cn.zhx2019.young.portal.pojo.Admin;

/**
 * @author  young
 */
public interface AdminMapper {
    Admin adminLogin(String aname, String password);
}
