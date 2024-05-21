import java.awt.*;
import java.awt.event.*;
import java.util.*;

class top 
{
    int x, y, hiz;
    Color renk;

    public top(int x, int y, int hiz, Color renk) 
    {
        this.x = x;
        this.y = y;
        this.hiz = hiz;
        this.renk = renk;
    }

    public void hareketEt() 
    {
        y += hiz;
    }

    public void ciz(Graphics g) 
    {
        g.setColor(renk);
        g.fillOval(x, y, 20, 20);
    }
}

class kanvas extends Canvas implements KeyListener 
{
    private int cizgiY = 575, skor = 0;;
    private int sepetEn = 75, sepetBoy = 50, sepetX = 425, sepetY = cizgiY - sepetBoy;
    private int topX = sayiOlustur_TopX(), topY = 50, topBuyukluk = 20, topHiz = sayiOlustur_TopHiz();
    private Color renkSepet;
    private top[] toplar;
    private boolean oyun = false, baslangic = true, sepetRenkOlustur = true;

    public kanvas() 
    {
        setBackground(Color.white);
        addKeyListener(this);
        setFocusable(true);

        toplar = new top[4];
        for (int i = 0; i < 4; i++) 
        {
            toplar[i] = new top(sayiOlustur_TopX(), 50, sayiOlustur_TopHiz(), renkOlustur());
        }
    }

    public void paint(Graphics g) 
    {
        if (baslangic)
        {
            setBackground(Color.red);
            g.setColor(Color.white);

            Font text1 = new Font("Times New Roman", Font.PLAIN, 30);
            g.setFont(text1);
            g.drawString("OYUNUN KURALLARI", 330, 200);

            Font text2 = new Font("Times New Roman", Font.PLAIN, 17);
            g.setFont(text2);
            g.drawString("Oyunun amaci yukaridan rastgele konumlardan, rastgele renkte ve hizda dusen toplar arasindan asagidaki sepet ile ayni renkte olani yakalamaktir.", 10,300);
            g.drawString("Klavyenin sol-sag tuslari yardimiyla sepeti hareket ettiriniz ve ayni renkteki topu yakalamaya calisiniz.", 150, 325);
            g.drawString("Topu yakalarsaniz skor 1 artacak, yakalayamazsaniz skor degismeyecek ve farkli renkteki topu yakalarsaniz skor 1 azalacaktir.", 70, 350);
            g.drawString("Skor 5 oldugunda oyun bitecektir." , 370,375);
            g.drawString("Oyunu baslatmak icin SPACE tusuna basiniz.", 330, 400);
            g.drawString("Oyunu bitirmek icin ENTER tusuna basiniz." , 335, 425);
            
            try 
            {
                Thread.sleep(150);
            } 
            catch (Exception e) {}
            repaint();
        }

        if (oyun) 
        {
            setBackground(Color.white);
            g.setColor(Color.black);

            baslangic = false;
            g.drawLine(0, cizgiY, 1000, cizgiY);

            if (sepetRenkOlustur) 
            {
                renkSepet = renkOlustur();
                sepetRenkOlustur = false;
            }

            g.setColor(renkSepet);
            g.fillRect(sepetX, sepetY, sepetEn, sepetBoy);
            g.fillOval(topX, topY, topBuyukluk, topBuyukluk);
            topY = topY + topHiz;

            Rectangle sepetCakisma = new Rectangle(sepetX, sepetY, sepetEn, sepetBoy);

            for (top Top : toplar) 
            {
                Top.hareketEt();
                Top.ciz(g);

                Rectangle topCakisma = new Rectangle(Top.x, Top.y, 20, 20);

                if (Top.y > cizgiY + 25) 
                {
                    Top.x = sayiOlustur_TopX();
                    Top.y = 50;
                    Top.hiz = sayiOlustur_TopHiz();
                }

                if (sepetCakisma.intersects(topCakisma)) 
                {
                    Top.x = sayiOlustur_TopX();
                    Top.y = 50;
                    Top.hiz = sayiOlustur_TopHiz();
                    skor--;
                }
            }

            Rectangle topCakisma = new Rectangle(topX, topY, topBuyukluk, topBuyukluk);

            if(topY > cizgiY + 25)
            {
                sepetRenkOlustur = true;
                topX = sayiOlustur_TopX();
                topY = 50;
                topHiz = sayiOlustur_TopHiz();
            }

            if(sepetCakisma.intersects(topCakisma))
            {
                sepetRenkOlustur = true;
                topX = sayiOlustur_TopX();
                topY = 50;
                topHiz = sayiOlustur_TopHiz();
                skor++;
            }

            if (skor == 5)
            {
                oyun = false;
            }

            Font text = new Font("Times New Roman", Font.PLAIN, 20);
            g.setFont(text);
            g.drawString("SKOR: " + skor, 20, 30);

            try 
            {
                Thread.sleep(50);
            } 
            catch (Exception e) {}
            repaint();
        }

        else if (baslangic == false && oyun == false)
        {
            skor = 0;
            setBackground(Color.red);
            g.setColor(Color.white);

            Font text = new Font("Times New Roman", Font.PLAIN, 40);
            g.setFont(text);
            g.drawString("OYUN BITTI", 375, 300);

            try 
            {
                Thread.sleep(150);
            } 
            catch (Exception e) {}
            repaint();
        }
    }

    public static Color renkOlustur() 
    {
        Random random = new Random();

        int kirmizi = random.nextInt(256);
        int yesil = random.nextInt(256);
        int mavi = random.nextInt(256);

        if(kirmizi >=240 || yesil >= 240 || mavi >= 240)
        {
            return renkOlustur();
        }

        return new Color(kirmizi, yesil, mavi);
    }

    public int sayiOlustur_TopX()
    {
        int sayi = (int) (950 * Math.random());

        if(sayi % 25 == 0 && sayi != 0)
        {
            return sayi;
        }

        return sayiOlustur_TopX();
    }

    public int sayiOlustur_TopHiz()
    {
        int sayi = (int) (18 * Math.random());

        if(sayi >= 7 && sayi <= 17)
        {
            return sayi;
        }

        return sayiOlustur_TopHiz();
    }

    public void keyPressed(KeyEvent e) 
    {
        int tus = e.getKeyCode();
        switch (tus) 
        {
            case (KeyEvent.VK_LEFT):
                sepetX -= 15;
                break;
            case (KeyEvent.VK_RIGHT):
                sepetX += 15;
                break;
            case (KeyEvent.VK_SPACE):
                oyun = true;
                break;
            case (KeyEvent.VK_ENTER):
                oyun = false;
                break;
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

public class oyun implements WindowListener 
{
    private Frame a;
    private kanvas k;

    public oyun() 
    {
        a = new Frame();
        k = new kanvas();
        a.addWindowListener(this);
        a.add(k);
        a.setSize(1000, 700);
        a.setResizable(false);
        a.setVisible(true);
    }

    public static void main(String args[]) 
    {
        new oyun();
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