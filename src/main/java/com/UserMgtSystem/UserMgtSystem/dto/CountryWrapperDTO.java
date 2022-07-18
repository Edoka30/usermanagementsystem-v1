package com.UserMgtSystem.UserMgtSystem.dto;

import java.util.List;

public class CountryWrapperDTO {
	private boolean error;
	private String msg;
	private List<CountryDto> data;

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<CountryDto> getData() {
		return data;
	}

	public void setData(List<CountryDto> data) {
		this.data = data;
	}

}
