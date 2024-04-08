// Bir dikdortgen olacak. Baslangic konumu ve dogrultusu rastgele belirlenen bir top ekranin kenarlarina carparsa yansima yapacak.
// Dikdortgenin icine girince hizi yariya dusecek, ciktiginda eski hizina donecek.
// Eger dikdortgenin kenarlarina 45 dereceden kucuk bir aciyla(dikdortgene bitisik) gelirse yansima yapacak.
import java.awt.*;
import java.awt.event.*;

class kanvas extends Canvas 
{
    private int rectX = 350, rectY = 200, rectEn = 250, rectBoy = 250;   // dikdörtgen ozellikleri

    private int topX = (int) (900 * Math.random());   // top ozellikleri
    private int topY = (int) (600 * Math.random());
    
    private int topBuyukluk = 10, topHiz = 25;   // yaricap, hiz
    private int dogrultuAci = randomSayiUretme();  

    private Rectangle dortgen, top;   // kesisme icin dikdrotgen olusturma
  
	public kanvas()
	{
		setBackground(Color.white);  
		setFocusable(true);
	}
	
	public void paint(Graphics p)
	{ 
        p.setColor(Color.yellow);
        p.fillRect(rectX, rectY, rectEn, rectBoy);   // dikdortgen olusturma

        p.setColor(Color.black);
        p.fillOval(topX - topBuyukluk, topY - topBuyukluk, 2* topBuyukluk, 2* topBuyukluk);   // top olusturma

        dortgen = new Rectangle(rectX, rectY, rectEn, rectBoy);   
        top = new Rectangle(topX - topBuyukluk, topY - topBuyukluk, 2 * topBuyukluk, 2 * topBuyukluk);

        if(dortgen.intersects(top))   // top dikdortgenin icinde hareket ediyor
        {
            topX = topX + (int) ((topHiz / 2) * Math.cos(Math.toRadians(dogrultuAci)));
            topY = topY - (int) ((topHiz / 2) * Math.sin(Math.toRadians(dogrultuAci)));
        }
        else   // top dikdortgenin disinda hareket ediyor
        {
            topX = topX + (int) (topHiz * Math.cos(Math.toRadians(dogrultuAci)));
            topY = topY - (int) (topHiz * Math.sin(Math.toRadians(dogrultuAci)));
        }

        if(topY - topBuyukluk <= 15 || topY + topBuyukluk  >= 655)   // top alt-uste carptiginda yansima yapacak
        {
            dogrultuAci = - dogrultuAci;
        }

        if(topX - topBuyukluk <= 15 || topX + topBuyukluk >= 975)   // top saga-sola carptiginda yansima yapacak
        {
            dogrultuAci = 180 - dogrultuAci;
        }

        if(dogrultuAci < 0)   // acinin daima pozitif olmasini sagliyor
        {
            dogrultuAci = dogrultuAci + 360;
        }

        if((topY + topBuyukluk <= rectY) && (topY + topBuyukluk >= rectY - 10) && (topX - topBuyukluk >= rectX) && 
        (topX + topBuyukluk <= rectX + rectEn) && ((dogrultuAci >= 180 && dogrultuAci <= 225) || (dogrultuAci >= 315 && dogrultuAci <= 360)))
        {   
            dogrultuAci = - dogrultuAci;   // dikdortgenin ust kenarina carpmasi
        }

        if((topY - topBuyukluk >= rectY + rectBoy) && (topY - topBuyukluk <= rectY + rectBoy + 10) && (topX - topBuyukluk >= rectX) && 
        (topX + topBuyukluk <= rectX + rectEn)&& ((dogrultuAci >= 0 && dogrultuAci <= 45) || (dogrultuAci >= 135 && dogrultuAci <= 180)))
        {   
            dogrultuAci = - dogrultuAci;   // dikdortgenin alt kenarina carpmasi
        }

        if((topX + topBuyukluk <= rectX) && (topX + topBuyukluk >= rectX - 10) && (topY - topBuyukluk >= rectY) && 
        (topY + topBuyukluk <= rectY + rectBoy) && ((dogrultuAci >= 45 && dogrultuAci <= 90) || (dogrultuAci >= 270 && dogrultuAci <= 315)))
        {   
            dogrultuAci = 180 - dogrultuAci;   // dikdortgenin sol kenarina carpmasi
        }

        if((topX - topBuyukluk >= rectX + rectEn) && (topX - topBuyukluk <= rectX + rectEn + 10) && (topY - topBuyukluk >= rectY) && 
        (topY + topBuyukluk <= rectY + rectBoy) && ((dogrultuAci >= 225 && dogrultuAci <= 270) || (dogrultuAci >= 90 && dogrultuAci <= 135)))
        {   
            dogrultuAci = 180 - dogrultuAci;   // dikdortgenin sag kenarina carpmasi
        }

        try  
        {
            Thread.sleep(50);
        }
        catch(Exception e) {}
        repaint();
	}
    
    public int randomSayiUretme()
	{
		int sayi = (int) (360 * Math.random());;
		
		if((sayi >= 0 && sayi <= 5) || (sayi >= 85 && sayi <= 95) || (sayi >= 175 && sayi <= 185) || 
			(sayi >= 265 && sayi <= 275) || (sayi >= 355 && sayi < 360))
		{
			return randomSayiUretme();
		}
	
		return sayi;
	}
}

public class soru2 implements WindowListener
{
	private Frame a;
	private kanvas k;

	public soru2()
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
		new soru2();
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