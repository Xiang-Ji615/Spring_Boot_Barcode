package main.java.spring.boot.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.IOUtil;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "Rest")
public class RestfulController {

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
}
