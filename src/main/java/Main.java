

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
            {
                BaseFont baseFontTable = BaseFont.createFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", false);

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
                Font tableFont = new Font(baseFontTable, 12);

                //-----表タイトルの設定 ------
                //追加するセル(列タイトル)の作成（1行目)
                PdfPCell cell1_1 = new PdfPCell(new Paragraph("発電源", tableFont));
                cell1_1.setGrayFill(0.8f);  //セルを灰色に設定
                cell1_1.setFixedHeight(22f);    //セルの高さを設定
                table.addCell(cell1_1);    //セルをテーブルに追加

                //追加するセル(列タイトル)の作成(2行目)
                PdfPCell cell2_1 = new PdfPCell(new Paragraph("CREV_ID", tableFont));
                cell2_1.setGrayFill(0.8f);  //セルを灰色に設定
                cell2_1.setFixedHeight(22f);    //セルの高さを設定
                table.addCell(cell2_1);     //セルをテーブルに追加

                //----表要素の追加----

                int i;

                for (i = 0; i < 10; i++) {
                    PdfPCell powerSupply;
                    PdfPCell crevId;

                    //サンプルとして2行追加しておく
                    powerSupply = new PdfPCell(new Paragraph("-", tableFont));
                    crevId = new PdfPCell(new Paragraph("-", tableFont));

                    table.addCell(powerSupply);
                    table.addCell(crevId);

                }

                //設定したテーブルをドキュメントに追加
                table.writeSelectedRows(0, -1, 100, 700, writer.getDirectContent());
            }

            //現在時刻の取得と表示
            {
                //カレンダクラスのオブジェクトを作成する
               Calendar cl = Calendar.getInstance();
               //日付表示のオブジェクトを作成する
               PdfContentByte dateText = writer.getDirectContent();

               //日付表示フォーマットの設定
                SimpleDateFormat data = new SimpleDateFormat("yyyy年MM月dd日");

               //表示テキストの設定開始
               dateText.saveState();
               dateText.beginText();
               //フォント(明朝)、サイズの設定
               dateText.setFontAndSize(baseFont, 15);
               //表示位置(右上端に表示させる)
               dateText.setTextMatrix(450, (document.getPageSize().getHeight() - 30));
               //文字表示
               dateText.showText(data.format(cl.getTime()));

                title.endText();
                title.restoreState();

            }

            //償却IDとDEPの表示設定
            {
                //償却IDのオブジェクトを作成する
                PdfContentByte azId = writer.getDirectContent();
                //DEPのオブジェクトを作成する
                PdfContentByte dep = writer.getDirectContent();

                //表示テキストの設定開始
                azId.saveState();
                azId.beginText();

                //フォント設定
                azId.setFontAndSize(baseFont, 15);

                //表示位置
                azId.setTextMatrix(100, (document.getPageSize().getHeight() - 100));

                //文字表示
                azId.showText("償却ID：---------ID");

                //償却IDについて設定完了
                azId.endText();
                azId.restoreState();

                //表示テキストの設定開始
                dep.saveState();
                dep.beginText();

                //フォント設定
                dep.setFontAndSize(baseFont, 15);

                //表示位置
                dep.setTextMatrix(100, (document.getPageSize().getHeight() - 120));

                //文字表示
                dep.showText("DEP：---------");

                //設定完了
                dep.endText();
                dep.restoreState();


            }

        document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
