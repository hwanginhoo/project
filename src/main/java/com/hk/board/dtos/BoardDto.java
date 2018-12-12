package com.hk.board.dtos;

import java.io.Serializable;
import java.util.Date;

public class BoardDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int b_seq;
	private String m_id;
	private String b_title;
	private String b_content;
	private Date b_regdate;
	private int b_readcount;
	public BoardDto() {
		
	}
	public BoardDto(int b_seq, String m_id, String b_title, String b_content, Date b_regdate, int b_readcount) {
		super();
		this.b_seq = b_seq;
		this.m_id = m_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_regdate = b_regdate;
		this.b_readcount = b_readcount;
	}
	public int getB_seq() {
		return b_seq;
	}
	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public Date getB_regdate() {
		return b_regdate;
	}
	public void setB_regdate(Date b_regdate) {
		this.b_regdate = b_regdate;
	}
	public int getB_readcount() {
		return b_readcount;
	}
	public void setB_readcount(int b_readcount) {
		this.b_readcount = b_readcount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
