package com.yidu.user.domain;

import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * POJO_Description: 用户实体类
 * @author wh
 * @since 2020-08-28
 */
public class User implements Serializable{

	private static final long serialVersionUID =  8564240847035410206L;
	/**
	 * 用户Id
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码,不能为空
	 */
	private String password;

	/**
	 * 手机号
	 */
	private String telephone;

	/**
	 * 性别 1 :男|Male, 0 :女|Female
	 */
	private int gender;
    /**
     * 性别 str格式
     */
	private String genderStr;

	/**
	 * 用户图像地址,默认系统logo图
	 */
	private String userPic;

	/**
	 * 是否可用  1：可用  0:不可用
	 */
	private String usable;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
	private Date createTime;
    /**
     * 创建时间str格式
     */
	private String createTimeStr;
	/**
	 * 用户描述
	 */
	private String description;

	public User() {

	}



    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	   	this.genderStr = gender == 1 ? "男" : "女";
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		if(null != this.createTime){
			this.createTimeStr = DateUtils.dataToString(createTime,"yy-MM-dd HH:mm:ss");
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenderStr() {
		return genderStr;
	}


	public String getCreateTimeStr() {
		return createTimeStr;
	}


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gender=" + gender +
                ", genderStr='" + genderStr + '\'' +
                ", userPic='" + userPic + '\'' +
                ", usable=" + usable +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
