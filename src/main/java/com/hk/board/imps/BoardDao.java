package com.hk.board.imps;

import java.util.List;




import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CommentDto;

@Repository
public class BoardDao implements IBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "com.hk.board.";
	private String namespace1 = "com.hk.board.comment.";
//-------------------------게시판---------------------------
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
//--------------------페이징---------------------------------
	@Override
	public List<BoardDto> listCriteria(String page) throws Exception {
	return sqlSession.selectList(namespace+"listCriteria", page);
	}
	@Override
	public List<BoardDto> listCriteria1(String page) throws Exception {
		return sqlSession.selectList(namespace+"listCriteria1", page);
	}


//-------------게시글 전체 수 구하기------------------------------
	@Override
	public Integer TotalCount() throws Exception {
		return sqlSession.selectOne(namespace+"getTotalCount");
	}
//-----------------------댓글--------------------------
	@Override
	public List<CommentDto> commentList(int b_seq) {
		return sqlSession.selectList(namespace1+"commentList",b_seq);
	}
	@Override
	public boolean insertComment(CommentDto comment) {
		
		int count=0;
		count = sqlSession.insert(namespace1+"insertComment", comment);
		return count>0?true:false;
	}
	@Override
	public boolean replyBoardUpdate(CommentDto comment) {
	
		int count=0;
		count = sqlSession.update(namespace1+"updateComment",comment);
		return count>0?true:false;
		
	}
	@Override
	public boolean delComment(int r_seq) {

		int count=0;
		count = sqlSession.delete(namespace1+"delComment",r_seq);
		return count>0?true:false;
	}

	@Override
	public CommentDto readComment(int r_seq) {
		return sqlSession.selectOne(namespace1+"readComment",r_seq);
	}

	
	
	
	
	

}
