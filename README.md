对原有的CoursePlatform（独墅湖高教区课程资源共享平台）进行改造，加入Dubbo，Zookeeper作为分布式通信与注册中心，并使用Redis优化首页和评论功能，ElasticSearch作为搜索引擎。
当然考虑到大规模选课的情况，将引入分布式事务。还有SSO单点登录。
感觉要完成这些有点悬，慢慢弄吧~

---

弃坑了o(╥﹏╥)o，前后端没法分离，新开了一个项目MeetingFilms学分布式
