package cn.zhx2019.young.portal.pojo;

import java.io.Serializable;

/**
 * @author  young
 */
public class Admin implements Serializable {

    private Long aid;

    private String aname;

    private String password;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
