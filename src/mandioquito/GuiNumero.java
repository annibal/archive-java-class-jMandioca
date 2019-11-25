package mandioquito;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiNumero extends JPanel {
	
	Canvas getImage(String path) {
		Canvas c = new Canvas() {
			public void paint(Graphics g) {
		        Toolkit t=Toolkit.getDefaultToolkit();  
		        Image i=t.getImage(path);  
		        g.drawImage(i, 0, 0, 30, 30, this);  
			}
		};
		c.setSize(30,30);
		return c;
	}

	Canvas lessButton = getImage("Images/flat-minus.png");
	Canvas moreButton = getImage("Images/flat-plus.png");
	Canvas lessButtonDisabled = getImage("Images/flat-minus-gray.png");
	Canvas moreButtonDisabled = getImage("Images/flat-plus-gray.png");
	JLabel numberLabel = new JLabel("0");
	int number = 0;
	int min = 0;
	int max = 100;
	
	
	public GuiNumero(int min, int max) {
		this.min = min;
		this.max = max;
		
		this.setLayout(new GridLayout(0,3, 10,10));
		
		moreButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				setValue(number + 1);
			}
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
		});
		
		lessButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				setValue(number - 1);
			}
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
		});

		renderLayout();
	}
	
	void renderLayout() {
		this.removeAll();
		
		if (this.number == this.min) {
			this.add(lessButtonDisabled);
		} else {
			this.add(lessButton);
		}
		
		this.add(numberLabel);

		if (this.number == this.max) {
			this.add(moreButtonDisabled);
		} else {
			this.add(moreButton);
		}
		
		this.revalidate();
	}

	public int getValue() { return this.number; }
	public void setValue(int number) {
		this.number = number;
		if (this.number > this.max) { this.number = this.max; }
		if (this.number < this.min) { this.number = this.min; }
		this.numberLabel.setText(""+number);
		this.renderLayout();
	}
}
