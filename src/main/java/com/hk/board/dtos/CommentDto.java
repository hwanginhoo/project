package com.hk.board.dtos;

import java.util.Date;

public class CommentDto {
	
	private int r_seq;
	private int b_seq;
	private String m_id;
	private String r_content;
	private Date r_regdate;
	public CommentDto() {
	
	}
	public int getR_seq() {
		return r_seq;
	}
	public void setR_seq(int r_seq) {
		this.r_seq = r_seq;
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
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public Date getR_regdate() {
		return r_regdate;
	}
	public void setR_regdate(Date r_regdate) {
		this.r_regdate = r_regdate;
	}
	@Override
	public String toString() {
		return "CommentDto [r_seq=" + r_seq + ", b_seq=" + b_seq + ", m_id=" + m_id + ", r_content=" + r_content
				+ ", r_regdate=" + r_regdate + "]";
	}
	
	

}
