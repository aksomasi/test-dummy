package com.example.demo;

import com.opencsv.bean.CsvBindByPosition;

public class InputData {

	@CsvBindByPosition(position = 0)
	private String ipAddress;

	@CsvBindByPosition(position = 1)
	private String hostName;

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
	
	
}
