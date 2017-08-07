package main.java.spring.boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;


@Component
@Configurable(preConstruction=true, dependencyCheck=true)
public class JasperUtil implements ICommonUtil{

	@Autowired
	private CommonUtil commonUtil;

	public void printName() {
		System.out.println(commonUtil.getUtilName());
	}
	
	public JasperUtil() {
		System.out.println("Jasper util inited!!!");
	}
}
