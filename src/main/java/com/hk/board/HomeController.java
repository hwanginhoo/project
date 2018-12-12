package com.hk.board;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.dtos.BoardDto;
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
	public String boardList(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록조회{}.", locale);
		
		//조회수를 증가시킨 글번호를 세션 초기화한다.
		request.getSession().removeAttribute("b_readcount");
		
		List<BoardDto> list = boardService.getAllList();
		model.addAttribute("list", list);
		return "boardlist";
	}
	@RequestMapping(value="/insertform.do" , method= {RequestMethod.GET})
	public String insertform(Locale locale,Model model,String b_seq,BoardDto dto) {
		logger.info("글추가 폼이동{}.", locale);
		return "boardinsert";
	}
	
	@RequestMapping(value="/insertboard.do",method=RequestMethod.POST)
	public String insertBoard(Locale locale,Model model,String b_seq,BoardDto dto ) {
		logger.info("글 추가{}.", locale);
		boolean isS = boardService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "글 추가 실패");
			return "error";
		}
	}
	@RequestMapping(value="/detailboard.do", method=RequestMethod.GET)
	public String detailBoard(HttpServletRequest request, Locale locale, Model model,String b_seq) {
		logger.info("글 상세조회{}.", locale);
		int sseq = Integer.parseInt(b_seq);
		
		//조회수증가에 대한 처리
		String rseq = (String)request.getSession().getAttribute("b_readcount");
		if(rseq==null) {
			boardService.b_readCount(sseq);
		}
		request.getSession().setAttribute("b_readcount", b_seq);
		
		BoardDto dto = boardService.getBoard(sseq);
		model.addAttribute("dto", dto);
		return "boarddetail";
	}
	@RequestMapping(value="/updateform.do" , method=RequestMethod.GET)
	public String updateForm(Locale locale,Model model,String b_seq) {
		logger.info("글수정 폼이동{}.", locale);
		
		int sseq = Integer.parseInt(b_seq);
		BoardDto dto = boardService.getBoard(sseq);
		model.addAttribute("dto", dto);
		return "boardupdate";
	}
	@RequestMapping(value="/boardupdate.do" , method=RequestMethod.POST)
	public String updateBoard(Locale locale,Model model,String b_seq,BoardDto dto) {
		logger.info("글 수정하기{}.", locale);
		boolean isS = boardService.updateBoard(dto);
		if(isS) {
			return "redirect:detailboard.do?b_seq="+dto.getB_seq();
		}else {
			model.addAttribute("msg", "수정실패");
			return "error";
		}
	}
	@RequestMapping(value="/delboard.do" , method=RequestMethod.GET)
	public String delBoard(Locale locale,Model model,String b_seq) {
		logger.info("글삭제{}.", locale);
		int sseq = Integer.parseInt(b_seq);
		boolean isS = boardService.delBoard(sseq);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "글삭제 실패");
			return "error";
		}
		
	}
}