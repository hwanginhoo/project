package com.hk.board.imps;

import java.util.List;



import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CommentDto;

public interface IBoardDao {
	
	public List<BoardDto> getAllList(); //전체 게시글 보기
	
	public boolean insertBoard(BoardDto dto); //게시글 추가
	
	public BoardDto getBoard(int b_seq);  //게시글 상세보기
	
	public boolean updateBoard(BoardDto dto); //게시글 업데이트
	
	public boolean b_readCount(int b_seq); //게시글 조회수
	
	public boolean delBoard(int b_seq); //게시글 삭제
	
	
	
	// [ 페이징 처리를 위한 메서드 ]
    public List<BoardDto> listCriteria(String page) throws Exception;//최신순 10개씩
	public List<BoardDto> listCriteria1(String page) throws Exception;//조회순 10개씩
 
    // 전체 게시글 수 구하기
    public Integer TotalCount() throws Exception;
 
  //댓글목록
  	public List<CommentDto> commentList(int b_seq);
  	
  	//댓글작성
  	public boolean insertComment(CommentDto dto);
  	
  	//댓글수정
  	public boolean replyBoardUpdate(CommentDto dto);
  	
  	//댓글삭제
  	public boolean delComment(int r_seq);

  	//댓글보기
  	public CommentDto readComment(int r_seq);

}
