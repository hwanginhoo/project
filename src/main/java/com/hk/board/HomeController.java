package com.hk.board;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CommentDto;
import com.hk.board.dtos.CriteriaDto;
import com.hk.board.dtos.PageMakerDto;
import com.hk.board.service.IBoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IBoardService boardService; //객체생성안하고 스프링이 만들어서 넣어줄거임
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/boardlist.do", method= RequestMethod.GET)
	public String boardList(@ModelAttribute("cri") CriteriaDto cri, HttpServletRequest request, Locale locale, Model model,String page,String test) throws Exception {
				
		logger.info("글목록조회{}.", locale);

		//조회수를 증가시킨 글번호를 세션 초기화한다.
		request.getSession().removeAttribute("b_readcount");
		
		 //  커맨드 객체로 Criteria를 매개변수로 넣어줘, 넘어오는 page와 perPageNum정보를 받습니다.		 
        // 해당 cri 를 이용해서 service->dao->mapper.xml 순으로 접근하면서 DB처리를 해 cri 전달된
        // 현재 페이지 정보를 기준으로 BoardVO 객체를 담은 ArrayList가 반환될 것입니다.
		List<BoardDto> dto = boardService.listCriteria(page);

	     		 
        // 이제 view jsp 페이지에서 페이징 처리를 위해 사용할 PageMaker 객체를 생성하고
		PageMakerDto pageMaker = new PageMakerDto();

		pageMaker.setCri(cri);
		Integer totalNum = boardService.totalCount();
		pageMaker.setTotalCount(totalNum);
		
		
        // /views/board/listPage.jsp 에서 페이징 처리를 하기 위해 PageMaker 객체를 저장해 놓아야 할 것이고

        // 당연히 화면에 게시글을 뿌려주기 위해서 꺼내온 dto도 저장을 해 주어야 할 것입니다.(model 객체에)
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", dto);		
		
		String pagelist = request.getParameter("pagelist");
		if(pagelist == "b_readcount") {
			List<BoardDto> list = boardService.getReadcountList();
			model.addAttribute("list", list);
			return "boardlist";
			
		}

		
//			else if(pagelist =="b_regdate"){
//			boardService.getAllList();
//			return "boardlist";
//		}

		return "boardlist";
	}
	@RequestMapping(value="/insertform.do" , method= RequestMethod.GET)
	public String insertform(Locale locale,Model model,String b_seq,BoardDto dto) {
		logger.info("글추가 폼이동{}.", locale);
		return "boardinsert";
	}
	
	@RequestMapping(value="/insertboard.do",method=RequestMethod.POST)
	public String insertBoard(Locale locale,Model model,String b_seq,BoardDto dto ) {
		logger.info("글 추가{}.", locale);
		boolean isS = boardService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do?page=1";
		}else {
			model.addAttribute("msg", "글 추가 실패");
			return "error";
		}
	}
	@RequestMapping(value="/detailboard.do", method=RequestMethod.GET)
	public String detailBoard(CriteriaDto cri, HttpServletRequest request, Locale locale, Model model,String b_seq,String page,String perPageNum) {
		// page정보와 perPageNum을 받을 수 있도록 Criteria 객체를 추가
		
		logger.info("글 상세조회{}.", locale);
		int sseq = Integer.parseInt(b_seq);
		
		//조회수증가에 대한 처리
		String rseq = (String)request.getSession().getAttribute("b_readcount");
		if(rseq==null) {
			boardService.b_readCount(sseq);
		}
		request.getSession().setAttribute("b_readcount", b_seq);
		List<CommentDto> list = boardService.commentList(sseq);
		BoardDto dto = boardService.getBoard(sseq);
		model.addAttribute("cri", cri);
		model.addAttribute("dto", dto);
		model.addAttribute("list", list);
		//cri.setPage(Integer.parseInt(page));
		//cri.setPerPageNum(Integer.parseInt(perPageNum));
		
		 
		
//		model.addAttribute("dto", dto);
		return "boarddetail";
	}
	@RequestMapping(value="/updateform.do" , method=RequestMethod.GET)
	public String updateForm(CriteriaDto cri, Locale locale,Model model,String b_seq) {
		logger.info("글수정 폼이동{}.", locale);		
		
		int sseq = Integer.parseInt(b_seq);
		BoardDto dto = boardService.getBoard(sseq);
		model.addAttribute("cri", cri);
		model.addAttribute("dto", dto);
		//boarddetail폼에서 hidden으로 보낸 page와 perPageNum값은 커맨드객체인 CriteriaDto객체에 담겨 모델에
		//cri라는 이름으로 저장된 상태로 boardupdate.jsp로 이동
		
		return "boardupdate";
	}
	@RequestMapping(value="/boardupdate.do" , method=RequestMethod.POST)
	public String updateBoard(CriteriaDto cri,Locale locale,Model model,String b_seq,BoardDto dto) {
		logger.info("글 수정하기{}.", locale);
		model.addAttribute("cri", cri);
		
		boolean isS = boardService.updateBoard(dto);
		
		if(isS) {			
			return "redirect:detailboard.do?page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum()+"&b_seq="+dto.getB_seq();
			
		}else {
			model.addAttribute("msg", "수정실패");
			return "error";
		}
	}
	@RequestMapping(value="/delboard.do" , method=RequestMethod.GET)
	public String delBoard(CriteriaDto cri, Locale locale,Model model,String b_seq,RedirectAttributes rttr,BoardDto dto,String page,String perPageNum) {
		logger.info("글삭제{}.", locale);
		int sseq = Integer.parseInt(b_seq);
		model.addAttribute("cri", cri);
		boolean isS = boardService.delBoard(sseq);
		if(isS) {
			
			return "redirect:boardlist.do?page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum();
		}else {
			model.addAttribute("msg", "글삭제 실패");
			return "error";
		}
		
	}
	@RequestMapping(value="/insertcomment.do",method=RequestMethod.POST)
	public String insertComment(Locale locale,Model model,CommentDto cdto,CriteriaDto cri,BoardDto dto,String b_seq) {
		logger.info("댓글 추가{}.", locale);
		boolean isS = boardService.insertComment(cdto);
		if(isS) {
			return "redirect:detailboard.do?page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum()+"&b_seq="+dto.getB_seq();
		}else {
			model.addAttribute("msg", "글 추가 실패");
			return "error";
		}
	}
	@RequestMapping(value="/delcomment.do" , method=RequestMethod.GET)
	public String delComment(CriteriaDto cri, Locale locale,Model model,String r_seq,BoardDto dto,String page,String perPageNum,CommentDto cdto) {
		logger.info("댓글삭제{}.", locale);
		int sseq = Integer.parseInt(r_seq);
		
		model.addAttribute("cri", cri);
		model.addAttribute("dto",dto);
		model.addAttribute("cdto", cdto);
		
		boolean isS = boardService.delComment(sseq);
		if(isS) {
			
			return "redirect:detailboard.do?page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum()+"&b_seq="+dto.getB_seq();
		}else {
			model.addAttribute("msg", "댓글삭제 실패");
			return "error";
		}
		
	}
	@RequestMapping(value="/updatecommentform.do",method=RequestMethod.GET)
	public String updateComment(CriteriaDto cri,Locale locale,Model model,String r_seq,String b_seq) {
		logger.info("댓글수정 폼이동{}.", locale);
		int sseq = Integer.parseInt(b_seq);
		int rseq = Integer.parseInt(r_seq);
		
		BoardDto dto = boardService.getBoard(sseq);
		CommentDto cdto = boardService.readComment(rseq);
		model.addAttribute("cri", cri);
		model.addAttribute("dto", dto);
		model.addAttribute("cdto", cdto);
		
		
		return "commentupdate";
	}
	@RequestMapping(value="/updatecomment.do", method=RequestMethod.POST)
	public String updateComment(Locale locale,Model model,CriteriaDto cri,String r_seq,CommentDto cdto,BoardDto dto) {
		logger.info("댓글 수정하기{}.",locale);
		model.addAttribute("cri", cri);
		boolean isS = boardService.replyBoardUpdate(cdto);
		if(isS) {			
			return "redirect:detailboard.do?page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum()+"&b_seq="+dto.getB_seq();
			
		}else {
			model.addAttribute("msg", "수정실패");
			return "error";
		}
		
	}
	
	
//	@RequestMapping(value="/boardupdate.do" , method=RequestMethod.POST)
//	public String updateBoard(CriteriaDto cri,Locale locale,Model model,String b_seq,BoardDto dto) {
//		logger.info("글 수정하기{}.", locale);
//		model.addAttribute("cri", cri);
//		
//		boolean isS = boardService.updateBoard(dto);
//		
//		if(isS) {			
//			return "redirect:detailboard.do?page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum()+"&b_seq="+dto.getB_seq();
//			
//		}else {
//			model.addAttribute("msg", "수정실패");
//			return "error";
//		}
//	}
//	
}