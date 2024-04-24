import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class odev implements WindowListener, ActionListener
{
	GridBagLayout a = new GridBagLayout();
	GridBagConstraints b = new GridBagConstraints();

	Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
	
	Frame f = new Frame(); 
	Panel p = new Panel();
	Label l;

	Object tus;
	String sembol;
	String metin = "";

	double sayi1 = 0;
	int usSayi1 = - 1, usSayi1_Tmp = - 1, usKesir1 = 0, usKesir1_Tmp = - 1, sayi1Uzunluk = 0;
	int[] arrSayi1 = new int[20];
	int[] arrSayi1Kesir = new int[20];
	boolean sayi1Var = false;
	boolean sayi1Virgul = false;
	boolean sayi1Isaret = true;
	boolean sayi1Gir = true;
	boolean sayi1VirgulGir = false;
	
	double sayi2 = 0;
	int usSayi2 = - 1, usSayi2_Tmp = - 1, usKesir2 = 0, usKesir2_Tmp = - 1, sayi2Uzunluk = 0;
	int[] arrSayi2 = new int[20];
	int[] arrSayi2Kesir = new int[20];
	boolean sayi2Var = false;
	boolean sayi2Virgul = false;
	boolean sayi2Isaret = true;
	boolean sayi2Gir = true;
	boolean sayi2Kontrol = true;
	boolean sayi2VirgulGir = false;
	
	int islem = - 1;
	boolean islemYap = false;
	boolean operator2 = false;
	boolean baslangicOperator = true;
	double sonuc, cevap;
	boolean hata = false;
	
	String sonucString;
	int sonucRakam;
	int sonucTamKisim;
	double sonucKesirliKisim;

	public odev()
	{
		p.setLayout(a);
		b.fill = GridBagConstraints.BOTH;
		b.insets = new Insets(3,3,3,3);
		b.weightx = 0.8; 
		b.weighty = 0.8;

		Font font = new Font("Times New Roman", Font.PLAIN, 25);
		Font fontLabel = new Font("Times New Roman", Font.PLAIN, 25);
		
		b.gridx = 0;
		b.gridy = 0;
		b.gridwidth = 4;
		b.gridheight = 1;
		l = new Label();
        l.setAlignment(Label.RIGHT);
		l.setFont(fontLabel);
        l.setForeground(Color.black); 
		a.setConstraints(l, b);
		p.add(l); 
		
		b.gridx = 0;
		b.gridy = 5;
		b.gridwidth = 2;
		b.gridheight = 1;
		b0 = new Button("0");
		b0.setFont(font);
		a.setConstraints(b0, b);
		p.add(b0);
		
		b.gridx = 0;
		b.gridy = 4;
		b.gridwidth = 1;
		b.gridheight = 1;
		b1 = new Button("1");
		b1.setFont(font);
		a.setConstraints(b1, b);
		p.add(b1);

		b.gridx = 1;
		b.gridy = 4;
		b.gridwidth = 1;
		b.gridheight = 1;
		b2 = new Button("2");
		b2.setFont(font);
		a.setConstraints(b2, b);
		p.add(b2);

		b.gridx = 2;
		b.gridy = 4;
		b.gridwidth = 1;
		b.gridheight = 1;
		b3 = new Button("3");
		b3.setFont(font);
		a.setConstraints(b3, b);
		p.add(b3);

		b.gridx = 0;
		b.gridy = 3;
		b.gridwidth = 1;
		b.gridheight = 1;
		b4 = new Button("4");
		b4.setFont(font);
		a.setConstraints(b4, b);
		p.add(b4);

		b.gridx = 1;
		b.gridy = 3;
		b.gridwidth = 1;
		b.gridheight = 1;
		b5 = new Button("5");
		b5.setFont(font);
		a.setConstraints(b5, b);
		p.add(b5);

		b.gridx = 2;
		b.gridy = 3;
		b.gridwidth = 1;
		b.gridheight = 1;
		b6 = new Button("6");
		b6.setFont(font);
		a.setConstraints(b6, b);
		p.add(b6);

		b.gridx = 0;
		b.gridy = 2;
		b.gridwidth = 1;
		b.gridheight = 1;
		b7 = new Button("7");
		b7.setFont(font);
		a.setConstraints(b7, b);
		p.add(b7);

		b.gridx = 1;
		b.gridy = 2;
		b.gridwidth = 1;
		b.gridheight = 1;
		b8 = new Button("8");
		b8.setFont(font);
		a.setConstraints(b8, b);
		p.add(b8);

		b.gridx = 2;
		b.gridy = 2;
		b.gridwidth = 1;
		b.gridheight = 1;
		b9 = new Button("9");
		b9.setFont(font);
		a.setConstraints(b9, b);
		p.add(b9);

		b.gridx = 0;
		b.gridy = 1;
		b.gridwidth = 1;
		b.gridheight = 1;
		b10 = new Button("C");
		b10.setFont(font);
		a.setConstraints(b10, b);
		p.add(b10);

		b.gridx = 1;
		b.gridy = 1;
		b.gridwidth = 1;
		b.gridheight = 1; 
		b11 = new Button("/");
		b11.setFont(font);
		a.setConstraints(b11, b);
		p.add(b11);

		b.gridx = 2;
		b.gridy = 1;
		b.gridwidth = 1;
		b.gridheight = 1;
		b12 = new Button("*");
		b12.setFont(font);
		a.setConstraints(b12, b);
		p.add(b12);

		b.gridx = 3;
		b.gridy = 1;
		b.gridwidth = 1;
		b.gridheight = 1;
		b13 = new Button("-");
		b13.setFont(font);
		a.setConstraints(b13, b);
		p.add(b13);

		b.gridx = 3;
		b.gridy = 2;
		b.gridwidth = 1;
		b.gridheight = 2;
		b14 = new Button("+");
		b14.setFont(font);
		a.setConstraints(b14, b);
		p.add(b14);

		b.gridx = 3;
		b.gridy = 4;
		b.gridwidth = 1;
		b.gridheight = 3;
		b15 = new Button("=");
		b15.setFont(font);
		a.setConstraints(b15, b);
		p.add(b15);

		b.gridx = 2;
		b.gridy = 5;
		b.gridwidth = 1;
		b.gridheight = 1;
		b16 = new Button(",");
		b16.setFont(font);
		a.setConstraints(b16, b);
		p.add(b16);

		b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);
        b12.addActionListener(this);
        b13.addActionListener(this);
        b14.addActionListener(this);
        b15.addActionListener(this);
        b16.addActionListener(this);

		p.setBackground(Color.white);
		f.add(p);
		
		f.addWindowListener(this);
		f.pack();
		f.setSize(300,420);
		f.setResizable(false);
		f.setVisible(true);

		Dimension frameSize = f.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		f.setLocation(x, y);
	}

	public double toplama(double a, double b)
	{
		cevap = sayi1 + sayi2;
		return cevap;
	}

	public double cikarma(double a, double b)
	{
		cevap = sayi1 - sayi2;
		return cevap;
	}

	public double carpma(double a, double b)
	{
		cevap = sayi1 * sayi2;
		return cevap;
	}
	
	public double bolme(double a, double b)
	{
		cevap = sayi1 / sayi2;
		return cevap;
	}

	public void sifirlama()
	{
        metin = "";
		sayi1Var = false; 
		sayi2Var = false;
		sayi1Gir = true;
		sayi2Gir = true;

		Arrays.fill(arrSayi1, 0);
		Arrays.fill(arrSayi1Kesir, 0);

		Arrays.fill(arrSayi2, 0);
		Arrays.fill(arrSayi2Kesir, 0);

		sayi1 = 0; sayi1Uzunluk = 0;
		usSayi1 = - 1; usSayi1_Tmp = - 1;
		usKesir1 = 0; usKesir1_Tmp = - 1;
		sayi1Virgul = false;
		sayi1Isaret = true;
		sayi1VirgulGir = false;
		
		sayi2 = 0; sayi2Uzunluk = 0;
		usSayi2 = - 1; usSayi2_Tmp = - 1;
		usKesir2 = 0; usKesir2_Tmp = - 1;
		sayi2Virgul = false;
		sayi2Isaret = true;
		sayi2Kontrol = true;
		sayi2VirgulGir = false;

		islem = - 1;
		islemYap = false;
		operator2 = false; 
		baslangicOperator = true;
		hata = false;
	}

	public static void main(String args[])
	{
        new odev();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		sayi2Kontrol = true;
		
		tus = e.getSource();
		sembol = e.getActionCommand();
		
		if (((sayi1Gir && sayi1VirgulGir) || (sayi2Gir && sayi2VirgulGir)) && tus == b16)
		{
			
		}

		else
		{
			metin = metin + sembol; 
			l.setText(metin);
		}
		
		if (tus == b10)
		{
			sifirlama();
			l.setText(metin);
		}

		else
		{
			if (tus == b13 && !sayi1Var && baslangicOperator)
			{
				sayi1Isaret = false;
				baslangicOperator = false;
			}
			
			else if (tus == b14 && !sayi1Var && baslangicOperator)
			{
				sayi1Isaret = true;
				baslangicOperator = false;
			}

			else if (!sayi1Var && (tus == b11 || tus == b12|| tus == b13 || tus == b14 || tus == b15 || tus == b16))
			{
				l.setText("HATA");
				sifirlama();
			}

			else if ((tus == b0 || tus == b1 || tus == b2 || tus == b3 || tus == b4 || tus == b5 || 
				tus == b6 || tus == b7 || tus == b8 || tus == b9) && !sayi1Virgul && sayi1Gir)
			{
				arrSayi1[usSayi1 + 1] = Integer.valueOf(sembol);
				usSayi1++;
				usSayi1_Tmp++;
				sayi1Var = true;
				sayi1Uzunluk++;
			}
			
			else if ((tus == b0 || tus == b1 || tus == b2 || tus == b3 || tus == b4 || tus == b5 || 
					tus == b6 || tus == b7 || tus == b8 || tus == b9) && sayi1Virgul && sayi1Gir)
			{
				arrSayi1Kesir[usKesir1_Tmp + 1] = Integer.valueOf(sembol);
				usKesir1--;
				usKesir1_Tmp++;
			}
			
			else if ((tus == b11 || tus == b12 || tus == b13 || tus == b14) && sayi1Gir)
			{
				for (int i = 0; i <= usSayi1_Tmp; i++)
				{
					sayi1 = sayi1 + (arrSayi1[i] * Math.pow(10, usSayi1));
					usSayi1--;
				}

				for (int i = usKesir1_Tmp; i >= 0; i--)
				{
					sayi1 = sayi1 + (arrSayi1Kesir[i] * Math.pow(10, usKesir1));
					usKesir1++;
				}

				if (!sayi1Isaret)
				{
					sayi1 = 0 - sayi1;
				}

				sayi1Gir = false;
			}

			else if (sayi1Gir && !sayi1VirgulGir)
			{
				sayi1Virgul = true;
				sayi1VirgulGir = true;
			}

			/*********************************************************************************************/
			
			if (!islemYap && sayi1Var)
			{ 
				if (tus == b11)
				{
					islem = 11;
					islemYap = true;
				}

				else if (tus == b12)
				{
					islem = 12;
					islemYap = true;
				}

				else if (tus == b13)
				{
					islem = 13;
					islemYap = true;
				}

				else if (tus == b14)
				{
					islem = 14;
					islemYap = true;
				}

				else if (tus == b15)
				{
					l.setText("HATA");
					sifirlama();
				}
			}

			/*********************************************************************************************/

			else if (sayi1Var)
			{
				if (islemYap && !sayi2Var && !operator2) 
				{
					if (tus == b11 || tus == b12 || tus == b15 || tus == b16)
					{
						l.setText("HATA");
						sifirlama();
					}

					if (tus == b13)
					{
						sayi2Isaret = false;
						operator2 = true;
						sayi2Kontrol = false;
					}

					if (tus == b14)
					{
						sayi2Isaret = true;
						operator2 = true;
						sayi2Kontrol = false;
					}
				}

				else if (islemYap && !sayi2Var && operator2)
				{	
					if (tus == b11 || tus == b12 || tus == b13 || tus == b14 || tus == b15 || tus == b16)
					{
						l.setText("HATA");
						sifirlama();
					}
				}

				if (islemYap && sayi2Kontrol) 
				{	
					sayi2Var = true;
					
					if (tus == b11 || tus == b12 || tus == b13 || tus == b14)
					{
						l.setText("HATA");
						sifirlama();
					}

					else if ((tus == b0 || tus == b1 || tus == b2 || tus == b3 || tus == b4 || tus == b5 || 
						tus == b6 || tus == b7 || tus == b8 || tus == b9) && !sayi2Virgul)
					{
						arrSayi2[usSayi2 + 1] = Integer.valueOf(sembol);
						usSayi2++;
						usSayi2_Tmp++;
						sayi2Uzunluk++;
					}
					
					else if ((tus == b0 || tus == b1 || tus == b2 || tus == b3 || tus == b4 || tus == b5 || 
							tus == b6 || tus == b7 || tus == b8 || tus == b9) && sayi2Virgul)
					{
						arrSayi2Kesir[usKesir2_Tmp + 1] = Integer.valueOf(sembol);
						usKesir2--;
						usKesir2_Tmp++;
					}
					
					else if (tus == b15)
					{
						sayi2Gir = false;

						for (int i = 0; i <= usSayi2_Tmp; i++)
						{
							sayi2 = sayi2 + (arrSayi2[i] * Math.pow(10, usSayi2));
							usSayi2--; 
						}

						for (int i = usKesir2_Tmp; i >= 0; i--)
						{
							sayi2 = sayi2 + (arrSayi2Kesir[i] * Math.pow(10, usKesir2));
							usKesir2++;					
						}

						if (!sayi2Isaret)
						{
							sayi2 = 0 - sayi2;
						}

						/*********************************************************************************************/

						if (islem == 11)
						{
							sonuc = bolme(sayi1, sayi2);
						}

						else if (islem == 12)
						{
							sonuc = carpma(sayi1, sayi2);
						}
						
						else if (islem == 13)
						{
							sonuc = cikarma(sayi1, sayi2);
						}

						else if (islem == 14)
						{
							sonuc = toplama(sayi1, sayi2);
						}
						
						sonucString = String.valueOf(sonuc);
						sonucRakam = sonucString.length();

						sonucTamKisim = (int) sonuc;
						sonucKesirliKisim = sonuc - sonucTamKisim;

						if (sonucKesirliKisim == 0)
						{
							sonucRakam = sonucString.indexOf('.');
							metin = String.valueOf(sonucTamKisim);
						}

						else
						{
							metin = sonucString.replace('.',',');
						}

						/*********************************************************************************************/

						if (islem == 11 && sayi2 == 0)
						{
							hata = true;
						}

						if (hata)
						{	
							l.setText("HATA");
							sifirlama();
						}

						else
						{
							l.setText(metin);
							// System.out.println("Sayi1:  " + sayi1 + "  Sayi2:  " + sayi2+ "  Sonuc:  " + sonuc);
						}	
					}

					else if (tus == b16 && !sayi2VirgulGir)
					{
						sayi2Virgul = true;
						sayi2VirgulGir = true;
					}

					if (!sayi2Gir)
					{
						sayi1 = sonuc;

						sayi2Var = false;
						sayi2Gir = true;

						Arrays.fill(arrSayi2, 0);
						Arrays.fill(arrSayi2Kesir, 0);

						sayi2 = 0; sayi2Uzunluk = 0;
						usSayi2 = - 1; usSayi2_Tmp = - 1;
						usKesir2 = 0; usKesir2_Tmp = - 1;
						sayi2Virgul = false;
						sayi2Isaret = true;
						sayi2Kontrol = true;
						sayi2VirgulGir = false;

						islem = - 1;
						islemYap = false;
						operator2 = false;
						hata = false;
					}
				}
			}
		}
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