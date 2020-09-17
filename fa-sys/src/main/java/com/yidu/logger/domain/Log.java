package com.yidu.logger.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * 操作日志表
 * POJO_Description: Log
 * @author wh
 * @since 2020-09-02
 */
public class Log implements Serializable{

	private static final long serialVersionUID =  5521089631214651104L;
	/**
	 * 日志ID
	 */
	private String log;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 操作事项
	 */
	private String operation;

	/**
	 * 具体操作事项
	 */
	private String operationDetail;

	/**
	 * 操作时间
	 */
	private Date operationTime;


	public Log() {

	}


	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperationDetail() {
		return operationDetail;
	}

	public void setOperationDetail(String operationDetail) {
		this.operationDetail = operationDetail;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}



	@Override
	public String toString() {
		return " Log{" +
				"log='" + log + '\'' +
				"userId='" + userId + '\'' +
				"userName='" + userName + '\'' +
				"operation='" + operation + '\'' +
				"operationDetail='" + operationDetail + '\'' +
				"operationTime='" + operationTime + '\'' +
				'}';
	}

}
