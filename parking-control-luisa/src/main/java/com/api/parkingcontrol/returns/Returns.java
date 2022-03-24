package com.api.parkingcontrol.returns;

import java.util.Objects;

public class Returns {

	private String result;
	private String msg;
	private String cat;

	public Returns() {}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	@Override
	public String toString() {
		return "Returns [result=" + result + ", msg=" + msg + ", cat=" + cat + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cat, msg, result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Returns other = (Returns) obj;
		return Objects.equals(cat, other.cat) && Objects.equals(msg, other.msg) && Objects.equals(result, other.result);
	}

}

