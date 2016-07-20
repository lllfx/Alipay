package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Coder {

	public static void main(String args[]) {
		System.out.println(createQrcode("ÄãºÃ","ÄãºÃ"));
	}

	@SuppressWarnings("deprecation")
	public static String createQrcode(String _text, String name) {
		String qrcodeFilePath = "";
		try {
			int qrcodeWidth = 170;
			int qrcodeHeight = 170;
			String qrcodeFormat = "png";
			HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(_text, BarcodeFormat.QR_CODE, qrcodeWidth,
					qrcodeHeight, hints);

			BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
			File QrcodeFile = new File("image\\" + name + "." + qrcodeFormat);
			ImageIO.write(image, qrcodeFormat, QrcodeFile);
			MatrixToImageWriter.writeToFile(bitMatrix, qrcodeFormat, QrcodeFile);
			qrcodeFilePath = QrcodeFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qrcodeFilePath;
	}
}
