package org.example;

import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.Image;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.BaseFont;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class OzgecmisOlusturucu {

    private static final String GORSEL_KLASORU = "src/main/resources/gorseller/";

    private final String dosyaAdi;
    private final String fotoYolu;
    private Font baslikFont;
    private Font altBaslikFont;
    private Font kalinFont;
    private Font normalFont;

    // fotoNo 1 ile 4 arasında olmalı, gorseller klasöründeki foto1.jpg ... foto4.jpg dosyalarından birini seçer
    public OzgecmisOlusturucu(String dosyaAdi, int fotoNo) {
        this.dosyaAdi = dosyaAdi;
        this.fotoYolu = GORSEL_KLASORU + "foto" + fotoNo + ".jpg";
    }

    public void olustur() throws Exception {
        fontlariHazirla();

        Document belge = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter.getInstance(belge, new FileOutputStream(dosyaAdi));
        belge.open();

        ustBolumuEkle(belge);

        baslikEkle(belge, "HAKKINDA");
        belge.add(new Paragraph("Yazılım geliştirmeye meraklı, öğrenmeye açık ve takım çalışmasına yatkın bir bilgisayar programcısı. "
                + "Nesne yönelimli programlama ve temiz kod yazmaya önem veriyor.", normalFont));

        baslikEkle(belge, "İŞ DENEYİMİ");
        isDeneyimiEkle(belge, "Junior Java Geliştirici", "Mavi Yazılım A.Ş.", "2021 - 2022",
                "Kurumsal stok takip uygulamasında Java ile yeni özellikler geliştirdi.");
        isDeneyimiEkle(belge, "Yazılım Geliştirici", "Güneş Teknoloji Ltd.", "2022 - 2024",
                "Müşteri yönetim sistemi için raporlama modülünü yazdı ve hataları giderdi.");
        isDeneyimiEkle(belge, "Kıdemli Yazılım Geliştirici", "Ay Bilişim", "2024 - Günümüz",
                "Küçük bir ekibe liderlik etti, kod incelemeleri yaptı ve yeni geliştiricilere eğitim verdi.");

        baslikEkle(belge, "EĞİTİM");
        belge.add(new Paragraph("Kırklareli Üniversitesi - Bilgisayar Programcılığı (2020 - 2022)", normalFont));

        baslikEkle(belge, "PROJELER");
        belge.add(new Paragraph("Kütüphane Otomasyonu: Java ve nesne yönelimli programlama ile kitap ödünç takip uygulaması.", normalFont));
        belge.add(new Paragraph("Hesap Makinesi: Konsol üzerinden çalışan, dört işlem yapan basit bir uygulama.", normalFont));
        belge.add(new Paragraph("Özgeçmiş Hazırlayıcı: Bu özgeçmişi PDF olarak üreten Java programı.", normalFont));

        baslikEkle(belge, "SERTİFİKALAR");
        belge.add(new Paragraph("Java ile Nesne Yönelimli Programlama Eğitimi (2022)", normalFont));
        belge.add(new Paragraph("Git ve GitHub ile Versiyon Kontrolü (2023)", normalFont));

        baslikEkle(belge, "YETENEKLER");
        belge.add(new Paragraph("Java, Nesne Yönelimli Programlama, Git, SQL, Maven", normalFont));

        baslikEkle(belge, "DİLLER");
        belge.add(new Paragraph("Türkçe (Ana dil), İngilizce (Orta seviye)", normalFont));

        baslikEkle(belge, "HOBİLER");
        belge.add(new Paragraph("Futbol, kitap okumak, yeni teknolojileri denemek", normalFont));

        belge.close();
    }

    private void fontlariHazirla() throws Exception {
        // Türkçe karakterler (ş, ğ, ı, İ) için Windows'taki Arial fontu Unicode olarak yüklenir.
        BaseFont taban = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        baslikFont = new Font(taban, 24, Font.BOLD);
        altBaslikFont = new Font(taban, 14, Font.BOLD);
        kalinFont = new Font(taban, 12, Font.BOLD);
        normalFont = new Font(taban, 11);
    }

    private void ustBolumuEkle(Document belge) throws Exception {
        PdfPTable tablo = new PdfPTable(new float[]{1, 3});
        tablo.setWidthPercentage(100);

        Image foto = Image.getInstance(fotoYolu);
        foto.scaleToFit(110, 140);
        PdfPCell fotoHucre = new PdfPCell(foto);
        fotoHucre.setBorder(0);
        tablo.addCell(fotoHucre);

        PdfPCell bilgiHucre = new PdfPCell();
        bilgiHucre.setBorder(0);
        bilgiHucre.addElement(new Paragraph("Samet Şafak", baslikFont));
        bilgiHucre.addElement(new Paragraph("Yazılım Geliştirici", altBaslikFont));
        bilgiHucre.addElement(new Paragraph("E-posta: samet@example.com", normalFont));
        bilgiHucre.addElement(new Paragraph("Telefon: 0555 000 00 00", normalFont));
        bilgiHucre.addElement(new Paragraph("Adres: Kırklareli, Türkiye", normalFont));
        tablo.addCell(bilgiHucre);

        tablo.setHorizontalAlignment(Element.ALIGN_LEFT);
        belge.add(tablo);
    }

    private void baslikEkle(Document belge, String metin) throws Exception {
        belge.add(new Paragraph("\n" + metin, altBaslikFont));
    }

    private void isDeneyimiEkle(Document belge, String unvan, String sirket, String tarih, String aciklama) throws Exception {
        belge.add(new Paragraph(unvan + " - " + sirket + " (" + tarih + ")", kalinFont));
        belge.add(new Paragraph(aciklama + "\n", normalFont));
    }
}
