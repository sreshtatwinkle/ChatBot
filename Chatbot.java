import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);

	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","ola","howdy"},
		{"hi","hello","hey"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well"},
                 //events
                {"what are tomorrow events","what are events for tomorrow","is there any events for tomorrow","tomorrow events"},
                {"there is event called GTVM near science block at 2:00 PM .registrartions are opened"}, 
                //today events
                {"what are today events","what are events for today","is there any events for today","today events"},
                {"yes! there is a new event called meraka organaized by UG students ,another event called national science day event conducted by computer science department. no registrations needed"},
                //creater
                {"who created you","who made you","creater of you"},
                {"sreshta and aravinda from computer science department"},
                //name
                {"whats your name","who are you","what are you"},
                {"thanks for the question .i am evea... your event mate"},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//default
		{"shut up","you're bad","noob","stop talking",
		"(Evea is unavailable, due to LOL)"}
	};

	public static void main(String[] args){
		new ChatBot();
	}

	public ChatBot(){
		super("Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);

		setVisible(true);
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
//system.out.println("Hhhhhh");
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->Evea\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}

			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Evea\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e){}

	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}

	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}