package lessons;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.*;

public class BasicCalculator extends JFrame{
	
	JButton b1, b2, b3,b4,b5,b6,b7,b8,b9,b0,
	bclear,bsquare,storeA,storeB,storeSwitch,deleteKey,
	bexponent,broot,bequal,bplus,bminus,bmultiply,bdivide,bdecimal;
	JTextField result, selectedOp;
	JTextArea storage;
	double num1, num2, A, B;
	String optype="";
	int decimalcounter;
	String num1str, num2str, zeroRemover, strtxt;
	lforbutton listener = new lforbutton();
	lforkeypress keylistener = new lforkeypress();
	JPanel thePanel = new JPanel();
	
	
	public static void main (String[] args){
		
		new BasicCalculator();
		
	}

	public BasicCalculator()
	{
		
		this.setSize(550,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Calculator");
		this.setLocationRelativeTo(null);
		
		decimalcounter=0;

		
		A=0;
		B=0;
		zeroRemover="\\.0{5,}[1-9]$|\\.0+$";
		strtxt="Str Mode";
		
		
		b0=createButton(b0, "0");
		b1=createButton(b1, "1");
		b2=createButton(b2, "2");
		b3=createButton(b3, "3");
		b4=createButton(b4, "4");
		b5=createButton(b5, "5");
		b6=createButton(b6, "6");
		b7=createButton(b7, "7");
		b8=createButton(b8, "8");
		b9=createButton(b9, "9");
		bclear=createButton(bclear, "C");
		bplus=createButton(bplus, "+");
		bminus=createButton(bminus, "-");
		bmultiply=createButton(bmultiply, "*");
		bdivide=createButton(bdivide, "/");
		bequal=createButton(bequal, "=");
		bsquare=createButton(bsquare, "x²");
		bexponent=createButton(bexponent, "exp");
		broot=createButton(broot, "sqrt");
		bdecimal=createButton(bdecimal, ".");
		storeA=createButton(storeA, "A");
		storeB=createButton(storeB, "B");
		storeSwitch=createButton(storeSwitch, strtxt);
		deleteKey=createButton(deleteKey, "Del");
		
		
		Dimension bmin=b8.getMinimumSize();
		storeSwitch.setMinimumSize(bmin);
		broot.setMinimumSize(bmin);
		bsquare.setMinimumSize(bmin);
		bexponent.setMinimumSize(bmin);
		deleteKey.setMinimumSize(bmin);
		
		
		
		num1str=replacer(Double.toString(num1), ".0");
		num2str=replacer(Double.toString(num2), ".0");
		
		
		result=new JTextField(num2str, 20);
		selectedOp= new JTextField("",20);
		storage=new JTextArea(3,15);
		selectedOp.setEditable(false);
		storage.setEditable(false);
		Dimension d=storage.getSize();
		storage.setMinimumSize(d);
		storage.setText("Stored numbers: \nA: 0\nB: 0");
		result.setEditable(false);
		result.setBackground(Color.WHITE);
		selectedOp.setBackground(Color.WHITE);
	
		
		Font font = new Font("Helvetica", Font.PLAIN, 18);
        result.setFont(font);
        selectedOp.setFont(font);
        storage.setFont(font);
        
        
        
    	thePanel.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.weightx=900;
		gridConstraints.weighty=1;
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.insets = new Insets(5,5,5,5);
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 3;
		gridConstraints.gridheight = 1;
		
		thePanel.add(result, gridConstraints);
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 4;
		thePanel.add(selectedOp, gridConstraints);
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		thePanel.add(b1, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(b2, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(b3, gridConstraints);
		gridConstraints.gridx = 4;
		thePanel.add(bplus, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 3;
		thePanel.add(b4, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(b5, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(b6, gridConstraints);
		gridConstraints.gridx = 4;
		thePanel.add(bminus, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		thePanel.add(b7, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(b8, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(b9, gridConstraints);
		gridConstraints.gridx = 4;
		thePanel.add(bmultiply, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 5;
		thePanel.add(b0, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(bclear, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(bequal, gridConstraints);
		gridConstraints.gridx = 4;
		thePanel.add(bdivide, gridConstraints);
		gridConstraints.gridy = 2;
		gridConstraints.gridx = 5;
		thePanel.add(bsquare, gridConstraints);
		gridConstraints.gridy = 3;
		thePanel.add(broot, gridConstraints);
		gridConstraints.gridy = 4;
		thePanel.add(bexponent, gridConstraints);
		gridConstraints.gridy = 5;
		thePanel.add(bdecimal, gridConstraints);
		gridConstraints.gridy = 6;
		gridConstraints.gridx = 1;
		thePanel.add(storeSwitch, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(storeA, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(storeB, gridConstraints);
		gridConstraints.gridx = 4;
		gridConstraints.gridwidth = 2;
		thePanel.add(storage, gridConstraints);
		gridConstraints.gridx = 5;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridy = 1;
		thePanel.add(deleteKey, gridConstraints);
		
		
		
		this.add(thePanel);
		this.setVisible(true);
		thePanel.setFocusable(true);
		thePanel.requestFocus();
		thePanel.addKeyListener(keylistener);
		
		
	}
	public JButton createButton(JButton button, String buttonName)
	{
		
		button = new JButton(buttonName);
		//Font font1 = new Font("Helvetica", Font.PLAIN, 10);
		//button.setFont(font1);
		button.addActionListener(listener);
		
		return button;
		
	}
	private String replacer(String strtoreplace, String checker)
	{
		Pattern checkRegex = Pattern.compile(checker);
		Matcher regexMatcher = checkRegex.matcher(strtoreplace);
		strtoreplace=regexMatcher.replaceAll("");
		return strtoreplace;
	}
	double equal(String operation)
	{
	
		if(operation=="+")
		{
			num2=num1+num2;
			num1=0;
		}
		else if(operation=="-")
		{
			num2=num2-num1;
			num1=0;
		}
		else if(operation=="*")
		{
			num2=num2*num1;
			num1=0;
		}	
		else if(operation=="/")
		{
			num2=num2/num1;
			num1=0;
		}
		
		else if(operation=="exp")
		{
			num2=Math.pow(num2, num1);
			num1=0;
		}
		
		else
		{
			num1=0;
		}
		decimalcounter=0;
		num2str=replacer(Double.toString(num2), zeroRemover);
		result.setText(num2str);
		return num2;
	}
	
	public void bnumber(double i)
	{
		if(decimalcounter==0)
		{
		num1=10*num1+i;
		num1str=replacer(Double.toString(num1), zeroRemover);
		result.setText(num1str);
		}
		else
		{
			num1=num1+(i/(Math.pow(10, decimalcounter)));
			num1=round(num1, decimalcounter);
			decimalcounter++;
			
		}
		
		
	}
	


	private double round(double numToRound, int precision) {
		
		BigDecimal bd = new BigDecimal(numToRound);
	    bd = bd.setScale(precision, RoundingMode.HALF_UP);
	    result.setText(bd.toString());
	    return bd.doubleValue();
		
	}

	void performop()
	{
		if(num1!=0)
		{
			decimalcounter=0;
			if(optype=="")
			{
				num2=num1;
				num1=0;
			}
			else
			{
				equal(optype);
			}
		}
	}
	
	public void bOperation(String i)
	{
		performop();
		optype=i;
		selectedOp.setText(optype);
	}
	

	public class lforbutton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource()==b0){
				bnumber(0);
			}
			else if(e.getSource()==b1){
				bnumber(1);
			}
			else if(e.getSource()==b2){
				bnumber(2);
			}
			else if(e.getSource()==b3){
				bnumber(3);
			}
			else if(e.getSource()==b4){
				bnumber(4);
			}
			else if(e.getSource()==b5){
				bnumber(5);
			}
			else if(e.getSource()==b6){
				bnumber(6);
			}
			else if(e.getSource()==b7){
				bnumber(7);
			}
			else if(e.getSource()==b8){
				bnumber(8);
			}
			else if(e.getSource()==b9){
				bnumber(9);
			}
			else if(e.getSource()==bclear){
				num1=0;
				num2=0;
				optype="";
				num1str=replacer(Double.toString(num1), ".0");
				result.setText(num1str);
				decimalcounter=0;
				selectedOp.setText("");
				

				
			}
			else if(e.getSource()==bplus){
				bOperation("+");
			}
			else if(e.getSource()==bminus){
				bOperation("-");
			}
			else if(e.getSource()==bmultiply){
				bOperation("*");
			}
			else if(e.getSource()==bdivide){
				bOperation("/");
			}
			else if(e.getSource()==bequal){
				equal(optype);
				optype="";
				selectedOp.setText("");
				
			}
			else if(e.getSource()==bsquare){
				double tempnum=Double.parseDouble(result.getText());
				num2=Math.pow(tempnum, 2);
				num1=0;
				optype="";
				decimalcounter=0;
				num2str=replacer(Double.toString(num2), zeroRemover);
				result.setText(num2str);
			}
			else if(e.getSource()==bexponent){
				bOperation("exp");
			}
			else if(e.getSource()==broot){
				double tempnum=Double.parseDouble(result.getText());
				num2=Math.sqrt(tempnum);
				num1=0;
				optype="";
				decimalcounter=0;
				num2str=replacer(Double.toString(num2), zeroRemover);
				result.setText(num2str);			
			}
			else if (e.getSource()==bdecimal)
			{
				if(decimalcounter==0)
				{
					if(num1==0)
					{
						result.setText("0.");
						decimalcounter++;
					}
					else
					{
					result.setText(result.getText()+".");
					decimalcounter++;
					}
				}
			}
			else if (e.getSource()==storeSwitch)
			{
				if(strtxt=="Str Mode")
				{
					strtxt="Rcl Mode";
					storeSwitch.setText(strtxt);
				}
				else
				{
					strtxt="Str Mode";
					storeSwitch.setText(strtxt);
				}
			}
			else if(e.getSource()==storeA)
			{
				if(strtxt=="Str Mode")
				{
					A=Double.parseDouble(result.getText());
					storage.setText("Stored numbers: \nA: "+result.getText()+ "\nB: "
							+ ""+replacer(Double.toString(B), zeroRemover));
				}
				else
				{
					num1=A;
					num1str=replacer(Double.toString(num1), zeroRemover);
					result.setText(num1str);
				}
				
			}
			else if(e.getSource()==storeB)
			{
				if(strtxt=="Str Mode")
				{
					B=Double.parseDouble(result.getText());
					storage.setText("Stored numbers: \nA: "+replacer(Double.toString(A), 
							zeroRemover)+ "\nB: "+result.getText());
				}
				else
				{
					num1=B;
					num1str=replacer(Double.toString(num1), zeroRemover);
					result.setText(num1str);
				}
				
			}
			else if(e.getSource()==deleteKey)
			{
				
				if(result.getText().equals(replacer(Double.toString(num1), zeroRemover)))
				{
					
					if(decimalcounter==0)
					{
						num1=(num1-(((int)num1)%10))/10;
						num1=round(num1,1);
						num1str=replacer(Double.toString(num1), zeroRemover);
						result.setText(num1str);
						
					}
					else
					{
						result.setText(replacer(result.getText(), ".$"));
						num1=Double.parseDouble(result.getText());
						decimalcounter--;
					}
				}
				else if(result.getText().equals(replacer(Double.toString(num1), zeroRemover)+"."))
				{
					decimalcounter--;
					result.setText(replacer(result.getText(),"\\."));
				}
				
				
			}
			thePanel.requestFocus();
		}
		
		
	}
	
	public class lforkeypress implements KeyListener{
		
		
		public void keyPressed(KeyEvent e) 
		{
		
			
		}

		
		public void keyReleased(KeyEvent e) 
		{
			
			
		}

		
		public void keyTyped(KeyEvent e) 
		{
			
			int keyCode = e.getKeyChar();
			System.out.println(keyCode);
			//0 to 9
			if(e.getKeyChar()==48)
			{
				bnumber(0);
			}
			else if(e.getKeyChar()==49)
			{
				bnumber(1);
			}
			else if(e.getKeyChar()==50)
			{
				bnumber(2);
			}
			else if(e.getKeyChar()==51)
			{
				bnumber(3);
			}
			else if(e.getKeyChar()==52)
			{
				bnumber(4);
			}
			else if(e.getKeyChar()==53)
			{
				bnumber(5);
			}
			else if(e.getKeyChar()==54)
			{
				bnumber(6);
			}
			else if(e.getKeyChar()==55)
			{
				bnumber(7);
			}
			else if(e.getKeyChar()==56)
			{
				bnumber(8);
			}
			else if(e.getKeyChar()==57)
			{
				bnumber(9);
			}
			
			//backspace
			else if(e.getKeyChar()==8)
			{
				
				if(result.getText().equals(replacer(Double.toString(num1), zeroRemover)))
				{
					
					if(decimalcounter==0)
					{
						num1=(num1-(((int)num1)%10))/10;
						num1=round(num1,1);
						num1str=replacer(Double.toString(num1), zeroRemover);
						result.setText(num1str);
						
					}
					else
					{
						result.setText(replacer(result.getText(), ".$"));
						num1=Double.parseDouble(result.getText());
						decimalcounter--;
					}
				}
				else if(result.getText().equals(replacer(Double.toString(num1), zeroRemover)+"."))
				{
					decimalcounter--;
					result.setText(replacer(result.getText(),"\\."));
				}
			}
			//equals
			else if(e.getKeyChar()==61||e.getKeyChar()==10)
			{
				equal(optype);
				optype="";
				selectedOp.setText("");
			}
			
			else if(e.getKeyChar()==45)
			{
				bOperation("-");
			}
			//decimal
			else if(e.getKeyChar()==46)
			{
				if(decimalcounter==0)
				{
					if(num1==0)
					{
						result.setText("0.");
						decimalcounter++;
					}
					else
					{
					result.setText(result.getText()+".");
					decimalcounter++;
					}
				}
			}
			else if(e.getKeyChar()==47)
			{
				bOperation("/");
			}
			else if(e.getKeyChar()==43)
			{
				bOperation("+");
			}
			else if(e.getKeyChar()==42)
			{
				bOperation("*");
			}
			else if(e.getKeyChar()==94)
			{
				bOperation("exp");
			}
			
			
			//System.out.println(e.getKeyChar());
			
			
		}
	}
	
}
