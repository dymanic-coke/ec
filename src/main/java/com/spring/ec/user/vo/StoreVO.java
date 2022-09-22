package com.spring.ec.user.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("storeVO")
public class StoreVO {

	private int seller_num;
	private String category_code;
	private String seller_id;
	private String seller_pw;
	private String seller_tel;
	private String seller_bizReg;
	private String seller_name;
	private String seller_email;
	private String seller_addr;
	private String bizReg_image_name;
	private String keyword;
	private Date reg_date;
	private Date mod_date;

	public StoreVO() {

	}

	public StoreVO(int seller_num, String category_code, String seller_id, String seller_pw, String seller_tel, String seller_bizReg, String seller_name,String seller_email,String seller_addr,String bizReg_image_name,String keyword, Date reg_date, Date mod_date) {
		this.seller_num = seller_num;
		this.category_code = category_code;
		this.seller_id= seller_id;
		this.seller_pw = seller_pw;
		this.seller_tel = seller_tel;
		this.seller_bizReg = seller_bizReg;
		this.seller_name = seller_name;
		this.seller_email = seller_email;
		this.seller_addr = seller_addr;
		this.bizReg_image_name = bizReg_image_name;
		this.keyword = keyword;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
	}

	public int getSeller_num() {
		return seller_num;
	}

	public void setSeller_num(int seller_num) {
		this.seller_num = seller_num;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_pw() {
		return seller_pw;
	}

	public void setSeller_pw(String seller_pw) {
		this.seller_pw = seller_pw;
	}

	public String getSeller_tel() {
		return seller_tel;
	}

	public void setSeller_tel(String seller_tel) {
		this.seller_tel = seller_tel;
	}

	public String getSeller_bizReg() {
		return seller_bizReg;
	}

	public void setSeller_bizReg(String seller_bizReg) {
		this.seller_bizReg = seller_bizReg;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getSeller_addr() {
		return seller_addr;
	}

	public void setSeller_addr(String seller_addr) {
		this.seller_addr = seller_addr;
	}

	public String getBizReg_image_name() {
		return bizReg_image_name;
	}

	public void setBizReg_image_name(String bizReg_image_name) {
		this.bizReg_image_name = bizReg_image_name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}


}
