package com.hk.board.imps;

import java.util.List;

import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CriteriaDto;

public interface IBoardDao {
	
	public List<BoardDto> getAllList();
	
	public boolean insertBoard(BoardDto dto);
	
	public BoardDto getBoard(int b_seq);
	
	public boolean updateBoard(BoardDto dto);
	
	public boolean b_readCount(int b_seq);
	
	public boolean delBoard(int b_seq);
	
	// [ 페이징 처리를 위한 메서드 ]
    public List<BoardDto> listCriteria(String page) throws Exception;
//	public List<BoardDto> listCriteria(CriteriaDto cri) throws Exception;
 
    // 전체 게시글 수 구하기
    public Integer TotalCount() throws Exception;
 



}
