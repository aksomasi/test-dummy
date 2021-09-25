package com.example.demo.modal;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Devices {

	@CsvBindByName(column = "Device ID")
	@CsvBindByPosition(position = 0)
	private String id;
	@CsvBindByName(column = "IP Address")
	@CsvBindByPosition(position = 1)
	private String ipAddress;
	@CsvBindByName(column = "Device Type")
	@CsvBindByPosition(position = 2)
	private String deviceType;
	@CsvBindByName(column = "Host Name")
	@CsvBindByPosition(position = 3)
	private String hostName;
	@CsvBindByName(column = "appsLink")
	@CsvBindByPosition(position = 4)
	private String appsLink;
	@CsvBindByName(column = "App ID")
	@CsvBindByPosition(position = 5)
	private String appId;
	@CsvBindByName(column = "App Name")
	@CsvBindByPosition(position = 6)
	private String appName;
	@CsvBindByName(column = "OwnerID")
	@CsvBindByPosition(position = 7)
	private String ownerId;
	@CsvBindByName(column = "Department")
	@CsvBindByPosition(position = 8)
	private String department;
	@CsvBindByName(column = "First Name")
	@CsvBindByPosition(position = 9)
	private String firstName;
	@CsvBindByName(column = "Last Name")
	@CsvBindByPosition(position = 10)
	private String lastName;
	@CsvBindByName(column = "Email")
	@CsvBindByPosition(position = 11)
	private String email;
	@CsvBindByName(column = "Mobile")
	@CsvBindByPosition(position = 12)
	private String mobile;
	@CsvBindByName(column = "Job Description")
	@CsvBindByPosition(position = 13)
	private String jobDescription;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAppsLink() {
		return appsLink;
	}

	public void setAppsLink(String appsLink) {
		this.appsLink = appsLink;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
