package com.hk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CriteriaDto;
import com.hk.board.imps.IBoardDao;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private IBoardDao boardDao;
	
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

	@Override
	public List<BoardDto> listCriteria(String page) throws Exception {
	return boardDao.listCriteria(page);
	}
	
//	public List<BoardDto> listCriteria(CriteriaDto cri) throws Exception{
//		return boardDao.listCriteria(cri);
//	}

	@Override
	public Integer totalCount() throws Exception {
		return boardDao.TotalCount();
	}

}
