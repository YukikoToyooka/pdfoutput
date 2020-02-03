

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


            //テーブルの作成
            //引数は列数を入れる
            PdfPTable table = new PdfPTable(2);

            //テーブルの幅を指定
            table.setTotalWidth(400);

            //それぞれの列の幅を設定する
            int width[] = {200, 200};
            table.setWidths(width);

            //罫線の設定
            table.getDefaultCell().setBorder(Rectangle.BOX);

            //表のフォントの設定
            Font tableFont = new Font(baseFont, 12);

            //セルの宣言
            PdfPCell cell;

            //追加するセルの作成（1行目)
            cell = new PdfPCell(new Phrase("発電源", tableFont));
            table.addCell(cell);    //セルをテーブルに追加

            //追加するセルの作成(2行目)
            cell = new PdfPCell(new Phrase("CREV_ID", tableFont));
            table.addCell(cell);

            //設定したテーブルをドキュメントに追加
            table.writeSelectedRows(0, -1, 100, 700, writer.getDirectContent());

        document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
