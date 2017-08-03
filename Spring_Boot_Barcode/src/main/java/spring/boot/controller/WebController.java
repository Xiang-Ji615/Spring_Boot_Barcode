package main.java.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="Web")
public class WebController {

	@RequestMapping(value="HtmlBarcode", method=RequestMethod.GET)
	public String HtmlBarcode(ModelMap map) {
		return "HtmlBarcode";
	}
}
