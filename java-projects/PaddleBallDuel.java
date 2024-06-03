// IKI OYUNCULU FUTBOL OYUNU. DIKDORTGEN KLAVYE TUSLARIYLA KONTROL EDILEREK TOPUN CIZGIYI GECMESI ENGELLENECEK.
// TOP ORTA NOKTADAN BASLAYACAK VE RANDOM DOGRULTUDA HAREKET EDECEK. KENARLARA VE DIKDORTGENE CARPTIGINDA YANSIMA YAPACAK.
// TOP CIZGIYI GECTIGINDE KAZANACAK. SKORLAR YAZILACAK. 3 OLAN KAZANACAK.
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class kanvas extends Canvas 
{
    private int cizgi1 = 55, cizgi2 = 930;    // cizgilerin konumlari   

    private int topX = 500, topY = 330, topBuyukluk = 15, topHiz = 15;   // top ozellikleri
	private double dogrultuAci = randomSayiUretme();   // topun rastgele belirlenen dogrultusu

    private int dort1X = cizgi1 + 5, dort1Y = 280, dort1En = 15 , dort1Boy = 100;   // 1.dikdortgen ozellikleri
    private int dort2X = cizgi2 - 20, dort2Y = 280, dort2En = 15 , dort2Boy = 100;   // 2.dikdortgen ozellikleri
    private int degisim = 15;   // tusa basildiginda dikdortgenin yer degistirme miktari
    
	private int skor1 = 0, skor2 = 0;   // oyuncularin skorlari
	private boolean kazanan1 = false, kazanan2 = false;   // oyunun kazanilmasi durumu
	
	private Rectangle rect1, rect2, top;   // top ve dikdortgenlerin kesisimi icin dikdortgen ve top olusturma
	
	private Set<Integer> pressedKeys = new HashSet<>();

	public kanvas()
	{
		setBackground(Color.white);  
		setFocusable(true);
		addKeyListener(new KeyAdapter() 
		{
            @Override
            public void keyPressed(KeyEvent e) 
			{
                int tus = e.getKeyCode();
                pressedKeys.add(tus);
                klavyeHareket();
            }

            @Override
            public void keyReleased(KeyEvent e) 
			{
                int tus = e.getKeyCode();
                pressedKeys.remove(tus);
            }
        });
	}
	
	public void paint(Graphics p)
	{
		if(!kazanan1 && !kazanan2)
		{ 
			p.setColor(Color.black);
			p.fillRect(cizgi1,0,1,700);   // 1.cizgi cizme
			p.fillRect(cizgi2,0,1,700);   // 2.cizgi cizme
			
			p.fillRect(dort1X, dort1Y, dort1En, dort1Boy);   // 1.dikdortgen cizme
			p.fillRect(dort2X, dort2Y, dort2En, dort2Boy);   // 2.dikdortgen cizme
			
			p.fillOval(topX - topBuyukluk, topY - topBuyukluk, topBuyukluk, topBuyukluk);  // top cizme
			topX = topX + (int) (topHiz * Math.cos(Math.toRadians(dogrultuAci)));
			topY = topY - (int) (topHiz * Math.sin(Math.toRadians(dogrultuAci)));

			if(dort1Y <= 2)   // dikdortgenlerin ekran dısına cikmamasi icin kontrol
			{
				dort1Y = 3;
			}
			if(dort1Y + dort1Boy >= 663)
			{
				dort1Y = 663 - dort1Boy;
			}
			if(dort2Y <= 2)
			{
				dort2Y = 3;
			}
			if(dort2Y + dort2Boy >= 663)
			{
				dort2Y = 663 - dort2Boy;
			}
			
			rect1 =  new Rectangle(dort1X, dort1Y, dort1En, dort1Boy);
			rect2 =  new Rectangle(dort2X, dort2Y, dort2En, dort2Boy);
			top =  new Rectangle(topX - topBuyukluk, topY - topBuyukluk, topBuyukluk, topBuyukluk);

			if(topY - topBuyukluk <= 10 || topY + topBuyukluk >= 675)    // kenarlara carpma durumu
			{
				dogrultuAci = - dogrultuAci;
			}
			
			if(rect1.intersects(top) || rect2.intersects(top))   // dikdortgenlere carpmasi durumu
			{
				dogrultuAci = 180 - dogrultuAci;
			}

			if(topX + 6 < cizgi1)  // topun 1.cizgiyi gecmesi durumu
			{
				skor2 = skor2 + 1;
				if(skor2 == 3)
				{
					kazanan2 = true;
				}

				topX = 500;   // sifirlama
				topY = 330;   
				dort1X = cizgi1 + 5;
				dort1Y = 280;
				dort2X = cizgi2 - 20;
				dort2Y = 280;
				dogrultuAci = randomSayiUretme();
			
				try  
				{
					Thread.sleep(80);
				}
				catch(Exception e) {}
				repaint();
			}

			if(topX - 21 > cizgi2)   // topun 2.cizgiyi gecmesi durumu
			{
				skor1 = skor1 + 1;

				if(skor1 == 3)
				{
					kazanan1 = true;
				}
				
				topX = 500;   // sifirlama
				topY = 330;   
				dort1X = cizgi1 + 5;
				dort1Y = 280;
				dort2X = cizgi2 - 20;
				dort2Y = 280;
				dogrultuAci = randomSayiUretme();
				
				try  
				{
					Thread.sleep(80);
				}
				catch(Exception e) {}
				repaint();
			}

			p.drawString("oyuncu 1", 5,20);   // yazilari yazma
			p.drawString("oyuncu 2", 935,20);

			Font text = new Font("Times New Roman",Font.PLAIN,30);
			p.setFont(text);
			p.drawString(String.valueOf(skor1),20,50);  
			p.drawString(String.valueOf(skor2),950,50);  

			try  
			{
				Thread.sleep(50);
			}
			catch(Exception e) {}
			repaint();
		}

		else if(kazanan1)   // 1.oyuncunun kazanmasi durumu
		{
			setBackground(Color.red);
			p.setColor(Color.white);
			Font text = new Font("Times New Roman",Font.PLAIN,30);
			p.setFont(text);
			p.drawString("OYUNCU 1 KAZANDINIZ", 330,300);

			try  
			{
				Thread.sleep(150);
			}
			catch(Exception e) {}
			repaint();
		}
		
		else   // 2.oyuncunun kazanmasi durumu
		{
			setBackground(Color.red);
			p.setColor(Color.white);
			Font text = new Font("Times New Roman",Font.PLAIN,30);
			p.setFont(text);
			p.drawString("OYUNCU 2 KAZANDINIZ", 330,300);

			try  
			{
				Thread.sleep(150);
			}
			catch(Exception e) {}
			repaint();
		}		
	}

	public int randomSayiUretme()
	{
		int sayi = (int) (360 * Math.random());;
		
		if((sayi >= 0 && sayi <= 15) || (sayi >= 75 && sayi <= 105) || (sayi >= 165 && sayi <= 195) || 
			(sayi >= 255 && sayi <= 285) || (sayi >= 345 && sayi < 360))
		{
			return randomSayiUretme();
		}
	
		return sayi;
	}

	private void klavyeHareket() 
	{
        if(pressedKeys.contains(KeyEvent.VK_UP)) 
		{
            dort2Y = dort2Y - degisim;
        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)) 
		{
            dort2Y = dort2Y + degisim;
        }
        if(pressedKeys.contains(KeyEvent.VK_A)) 
		{
            dort1Y = dort1Y - degisim;
        }
        if(pressedKeys.contains(KeyEvent.VK_Z)) 
		{
            dort1Y = dort1Y + degisim;
        }

        repaint();
    }
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