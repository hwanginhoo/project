package com.hk.board.imps;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CriteriaDto;

@Repository
public class BoardDao implements IBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "com.hk.board.";
	@Override
	public List<BoardDto> getAllList() {
		return sqlSession.selectList(namespace+"getAllContent");
	}

	@Override
	public boolean insertBoard(BoardDto dto) {
		int count = 0;
		count = sqlSession.insert(namespace+"insertBoard",dto);
		return count>0?true:false;
	}

	@Override
	public BoardDto getBoard(int b_seq) {
		return sqlSession.selectOne(namespace+"getBoard",b_seq);
	}

	@Override
	public boolean updateBoard(BoardDto dto) {
		int count=0;
		count = sqlSession.update(namespace+"updateBoard",dto);
		return count>0?true:false;
	}

	@Override
	public boolean b_readCount(int b_seq) {
		int count=0;
		count = sqlSession.update(namespace+"b_readCount",b_seq);
		return count>0?true:false;
	}

	@Override
	public boolean delBoard(int b_seq) {
		int count=0;
		count = sqlSession.delete(namespace+"delBoard", b_seq);
		return count>0?true:false;
	}

	@Override
	public List<BoardDto> listCriteria(String page) throws Exception {
	return sqlSession.selectList(namespace+"listCriteria", page);
	}

//	public List<BoardDto> listCriteria(CriteriaDto cri) throws Exception{
//		return sqlSession.selectList(namespace+"listCriteria", cri);
//	}

	@Override
	public Integer TotalCount() throws Exception {
		return sqlSession.selectOne(namespace+"getTotalCount");
	}


	
	

}
