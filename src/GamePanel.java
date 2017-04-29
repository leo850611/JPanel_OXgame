import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GamePanel extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 525;
    public static final int HEIGHT = 370;

    private JLabel leftInfo;
    private JLabel rightInfo;
    private JLabel leftPic;
    private JLabel rightPic;
    private JLabel leftLabel;
    private JLabel rightLabel;
    
    private int[] buttonNum = {-99,0,0,0,0,0,0,0,0,0,99};
    private String[] buttonName = {"","a1","a2","a3","b1","b2","b3","c1","c2","c3"};
    private JButton[] mainButton = new JButton[10];
    
    private ImageIcon computer = new ImageIcon("pic\\00.jpg");  
    private ImageIcon pic0 = new ImageIcon("pic\\0.jpg");    
    private ImageIcon[] PlayerPic = new ImageIcon[2];
    private int toComputer = 0;
    private int i,j;
    private int END = 0;
    private int count = 0;
    private int[] player1_2 = {1,-1};  

    
    public static void main(String[] args)
    {  	
        PlayersPanel players = new PlayersPanel();
    	players.setVisible(true);    	
    }

    public GamePanel( )
    {
        super("OOXX小遊戲");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout( ));

        JPanel biggerPanel = new JPanel( );
        biggerPanel.setLayout(new GridLayout(3, 3));
        add(biggerPanel, BorderLayout.CENTER);    
        
        JPanel allButton = new JPanel( );
        allButton.setLayout(new GridLayout(1, 3));
        add(allButton, BorderLayout.SOUTH);
        
        
        
        //下面按鈕
        JButton reset = new JButton("Reset");
		reset.setBackground(Color.CYAN);
		reset.addActionListener(this);
		allButton.add(reset);
		
        JButton exit = new JButton("Exit");
        exit.setBackground(Color.PINK);
        exit.addActionListener(this);
		allButton.add(exit); 
        //--------------------------------------- 
		
		
        
        //左右玩家顯示
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        add(leftPanel, BorderLayout.WEST);
        
        leftLabel = new JLabel("  玩家一");
        leftPanel.add(leftLabel);
              
        leftPic = new JLabel();
        leftPic.setIcon(PlayerPic[0]);
        leftPanel.add(leftPic);
        
        leftInfo = new JLabel("  輪到玩家1");
        leftPanel.add(leftInfo);
        
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));
        add(rightPanel, BorderLayout.EAST);
        
        rightLabel = new JLabel("  玩家二");
        rightPanel.add(rightLabel);
        
        rightPic = new JLabel();
        rightPic.setIcon(PlayerPic[1]);
        rightPanel.add(rightPic);
        
        rightInfo = new JLabel("");
        rightPanel.add(rightInfo);  
        //--------------------------------------- 

        
        //九宮格        
        for(i = 1;i<=9;i++){
        	 mainButton[i] = new JButton(pic0);
             mainButton[i].setActionCommand(buttonName[i]);
             mainButton[i].addActionListener(this);
             biggerPanel.add(mainButton[i]);
        }

    }

    
    public void setPic(ImageIcon p1,ImageIcon p2,int toComputer){
    	this.toComputer = toComputer;
    	PlayerPic[0] = p1;
    	PlayerPic[1] = p2;
    	leftPic.setIcon(PlayerPic[0]);
    	rightPic.setIcon(PlayerPic[1]);
    	if(toComputer == 1){
    		mainButton[5].setIcon(computer);
    		leftInfo.setText(" ");
    		leftLabel.setText("  毛茸茸的兔子");  
    		rightLabel.setText("  玩家一");
    		buttonNum[5] = 1;
    		count++;
    	}
    }
    
    
    public void actionPerformed(ActionEvent e)
    {	
    	int temp1 = 0,temp2 = 0,temp3 = 0,flag = 0;
        String buttonString = e.getActionCommand( );
    	
        if(toComputer == 0 || count%2==1){
	        for(i = 1;i<=9;i++){
	        	if(buttonString.equals(buttonName[i])){
	        		if (mainButton[i].getIcon().equals(pic0)){
	        			mainButton[i].setIcon(PlayerPic[count%2]);
	        			buttonNum[i] = player1_2[count%2];
	        			count++;
	        		}
	        	}	
	        }
    	}

        //AI
    	if(toComputer == 1  && count%2==0){
    		//絕對進攻
    		for(j = 1;j<=9;j++){
    			if(buttonNum[j] == 0){		
    				buttonNum[j] = 1;
    				for(i = 1;i<9;i = i+3){
    					if(buttonNum[i]+buttonNum[i+1]+buttonNum[i+2] == 3){
    							mainButton[j].setIcon(computer);
    							buttonNum[j] = 1;
    							flag = 1;
    				           	i = 99;
    					}
    				}//橫
    				if(flag != 1){
    					for(i = 1;i<=3;i++){
    					   	if(buttonNum[i]+buttonNum[i+3]+buttonNum[i+6] == 3){
    							mainButton[j].setIcon(computer);
    							buttonNum[j] = 1;
    							flag = 1;
    							i = 99;
    						}
    					}//直
    				}
    				if(flag != 1){
    					for(i = 0;i<=6;i = i+6){
    						if(buttonNum[1+i]+buttonNum[5]+buttonNum[9-i] == 3){
    							mainButton[j].setIcon(computer);
    							buttonNum[j] = 1;
    							flag = 1;
    							i = 99;
    						}
    					}//斜
    				}
    				
    				if(flag != 1){
    					buttonNum[j] = 0;
    				}
    				else{
    					j = 99;
    				}
    			}
    		}
    	    
    		if(flag != 1){
    			//防守
    	    	for(j = 1;j<=9;j++){
    		    	if(buttonNum[j] == 0){		
    			    	buttonNum[j] = -1;
    			    	for(i = 1;i<9;i = i+3){
    			           	if(buttonNum[i]+buttonNum[i+1]+buttonNum[i+2] == -3){
    			           		mainButton[j].setIcon(computer);
    			           		buttonNum[j] = 1;
    			           		flag = 1;
    			           		i = 99;
    			            	}
    			            }//橫       
    			            for(i = 1;i<=3;i++){
    			            	if(buttonNum[i]+buttonNum[i+3]+buttonNum[i+6] == -3){
    			            		mainButton[j].setIcon(computer);
    			            		buttonNum[j] = 1;
    			            		flag = 1;
    			            		i = 99;	
    			            	}
    			            }//直
    			            for(i = 0;i<=6;i = i+6){
    			            	if(buttonNum[1+i]+buttonNum[5]+buttonNum[9-i] == -3){
    			            		mainButton[j].setIcon(computer);
    			            		buttonNum[j] = 1;
    			            		flag = 1;
    			            		i = 99;
    			            	}
    			            }//斜
    			            
    				        if(flag != 1){
    				        	buttonNum[j] = 0;
    				        }
    				        else{
    					    	j = 99;
    					    }
    		    		}
    	    		}
    	    		
    	    		//進攻1
    		    	if(flag != 1){  		
    			    	for(j = 2;j<=9;j = j+2){
    				    	if(buttonNum[j] == 0){		
    					   		buttonNum[j] = 1;
    					   		for(i = 1;i<9;i = i+3){
    					           	if(buttonNum[i]+buttonNum[i+1]+buttonNum[i+2] >=2){
    					           		mainButton[j].setIcon(computer);
    					           		buttonNum[j] = 1;
    					           		flag = 1;
    					           		i = 99;
    						         }
    						    }//橫
    					   		if(flag != 1){
    					   			for(i = 1;i<=3;i++){
    							    	if(buttonNum[i]+buttonNum[i+3]+buttonNum[i+6] >=2){
    							         	mainButton[j].setIcon(computer);
    							           	buttonNum[j] = 1;
    							           	flag = 1;
    							           	i = 99;
    							    	}
    							    }//直
    					   		}
    						    if(flag != 1){
    						    	for(i = 0;i<=6;i = i+6){
    							       if(buttonNum[1+i]+buttonNum[5]+buttonNum[9-i] >=2){
    							        	mainButton[j].setIcon(computer);
    							           	buttonNum[j] = 1;
    							           	flag = 1;
    							           	i = 99;
    							        }
    							    }//斜
    						    }
    						    if(flag != 1){
    					        	buttonNum[j] = 0;
    					        }
    						    else{
    						    	j = 99;
    						    }
    				    	}
    			    	}
    			    	
    			    	//進攻2
    			    	if(flag != 1){
    			    		//進攻
    				    	for(j = 1;j<=9;j = j+2){
    					    	if(buttonNum[j] == 0){		
    						   		buttonNum[j] = 1;
    						   		for(i = 1;i<9;i = i+3){
    						           	if(buttonNum[i]+buttonNum[i+1]+buttonNum[i+2] >=2){
    						           		mainButton[j].setIcon(computer);
    						           		buttonNum[j] = 1;
    						           		flag = 1;
    						           		i = 99;
    							         }
    							    }//橫
    						   		if(flag != 1){
    						   			for(i = 1;i<=3;i++){
    								    	if(buttonNum[i]+buttonNum[i+3]+buttonNum[i+6] >=2){
    								        	mainButton[j].setIcon(computer);
    								        	buttonNum[j] = 1;
    								        	flag = 1;
    											i = 99;
    								    	}
    								    }//直
    						   		}
    							    if(flag != 1){
    							    	for(i = 0;i<=6;i = i+6){
    								       if(buttonNum[1+i]+buttonNum[5]+buttonNum[9-i] >=2){
    											mainButton[j].setIcon(computer);
    											buttonNum[j] = 1;
    											flag = 1;
    											i = 99;
    								        }
    								    }//斜
    							    }
    							    if(flag != 1){
    						        	buttonNum[j] = 0;
    						        }
    							    else{
    							    	j = 99;
    							    }
    					    	}
    				    	}
    			    	}
    	    		}		
        		}
        		
    		
    			//隨機填空
    			if(flag != 1){
    				for(i = 1;i<=9;i++){
    		        	if (mainButton[i].getIcon().equals(pic0)){
    		        		mainButton[i].setIcon(computer);
    		        		buttonNum[i] = 1;
    		        		i = 99;
    		        	}
    		        }
    			}
    		
    		
    		count++;
    		}
    		//AI--------------------------------------------------------------
    	



		//連成一線?
        for(i = 1;i<9;i = i+3){
        	if(buttonNum[i]+buttonNum[i+1]+buttonNum[i+2] == 3 || buttonNum[i]+buttonNum[i+1]+buttonNum[i+2] == -3){
        		temp1 = i;
        		temp2 = i+1;
        		temp3 = i+2;
        		END = 1;
        	}
        }//橫       
        for(i = 1;i<=3;i++){
        	if(buttonNum[i]+buttonNum[i+3]+buttonNum[i+6] == 3 || buttonNum[i]+buttonNum[i+3]+buttonNum[i+6] == -3){
        		temp1 = i;
        		temp2 = i+3;
        		temp3 = i+6;
        		END = 1;
        	}
        }//直
        for(i = 0;i<=6;i = i+6){
        	if(buttonNum[1+i]+buttonNum[5]+buttonNum[9-i] == 3 || buttonNum[1+i]+buttonNum[5]+buttonNum[9-i] == -3){
        		temp1 = 1+i;
        		temp2 = 5;
        		temp3 = 9-i;
        		END = 1;
        	}
        }//斜
        
       
        if(END == 1 || count == 9){
        	for(i = 1;i<=9;i++){
        		mainButton[i].setEnabled(false);
        	}
        	if (END == 1){
        		mainButton[temp1].setEnabled(true);
        		mainButton[temp2].setEnabled(true);
        		mainButton[temp3].setEnabled(true);
        	if((count-1)%2 == 0){
        		if(toComputer == 1){
        			leftInfo.setText("  兔子提比勝利!");
        		}
        		else{
        			leftInfo.setText("  玩家一勝利!!!");
        		}
        		rightInfo.setText("            OAO");
	        }
        	else{
        		leftInfo.setText("            OAO");
        		rightInfo.setText("  玩家二勝利!!!");
	        }	
        }
        else{
        	leftInfo.setText("        ~~平手~~");
        	rightInfo.setText("        ~~平手~~");
	    }

        
        }
        else{
        	if(count%2 == 0){
        		leftInfo.setText("  輪到玩家1");
            	rightInfo.setText("");
            }
            else{
            	leftInfo.setText("");
            	rightInfo.setText("  輪到玩家2");
            }
        }
        
        
        //重置
        if(buttonString.equals("Reset")){
        	END = 0;
        	count = 0;
        	for(i = 1;i<=9;i++){
        		buttonNum[i] = 0;
        		mainButton[i].setIcon(pic0);
        		mainButton[i].setEnabled(true);
        	}
        	leftInfo.setText("  輪到玩家1");
        	rightInfo.setText("");
        	if(toComputer == 1){
        		mainButton[5].setIcon(computer);
        		leftInfo.setText(" ");
        		buttonNum[5] = 1;
        		count++;
        	}
        }
        
        //離開
        if(buttonString.equals("Exit")){
        	System.exit(0);
        }
  
        //System.out.println("Unexpected error.");
    }

}
