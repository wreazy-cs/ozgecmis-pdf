package org.example;

public class Main {
    // Fotoğraf seçmek için 1-4 arası bir sayı verilebilir. Verilmezse 1 numaralı fotoğraf kullanılır.
    public static void main(String[] args) throws Exception {
        int fotoNo = 1;
        if (args.length > 0) {
            fotoNo = Integer.parseInt(args[0]);
        }

        OzgecmisOlusturucu olusturucu = new OzgecmisOlusturucu("ozgecmis.pdf", fotoNo);
        olusturucu.olustur();
        System.out.println("ozgecmis.pdf olusturuldu.");
    }
}
