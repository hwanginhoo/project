package com.hk.board.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CommentDto;
import com.hk.board.imps.IBoardDao;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private IBoardDao boardDao;
//------------------------게시판--------------------------	
	@Override
	public List<BoardDto> getAllList() {
		return boardDao.getAllList();
	}

	@Override
	public boolean insertBoard(BoardDto dto) {
		return boardDao.insertBoard(dto);
	}

	@Override
	public BoardDto getBoard(int b_seq) {
		return boardDao.getBoard(b_seq);
	}

	@Override
	public boolean updateBoard(BoardDto dto) {
		return boardDao.updateBoard(dto);
	}

	@Override
	public boolean b_readCount(int b_seq) {
		return boardDao.b_readCount(b_seq);
	}

	@Override
	public boolean delBoard(int b_seq) {
		return boardDao.delBoard(b_seq);
	}
	
//---------------------------페이징------------------------------------
	@Override
	public List<BoardDto> listCriteria(String page) throws Exception {
		return boardDao.listCriteria(page);
	}
	@Override
	public List<BoardDto> listCriteria1(String page) throws Exception {
		return boardDao.listCriteria1(page);
	}
//------------------게시글 전체 수 구하기---------------------------------

	@Override
	public Integer totalCount() throws Exception {
		return boardDao.TotalCount();
	}
//-----------------------------댓글------------------------------------
	@Override
	public List<CommentDto> commentList(int b_seq) {
		return boardDao.commentList(b_seq);
	}

	@Override
	public boolean insertComment(CommentDto dto) {
		return boardDao.insertComment(dto);
	}

	@Override
	public boolean replyBoardUpdate(CommentDto dto) {
		return boardDao.replyBoardUpdate(dto);
	}

	@Override
	public boolean delComment(int r_seq) {
		return boardDao.delComment(r_seq);
	}

	@Override
	public CommentDto readComment(int r_seq) {
		return boardDao.readComment(r_seq);
	}

	
	

}
