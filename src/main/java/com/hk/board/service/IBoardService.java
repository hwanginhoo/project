package com.hk.board.service;

import java.util.List;

import com.hk.board.dtos.BoardDto;

public interface IBoardService {
	
	public List<BoardDto> getAllList();
	
	public boolean insertBoard(BoardDto dto);
	
	public BoardDto getBoard(int b_seq);
	
	public boolean updateBoard(BoardDto dto);
	
	public boolean b_readCount(int b_seq);
	
	public boolean delBoard(int b_seq);
	

}
