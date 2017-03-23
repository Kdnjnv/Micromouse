import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Grid implements ActionListener{
	
	protected int a;
	protected int b;
	public int k=0,x,w,y,z,row,xs=0,ys=0,col,ks=1,kd=1,aa,destval=0,xd,yd,makepath=0;
	JFrame frame = new JFrame();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JButton source=new JButton("Source");// Starting point of rat
	    JButton dest=new JButton("Destination"); 
	    JButton obs=new JButton("Obstacles"); // Rats cant go on that block
	    JButton path=new JButton("BuildPath"); // The path covered by rat having smallest path
	    JButton run=new JButton("Run"); // It will show the path covered with yellow colour
	    JButton reset=new JButton("Reset"); 
	    JButton ran = new JButton("Random"); // It will allow you to push random number of obstacles at once
	    JButton[][] button = new JButton[100][100];
	    int[][] Arr = new int[100][100];
	    Color color = UIManager.getColor ( "Panel.background" );
	Grid(int a, int b){
		this.a=a;this.b=b;
		row=a;col=b;
		frame.setTitle("MicroMouse");
		frame.setVisible(true);
		frame.setBounds(500, 200, 125, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1.setLayout(new GridLayout(a,b));
		p2.setBackground(Color.GRAY);
		p1.setBackground(Color.BLACK);
		frame.add(p1);
		//bottom
	    
	    source.addActionListener(this);
	    dest.addActionListener(this);
	    obs.addActionListener(this);
	    path.addActionListener(this);
	    run.addActionListener(this);
	    reset.addActionListener(this);
	    ran.addActionListener(this);
	    
		frame.add(p2, BorderLayout.SOUTH);
        p2.add(source);p2.add(dest);p2.add(obs);p2.add(path);p2.add(run);p2.add(reset);p2.add(ran);
	    
		//JButton[][] button = new JButton[a][b];
		for(int i=0;i<a;++i){
			for(int j=0;j<b;++j){
				button[i][j]=new JButton();
				button[i][j].setText("-");
				//button[i][j].setActionCommand("-");
				button[i][j].addActionListener(this);
				p1.add(button[i][j]);
			}
		}
        frame.pack();
		frame.add(p1);
		frame.setVisible(true);
	}
	public static void main(int a, int b) {
		// TODO Auto-generated method stub
		new Grid(a,b);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
	         String s=e.getActionCommand();
	         if(s.equalsIgnoreCase("Source")){
	             k=1;
	         }
	         if(k==1  && s.equalsIgnoreCase("-")){
	             for(int i=0;i<a;i++){
	                 for(int j=0;j<b;j++){
	                	 if(button[i][j].getText()=="0"){
	                		 button[i][j].setText("-");
	                		 Color color = UIManager.getColor ( "Panel.background" );
	                		 button[i][j].setBackground(color);
	                	 }
	                     if(button[i][j].isFocusOwner()){
	                         button[i][j].setText("0");
	                         button[i][j].setBackground(Color.GREEN); //k=0
	                         xs=i; ys=j; 
	                         Arr[i][j]=0;
	                         break;
	                     }
	                 }//if(k==0) break;
	                 
	             }
	         }
	         if(s.equalsIgnoreCase("Destination")){
	        	 k=2;
	         }
	         if(k==2  && s.equalsIgnoreCase("-")){
	        	 for(int i=0;i<a;++i){
	        		 for(int j=0;j<b;++j){
	        			 if(button[i][j].getText()=="d"){
	                		 button[i][j].setText("-");
	                		 button[i][j].setBackground(color);
	                	 }
	        			 if(button[i][j].isFocusOwner()){
	        				 button[i][j].setText("d");
	        				 button[i][j].setBackground(Color.BLUE);//k=0;
	        				 xd=i;yd=j; break;
	        			 }
	        		 }
	        		 //if(k==0) break;
	        	 }
	         }
	         if(s.equalsIgnoreCase("Obstacles")){
	        	 k=3;
	         }
	         if(k==3 && s.equalsIgnoreCase("-")){
	        	 for(int i=0;i<a;++i){//System.out.println("obs3");
	        		 for(int j=0;j<b;++j){//System.out.println("obs2");
	        			 if(button[i][j].isFocusOwner()){
	        				 button[i][j].setText("x");
	        				 button[i][j].setBackground(Color.BLACK);
	        				 w=i;x=j; 
	        				 Arr[i][j]=-1;
	        				 break;
	        			 }
	        		 }
	        	 }
	         }else if(k==3 && s.equalsIgnoreCase("x")){
	        	 for(int i=0;i<a;++i){
	        		 for(int j=0;j<b;++j){
	        			 if(button[i][j].isFocusOwner()){
	        				 button[i][j].setText("-");
	        				 button[i][j].setBackground(color);
	        				 w=i;x=j;
	        				 Arr[i][j]=0;
	        				 break;
	        			 }
	        		 }
	        	 }
	         }
	         // for path
	         
	         if(s.equalsIgnoreCase("BuildPath")){
	        	 k=4;
	        	 int i,j;
	        	 Queue<Integer> q1=new LinkedList<Integer>();
	        	 Queue<Integer> q2=new LinkedList<Integer>();
	        	 q1.add(xs); q2.add(ys);
	             while(!q1.isEmpty() && !q2.isEmpty()){
	                     i=q1.poll();
	                     j=q2.poll();
	                     if(((i+1)<row)){
	                    	 if(Arr[i+1][j]==0 && !((i+1)==xs && j==ys)){
	                             Arr[i+1][j]=Arr[i][j]+1; //System.out.print(" hye");
	                             q1.add(i+1);q2.add(j);
	                         }
	                     }
	                         
	                     if((i-1)>=0){
	                    	  if(Arr[i-1][j]==0 && !((i-1)==xs && j==ys)){//System.out.print(" hye11");
	                             Arr[i-1][j]=Arr[i][j]+1; //System.out.print(Arr[i-1][j]+" hye2");
	                             q1.add(i-1);q2.add(j);
	                         }
	                     }
	                     if((j+1)<col){
	                    	 if(Arr[i][j+1]==0 && !((i)==xs && (j+1)==ys)){/
	                             Arr[i][j+1]=Arr[i][j]+1; 
	                             q1.add(i);q2.add(j+1);
	                         }
	                     }
	                         
	                     if((j-1)>=0){
	                    	 if( Arr[i][j-1]==0 && !((i)==xs && (j-1)==ys)){//System.out.print(" hy3e");
	                             Arr[i][j-1]=Arr[i][j]+1;// System.out.print(Arr[i][j-1]+"hye4 ");
	                             q1.add(i);q2.add(j-1);
	                         }
	                     }  
	             }
	                 /*for(int m=0;m<row;++m){
	                	 for(int n=0;n<col;++n){
	                		 System.out.print(Arr[m][n]+" ");
	                	 }System.out.println("");
	                 }*/
	                 for(int i1=0;i1<row;++i1){
	                	 for(int j1=0;j1<col;++j1){
	                		 if(button[i1][j1].getText()!="x" && button[i1][j1].getText()!="s"){
	                			 button[i1][j1].setText(Integer.toString(Arr[i1][j1]));
	                		 }
	                	 }
	                 }
	                 if(Arr[xd][yd]==0){
	                	 makepath=1;
	                 }
			}
	         
	         if(s.equalsIgnoreCase("run")){
	        	 k=5;
	        	 int i=xd; int j=yd;
	        	 if(Arr[xd][yd]==0){
	        		 JOptionPane.showMessageDialog(null, "oops! No path found");
	        	 }else{
	        	 int count=Integer.parseInt(button[i][j].getText());
	        	 while(count!=0){count--;
	        	 int ss;
	        		 if(((i+1)<row  && button[i+1][j].getText()!="x")){
	        			 ss=Integer.parseInt(button[i+1][j].getText());
                    	 if(count==ss ){
                    		 button[i+1][j].setBackground(Color.YELLOW);
                    		 i++;
                    		 continue;
                    	 }
                    	 
                     }  
	        		 if((i-1)>=0&& button[i-1][j].getText()!="x"){
	        			 ss=Integer.parseInt(button[i-1][j].getText());
                    	 if(count==ss ){
                    		 button[i-1][j].setBackground(Color.YELLOW);
                    		 i--;
                    		 continue;
                    	 }
                     }
                     if((j+1)<col&& button[i][j+1].getText()!="x"){
                    	 ss=Integer.parseInt(button[i][j+1].getText());
                    	 if(count==ss  ){
                    		 button[i][j+1].setBackground(Color.YELLOW);
                    		 j++;
                    		 continue;
                    	 }
                     }
                         
                     if((j-1)>=0&& button[i][j-1].getText()!="x"){
                    	 ss=Integer.parseInt(button[i][j-1].getText());
                    	 if(count==ss ){
                    		 button[i][j-1].setBackground(Color.YELLOW);
                    		 j--;
                    		 continue;
                    	 }
                     }
                     
	        	 }button[xs][ys].setBackground(Color.GREEN);
	        	 }
	         }
	         
	         if(s.equalsIgnoreCase("Reset")){
	        	 for(int i=0;i<a;++i){
	        		 for(int j=0;j<b;++j){
	        			 button[i][j].setText("-");
	        			 button[i][j].setBackground(color);
	        			 Arr[i][j]=0;
	        		 }
	        	 }
	        	 k=0;
	         }  
	         if(s.equalsIgnoreCase("Random")){
	        	 k=7;
	         }
	        	 if(k==7){
	        		 int loop =((int)(Math.random()*1000)%((row*col)/5));
	        		 //System.out.println(loop);
	        		 while(loop>0){
	        			 loop--;
	        			 int  ll =((int)(Math.random()*1000))%row;
	        			 int rr = ((int)(Math.random()*1000))%col;
	        			 if(!((ll==xs && rr==ys)||(ll==xd && rr==yd))){
	        				 button[ll][rr].setText("x");
		        			 button[ll][rr].setBackground(Color.BLACK);
		        			 Arr[ll][rr]=-1;
	        			 }
	        		 }
		         }
	        }
}
