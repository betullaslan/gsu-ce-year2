// YERDE KONUMU RASTGELE BELIRLENEN BIR KUTU OLACAK. KUTUNUN ORTASINDA BIR NAMLU OLACAK. NAMLU SOL-SAG TUSLARIYLA HAREKET EDECEK. 
// YUKARIDA DA BIR UCAK OLACAK VE SOLDAN SAGA DOGRU HAREKET EDECEK. EKRANIN DISINA CIKTIGINDA TEKRAR DONGU OLARAK HAREKETE BASLAYACAK.
// AMAC YUKARI TUSUYLA NAMLUDAN BIR TOP CIKMASINI SAGLAMAK VE UCAGI VURMAK. TOP OYUN ICINDEYKEN YENI BIR TOP GONDERILEMEYECEK.
// BOSLUK TUSUNA BASILDIGINDA OYUN BITECEK.
import java.awt.*;
import java.awt.event.*;

class kanvas extends Canvas implements KeyListener
{
    private int cizgiY = 600;   // yerdeki cizginin konumu

    private int kutuEn = 100, kutuBoy = 75;   // kutunun boyutu
    private int kutuX = (int) (600 * Math.random());   // kutunun x koordinati
    private int kutuY = cizgiY - kutuBoy;   // kutunun y koordinati

	private int ucakEn = 75, ucakBoy = 50, ucakHiz = 10;   // ucagin boyutu
	private int ucakX = 0, ucakY = 50;   // ucagin koordinatlari

	private int namluUzunluk = 75;   // namlunun uzunlugu 
	private int namluAci = 90;   // namlunun yer duzlemiyle yaptigi aci
	
	private int topBuyukluk = 10, topHiz = 40, topX, topY, topAci;   // topun bilgileri

	private Rectangle top, ucak;   // ucak ve topun carpisma durumunu incelemek icin

	private boolean topAtma = false;   // topun oyun icinde olmasi
	private boolean oyun = true;   // oyunun devam etmesi
	private boolean baslangic = true;   // yukari tusuna basildiktan sonraki ilk seferde namludan cikisi

	public kanvas()
	{
		setBackground(Color.white);
		addKeyListener(this); 
		setFocusable(true);   // mouse window icinde degilken de calisiyor
	}
	
	public void paint(Graphics p)
	{
		if(oyun)   // oyun devam ediyor
		{
			p.setColor((Color.black));
			p.drawLine(0, cizgiY, 1000, cizgiY);   // cizgi cizme
			p.fillRect(kutuX, kutuY, kutuEn, kutuBoy);   // kutu cizme

			p.drawLine(kutuX + (kutuEn / 2), kutuY, kutuX + (kutuEn / 2) + (int) (namluUzunluk * Math.cos(Math.toRadians(namluAci))), 
					kutuY - (int) (namluUzunluk * Math.sin(Math.toRadians(namluAci))));   // namlu cizme

			p.setColor(Color.blue);
			p.fillRect(ucakX, ucakY, ucakEn, ucakBoy);   // ucak cizme

			ucakX = ucakX + ucakHiz;   // ucak x dogrultusunda hareket ediyor

			if(ucakX > 980)
			{
				ucakX = 0;   // ucak ekran disina cikarsa baslangic konumuna donuyor
			}

			if(topAtma == true)   // yukari tusuna basildiginda yapilan islemler
			{
				if(baslangic)   // yukari tusuna basildiktan sonraki ilk sefer
				{
					topX = kutuX + (kutuEn / 2) + (int) (namluUzunluk * Math.cos(Math.toRadians(namluAci)));   // topun konumu namlunun ust noktasina gore olusturuluyor
					topY = kutuY - (int) (namluUzunluk * Math.sin(Math.toRadians(namluAci)));
					topAci = namluAci;
					baslangic = false;
				}

				topX = topX + (int) (topHiz * Math.cos(Math.toRadians(topAci)));   // topun x dogrultusundaki konumu
				topY = topY - (int) (topHiz * Math.sin(Math.toRadians(topAci)));   // topun y dogrultusundaki konumu
				
				p.setColor(Color.red);
				p.fillOval(topX, topY, topBuyukluk, topBuyukluk);   // top cizimi

				top = new Rectangle(topX, topY, topBuyukluk, topBuyukluk);
				ucak = new Rectangle(ucakX, ucakY, ucakEn, ucakBoy);

				if(ucak.intersects(top))   // top ucagi vurdugunda sifirlama yapiliyor
				{
					kutuX = (int) (600 * Math.random());
					topAtma = false;
					baslangic = true;
					topX = topY = 0;
				}

				if(topY < 0 || topX < 0 || topX > 980)   // top ekran disina ciktiginda yok oluyor
				{
					topAtma = false;
					baslangic = true;
					topX = topY = 0;
				}
			}

			try  
			{
				Thread.sleep(50);
			}
			catch(Exception e) {}
			repaint();
		}

		else  // space'a basilip oyun bittiginde yapiliyor
		{
			setBackground(Color.red);
			Font text = new Font("Times New Roman",Font.PLAIN, 40);
			p.setFont(text);
			p.drawString("OYUN BİTTİ",375, 300);
		}
	}

	public void keyPressed(KeyEvent e)
 	{
 		int tus = e.getKeyCode();
        switch(tus)
		{
			case(KeyEvent.VK_LEFT):
				namluAci = namluAci + 5;
				if(namluAci > 180)
				{
					namluAci = 180;
				}
				break;
			case(KeyEvent.VK_RIGHT):
				namluAci = namluAci - 5;
				if(namluAci < 0)
				{
					namluAci = 0;
				}
				break;
			case(KeyEvent.VK_UP):
				topAtma = true;
				break;
			case(KeyEvent.VK_SPACE):
				oyun = false;
				break;
        }
 		
 		repaint();	
 	}	
 		
 	public void keyReleased(KeyEvent e) {}	
 	public void keyTyped(KeyEvent e) {}			   
}

public class odev implements WindowListener
{
	private Frame a;
	private kanvas k;

	public odev()
	{
		a = new Frame();
		k = new kanvas();
		a.addWindowListener(this);
		a.add(k);
		a.setSize(1000,700);
		a.setResizable(false);
		a.setVisible(true);
	}
	
	public static void main(String args[])
	{
		new odev();
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) 
	{
		System.exit(0);
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}