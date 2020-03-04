package test.QR;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

public class QrCode {

    public static void outputQrCode () {

        try {

            //表示先URL
            String url = "https://www.google.com/";

            //生成するバーコード種類
            BarcodeFormat format = BarcodeFormat.QR_CODE;
            int width = 160;
            int height = 160;

            //バーコード補正レベル(最小7%)
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            //QRコードの出力
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(url, format, width, height, hints);

            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(image, "png", new File("qrcode.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
