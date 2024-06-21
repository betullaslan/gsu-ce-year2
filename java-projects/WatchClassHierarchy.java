// KENDINIZ ICIN ONEMLI OLAN EN AZ 2 ADET VARLIGI(NESNE) ANLATAN BIR CLASS'LAR TOPLULUGU YAZINIZ.
import java.util.Calendar;

class Saat 
{
    protected int saat;
    protected int dakika;
    protected int saniye;
    private String marka;
    private String model;
    private String renk;      

    public Saat(int saat, int dakika, int saniye, String marka, String model, String renk) 
    {
        this.saat = saat;
        this.dakika = dakika;
        this.saniye = saniye;
        this.marka = marka;
        this.model = model;
        this.renk = renk;
    }

    public void saatBilgileriniYazdir() 
    {
        System.out.println("Saat: " + saat);
        System.out.println("Dakika: " + dakika);
        System.out.println("Saniye: " + saniye);
        System.out.println("Marka: " + marka);
        System.out.println("Model: " + model);
        System.out.println("Renk: " + renk);
    }
}

class KolSaati extends Saat 
{
    private boolean kronometre;
    private boolean alarm;
    protected double ekranBoyutu;

    public KolSaati(int saat, int dakika, int saniye, String marka, String model, String renk, boolean kronometre, boolean alarm, double ekranBoyutu) 
    {
        super(saat, dakika, saniye, marka, model, renk);
        this.kronometre = kronometre;
        this.alarm = alarm;
        this.ekranBoyutu = ekranBoyutu;
    }

    public boolean kronometreVarYok() 
    {
        return kronometre;
    }

    public boolean alarmVarYok() 
    {
        return alarm;
    }
}

class DijitalKolSaati extends KolSaati
{
    private int alarmSaat;
    private int alarmDakika;
    protected int kronometreSure;

    Calendar tarih = Calendar.getInstance();
    private int gun = tarih.get(Calendar.DAY_OF_MONTH); 
    private int ay = tarih.get(Calendar.MONTH) + 1;  
    private int yil = tarih.get(Calendar.YEAR);   

    public DijitalKolSaati(int saat, int dakika, int saniye, String marka, String model, String renk, 
                        boolean kronometre, boolean alarm, double ekranBoyutu) 
    {
        super(saat, dakika, saniye, marka, model, renk, kronometre, alarm, ekranBoyutu);
        this.alarmSaat = -1; 
        this.alarmDakika = -1; 
        this.kronometreSure = 0; 
    }

    public void tarihBilgileriniYazdir()
    {
        System.out.println("Tarih: " + gun + "/" + ay + "/" + yil);
    }
    
    public void dijitalKolSaatiBilgileriniYazdir() 
    {
        saatBilgileriniYazdir();
        System.out.println("Ekran Boyutu: " + ekranBoyutu + " mm");

        if(alarmVarYok())
        {
            if(alarmSaat != -1 && alarmDakika != -1) 
            {
                System.out.println("Alarm: " + alarmSaat + "." + alarmDakika);
            } 
            else 
            {
                System.out.println("Alarm kurulmadi");
            }
        }
        else
        {
            System.out.println("Alarm Ozelligi: Yok");
        }

        if(kronometreVarYok())
        {
            if(kronometreSure > 0) 
            {
                System.out.println("Kronometre Suresi: " + kronometreSure + " saniye");
            } 
            else 
            {
                System.out.println("Kronometre baslatilmadi");
            }
        }
        else
        {
            System.out.println("Kronometre Ozelligi: Yok");
        }  
    }

    public void alarmKur(int saat, int dakika) 
    {
        alarmSaat = saat;
        alarmDakika = dakika;
        System.out.println("Alarm " + saat + "." + dakika + " saatine kuruldu");
    }

    public void kronometreBaslat() 
    {
        if(kronometreSure == 0) 
        {
            System.out.println("Kronometre baslatildi");
            int sayi = 0;
            while(sayi == 0)
            {
                sayi = (int) (60 * Math.random());
            }

            kronometreSure = sayi;
        } 
        else 
        {
            System.out.println("Kronometre calisiyor");
        }
    }

    public void kronometreSifirla() 
    {
        if(kronometreSure > 0) 
        {
            System.out.println("Kronometre sifirlandi");
            kronometreSure = 0;
        } 
        else 
        {
            System.out.println("Kronometre onceden sifirlanmisti");
        }
    }
}

class AnalogKolSaati extends KolSaati 
{
    public AnalogKolSaati(int saat, int dakika, int saniye, String marka, String model, String renk, 
                    boolean kronometre, boolean alarm, double ekranBoyutu) 
    {
        super(saat, dakika, saniye, marka, model, renk, kronometre, alarm, ekranBoyutu);
    }

    public void analogKolSaatiBilgileriniYazdir() 
    {
        saatBilgileriniYazdir();
        System.out.println("Ekran Boyutu: " + ekranBoyutu + " mm");

        if(alarmVarYok()) 
        {
            System.out.println("Alarm Ozelligi: Var");
        } 
        else 
        {
            System.out.println("Alarm Ozelligi: Yok");
        }

        if(kronometreVarYok()) 
        {
            System.out.println("Kronometre Ozelligi: Var");
        } 
        else 
        {
            System.out.println("Kronometre Ozelligi: Yok");
        }
    }
}

public class odev
{
    public static void main(String[] args) 
    {
        Calendar simdi = Calendar.getInstance();
        int simdikiSaat = simdi.get(Calendar.HOUR_OF_DAY); 
        int simdikiDakika = simdi.get(Calendar.MINUTE);    
        int simdikiSaniye = simdi.get(Calendar.SECOND);
        
        System.out.println("------------------------------");
        AnalogKolSaati analog = new AnalogKolSaati(simdikiSaat, simdikiDakika, simdikiSaniye, "Casio", "Edifice", "Beyaz", false, false, 37.2);
        System.out.println("Analog Kol Saati Bilgileri");
        analog.analogKolSaatiBilgileriniYazdir();
    
        System.out.println("------------------------------");
        DijitalKolSaati dijital = new DijitalKolSaati(simdikiSaat, simdikiDakika, simdikiSaniye, "Xiaomi", "Smart Band", "Siyah" , true, true, 28.5);
        System.out.println("Dijital Kol Saati Bilgileri");
        dijital.dijitalKolSaatiBilgileriniYazdir();
        dijital.tarihBilgileriniYazdir();

        System.out.println("------------------------------");
        dijital.alarmKur(12, 15);
        dijital.kronometreBaslat();
        
        System.out.println("------------------------------");
        System.out.println("Alarm Kurulduktan ve Kronometre Baslatildiktan Sonraki Guncellenmis Dijital Kol Saati Bilgileri");

        if(dijital.saniye + dijital.kronometreSure >= 60)
        {
            dijital.dakika ++;
            dijital.saniye = dijital.saniye + dijital.kronometreSure - 60;
        }
        else
        {
            dijital.saniye = dijital.saniye + dijital.kronometreSure;
        }

        dijital.dijitalKolSaatiBilgileriniYazdir();
        dijital.tarihBilgileriniYazdir();
    }
}