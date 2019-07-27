package cn.zhx2019.young.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 课程详情页的用户评价专区
 * @author young
 */
public class UserRemark {
    /**
     *用户名
     */
    private String uname;
    /**
     *用户头像
     */
    private String uimage;
    /**
     *用户评分
     */
    private Integer mark;
    /**
     *用户评价
     */
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date content_date;

    public UserRemark() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getContent_date() {
        return content_date;
    }

    public void setContent_date(Date content_date) {
        this.content_date = content_date;
    }
}
