

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Document;


public class Main {

    public static void  main (String args[]) {

        try {

        //test.pdfとしてPDF作成
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));

        //タイトルの設定
        document.addTitle("CREV証書");

        //PDF印刷時に「大きいページを縮小」がデフォルトとなっており、縮小されて印刷されてしまうので実サイズで印刷するようにする
        writer.addViewerPreference(PdfName.PRINTSCALING, PdfName.NONE);

        document.open();

        //PDFページの設定
        //A4サイズ
        document.setPageSize(PageSize.A4);
        //余白設定(余白なし)
        document.setMargins(0, 0, 0,0);
        //改ページ
        document.newPage();

        //見出し描画のため、テキストブロック(レイアウト枠)のオブジェクトを取得する
        PdfContentByte title = writer.getDirectContent();

        //フォントの種類と指定する(明朝かゴシックしか指定できない)
        //明朝に設定
        //日本語フォントは組み込みにできないので、第三引数はflaseとなる
        BaseFont baseFont = BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-H", false);

            //タイトルの描画
            {
                title.saveState();

                //線の太さを設定する
                title.setLineWidth(0.2);
                //ストローク？
                title.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
                //フォントとフォントサイズ設定
                title.setFontAndSize(baseFont, 20);

                //タイトル文字描画
                title.beginText();
                //配置場所の設定(中央に設定)
                title.showTextAligned(PdfContentByte.ALIGN_CENTER, "CREV証書",
                        300, (document.getPageSize().getHeight() - 50), 0);

                title.endText();
                title.restoreState();
            }










        //出力するフォント設定
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        //出力する文字
        Chunk chunk = new Chunk("Hello", font);

        //PDFに文字を出力する
        document.add(chunk);
        document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
