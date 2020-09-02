package com.yidu.paging;

/**
 * 类的描述: 用户分页+搜索查询，获取参数的模板
 *
 * @author wh
 * @since 2020/8/30 10:59
 */
public class UserPaging {
    private String userId;
    private String userName;
    private String usable;
    private int page;
    private int limit;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
