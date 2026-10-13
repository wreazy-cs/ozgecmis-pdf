# Özgeçmiş PDF Oluşturucu

Bu proje, Java ile PDF formatında özgeçmiş oluşturan basit bir uygulamadır. OOP dersi özgeçmiş ödevi için hazırladım. Program çalışınca proje klasöründe `ozgecmis.pdf` dosyası oluşuyor.

## Ne Yapıyor?

- PDF formatında özgeçmiş üretir.
- Özgeçmişte bir fotoğraf vardır. 4 fotoğraf arasından seçilebilir.
- 3 tane hayali iş deneyimi vardır.
- Yönergedeki şartlara ek olarak projeler, sertifikalar, diller ve hobiler bölümleri de vardır.
- Türkçe karakterler (ş, ğ, ı, İ) PDF'te doğru görünür.
- Arayüzü yoktur, konsoldan çalışır.

## Kullandığım Araçlar

| Araç | Ne için kullandım |
|---|---|
| Java 27 | Programı yazmak için |
| Maven | Projeyi yönetmek ve kütüphaneyi indirmek için |
| [OpenPDF 3.0.5](https://github.com/LibrePDF/OpenPDF) | PDF dosyası üretmek için |

PDF dosyasını sıfırdan yazmak çok zor olduğu için hazır bir kütüphane kullandım. OpenPDF ücretsiz ve açık kaynak olduğu için onu seçtim. Maven ile `pom.xml` dosyasına tek bir bağımlılık olarak ekledim.

## Proje Yapısı

```
ozgecmis-pdf
├── pom.xml
├── ozgecmis.pdf
└── src/main
    ├── java/org/example
    │   ├── Main.java
    │   └── OzgecmisOlusturucu.java
    └── resources/gorseller
        ├── foto1.jpg
        ├── foto2.jpg
        ├── foto3.jpg
        └── foto4.jpg
```

Fotoğraflar yapay zeka ile üretilmiş, gerçekte var olmayan kişilere aittir. [thispersondoesnotexist.com](https://thispersondoesnotexist.com) sitesinden indirdim.

- **Main**: Programın başladığı yerdir. Sadece `OzgecmisOlusturucu` nesnesini oluşturur ve `olustur()` metodunu çağırır.
- **OzgecmisOlusturucu**: PDF'i üreten sınıftır. Yönergede PDF işinin `main` içinde yapılmaması gerektiği yazdığı için ayrı bir sınıfa koydum.

## Hangi Nesneleri Neden Kullandım?

### Kendi sınıfım

- `OzgecmisOlusturucu`: PDF üretme işini `main`'den ayırmak için yazdım. Dosya adını ve fotoğraf numarasını kurucuda (constructor) alıyor.

### OpenPDF sınıfları

| Sınıf | Neden |
|---|---|
| `Document` | PDF belgesinin kendisi. A4 boyutu ve kenar boşlukları bununla ayarlanıyor. |
| `PdfWriter` | Belgeyi dosyaya yazıyor. |
| `Paragraph` | Başlıkları ve yazıları eklemek için. |
| `Font` ve `BaseFont` | Yazı tipini ve boyutunu ayarlamak için. Türkçe karakterler için Arial fontunu Unicode olarak yükledim. |
| `Image` | Fotoğrafı okuyup küçültmek için. |
| `PdfPTable` ve `PdfPCell` | Fotoğrafı ve kişisel bilgileri yan yana koymak için. |

### Java sınıfı

- `FileOutputStream`: PDF dosyasını bilgisayara kaydetmek için.

## Erişim Belirleyiciler

- `public`: `Main` ve `OzgecmisOlusturucu` sınıfları, kurucu ve `olustur()` metodu. `Main` bunlara dışarıdan ulaşabilmeli.
- `private`: `dosyaAdi`, `fotoYolu`, fontlar ve yardımcı metotlar (`fontlariHazirla`, `ustBolumuEkle`, `baslikEkle`, `isDeneyimiEkle`). Bunları sadece sınıfın kendisi kullanıyor, dışarıdan değiştirilmesini istemedim.

## Nasıl Çalıştırılır?

Gerekenler: JDK 27 ve IntelliJ IDEA (veya Maven).

1. Projeyi indirin veya klonlayın.
2. IntelliJ'de açın ve Maven değişikliklerini yükleyin (kütüphane iner).
3. `Main` sınıfını çalıştırın.

Program proje klasöründe `ozgecmis.pdf` dosyasını oluşturur. Programı proje klasöründen çalıştırmak gerekir, çünkü fotoğraflar bu klasöre göre aranır.

Fotoğraf seçmek için programa 1 ile 4 arasında bir sayı verilebilir. Sayı verilmezse 1 numaralı fotoğraf kullanılır. IntelliJ'de bunu *Run > Edit Configurations > Program arguments* kısmına yazarak yapabilirsiniz (örneğin `3`).

> **Not:** Yazı tipi olarak `C:/Windows/Fonts/arial.ttf` kullandığım için program Windows'ta çalışır.

## Fotoğrafı Değiştirmek

Kendi fotoğrafınızı kullanmak için `src/main/resources/gorseller` klasöründeki `foto1.jpg` ... `foto4.jpg` dosyalarından birini aynı adla değiştirmeniz yeterli.

## Yapay Zeka Kullanımı

Bu projede **Claude Code** üzerinden destek alınmıştır. Claude Code'dan şunlar için yararlandım:

- Ödev yönergesini okumak ve neler yapılması gerektiğini anlamak.
- OpenPDF kütüphanesini bulmak ve Maven ile projeye eklemek.
- PDF'i üreten `OzgecmisOlusturucu` sınıfının kodunu yazmak.
- Yapay zeka ile üretilmiş fotoğrafları indirmek.
- README ve `.gitignore` dosyalarını hazırlamak.
