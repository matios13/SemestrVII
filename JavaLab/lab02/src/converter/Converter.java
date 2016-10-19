package converter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Converter extends JPanel {

	public static final long serialVersionUID = 1L;
	
	private String title;
	private JLabel titleLabel;
	private Shape chosenShape;
	private String shape;
	private JLabel shapeLabel;
	private JLabel wynik;
	private JButton button;
	private Color bgcolor;
	
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JTextField propertyA = new JTextField();
	private JTextField propertyB = new JTextField();

	public Converter() {
		setLayout(new BorderLayout());
		prepareTopPanel();
		prepareBottomPanel();
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
	}

	private void prepareTopPanel() {
		topPanel.setLayout(new FlowLayout());
		titleLabel=new JLabel("Title");
		topPanel.add(titleLabel);
	}

	private void prepareBottomPanel() {
		bottomPanel.setLayout(new FlowLayout());
		shapeLabel=new JLabel("stozek");
		wynik=new JLabel("0");
		button = new JButton("calculate");
		button.addActionListener(e -> calculate());
		propertyA.setMinimumSize(new Dimension(10,30));
		bottomPanel.add(shapeLabel);
		bottomPanel.add(propertyA);
		bottomPanel.add(propertyB);
		bottomPanel.add(button);
		bottomPanel.add(wynik);

	}

	public static enum Shape {
		szescian,stozek,walec
	}


	public Color getBgcolor() {
		return bgcolor;
	}

	public Converter setBgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
		topPanel.setBackground(bgcolor);
		bottomPanel.setBackground(bgcolor);
		return this;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.titleLabel.setText(title);
		this.title=title;
	}

	public Shape getShape() {
		return chosenShape;
	}

	public void setShape(Shape chosenShape) {
		shapeLabel.setText(chosenShape.toString());
		this.chosenShape = chosenShape;	
	}

	private void calculate(){
		double wynik = 0;
		int a = new Integer(propertyA.getText());
		int b = new Integer(propertyB.getText());
		switch(chosenShape){
			case szescian:			
				wynik = a*a*a;
				break;
			case stozek:
				wynik = Math.PI*a*a*b/3;
				break;
			case walec:
				wynik = Math.PI*a*a*b;
		}
		this.wynik.setText(""+wynik);
	}



}
