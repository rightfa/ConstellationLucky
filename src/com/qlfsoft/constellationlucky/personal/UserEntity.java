package com.qlfsoft.constellationlucky.personal;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author hyn
 *
 */
public class UserEntity {

	private String email;
	private String pwd;
	private String nickName;
	private Date birthday;
	private String constellation;
	private String avatar;
	private String home;
	private String address;
	private String telephone;
	private String education;
	private String sex;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void GetUser(Context ctx)
	{
		SharedPreferences sp = ctx.getSharedPreferences("User",Context.MODE_PRIVATE);
		email = sp.getString("name", "");
		pwd = sp.getString("password", "");
		nickName = sp.getString("nickname", "");
		birthday = new Date();
		birthday.setTime(sp.getLong("birthday", 0));
		constellation = sp.getString("constellation", "");
		avatar = sp.getString("avatar", "");
		home = sp.getString("home", "");
		address = sp.getString("address", "");
		telephone = sp.getString("telephone","");
		education = sp.getString("education", "");
		sex = sp.getString("sex", "ÄÐ");
	}
}
