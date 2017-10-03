package com.common.system.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//prototype，多例，每次请求，会产生一个controller
@Scope("prototype")
@Controller
@RequestMapping(value="/page")
public class PageController {
	private Logger log = Logger.getLogger(PageController.class);
	
	/**
	 * 跳转到指定的url页面
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/goto")
	public ModelAndView goPage(String url) {
		ModelAndView view = new ModelAndView(url);
		return view;
	}
}
