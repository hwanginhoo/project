package com.hk.board.aop;


import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExecute {
	
	public void before(JoinPoint join) {
		Logger log = LoggerFactory.getLogger(join.getTarget()+"");
		log.debug("시작");
		log.info("-------시작--------");
	}
	
	public void afterReturning(JoinPoint join) {
		Logger log = LoggerFactory.getLogger(join.getTarget()+"");
		log.debug("끝");
		log.info("-------끝--------"+join.getArgs());
	}

	public void daoError(JoinPoint join) {
		Logger log = LoggerFactory.getLogger(join.getTarget()+"");
		log.debug("에러"+join.getArgs());
		log.info("---------------"+join.toString());
	}
}
