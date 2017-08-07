package main.java.spring.boot.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.spring.boot.util.ICommonUtil;
import main.java.spring.boot.util.JasperUtil;

@RestController
@RequestMapping(value = "Rest")
public class RestfulController implements ApplicationContextAware {
	
//	@Autowired
//	ICommonUtil util;
	
	ApplicationContext context;

	@RequestMapping(value = "HelloWorld", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public Object helloWorld(String name) {
		System.out.println("Hello world!!!  " + name + " , " + new Date());
		return "hello world";
	}

	@RequestMapping(value = "/Barcode", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] testphoto(String barcode) throws IOException {

		String barcodeString = barcode;
		Code128Bean barcode128Bean = new Code128Bean();

		barcode128Bean.setCodeset(Code128Constants.CODESET_B);
		barcode128Bean.setHeight(35);
		final int dpi = 100;

		// Configure the barcode generator
		// adjust barcode width here
//		barcode128Bean.setModuleWidth(UnitConv.in2mm(5.0f / dpi));
		barcode128Bean.setModuleWidth(0.5);
		barcode128Bean.doQuietZone(false);

		// Open output file
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(out, "image/jpeg", dpi,
					BufferedImage.TYPE_BYTE_GRAY, false, 0);

			barcode128Bean.generateBarcode(canvasProvider, barcodeString);

			canvasProvider.finish();
		} finally {
			out.close();
		}
		return out.toByteArray();
	}
	
	@RequestMapping(value="Util", method=RequestMethod.GET, produces=MediaType.ALL_VALUE)
	public Object UtilTest() {
//		JasperUtil util = new JasperUtil();
//		context.getBean(JasperUtil.class).printName();
		JasperUtil util = new JasperUtil();
		util.printName();
		return "Hello world by JJ";
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.context = arg0;
	}
}
