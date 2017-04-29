import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayersPanel extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 860;
    public static final int HEIGHT = 180;

    private ImageIcon[] pic = new ImageIcon[5];
    private ImageIcon[] playerPic = new ImageIcon[2];
    private String[] choButton = {"computer","A","B","C","D"}; 
    private JButton[] choseButton = new JButton[5];
 
    private int i;
    private int click = 0;
    private int toComputer = 0;
    
    
    public static void main(String[] args)
    {   	
        PlayersPanel players = new PlayersPanel();
    	players.setVisible(true);
    }

    public PlayersPanel( )
    {
        super("OOXX小遊戲  選擇兔子");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout( ));        
        
        pic[0] = new ImageIcon("pic\\00.jpg");
        pic[1] = new ImageIcon("pic\\01.jpg");
        pic[2] = new ImageIcon("pic\\02.jpg");
        pic[3] = new ImageIcon("pic\\03.jpg");
        pic[4] = new ImageIcon("pic\\04.jpg");

        JPanel chosePic = new JPanel( );
        chosePic.setLayout(new FlowLayout(10));
        add(chosePic, BorderLayout.CENTER);
        
        JLabel topLabel = new JLabel("  選擇玩家頭像 (P1/P2)   ->請先選擇是否對抗電腦");
        add(topLabel, BorderLayout.NORTH);
        
        for(i = 0;i<5;i++){
        	if(i == 0){
        		JLabel com = new JLabel("  對抗兔子");
        		chosePic.add(com);  
        	}
        	else if(i == 1){
        		JLabel pla = new JLabel("  玩家對戰");
        		chosePic.add(pla); 
        	}
        	choseButton[i] = new JButton(pic[i]);
	        choseButton[i].setActionCommand(choButton[i]);
	        choseButton[i].addActionListener(this);
	        chosePic.add(choseButton[i]);  
        }

    }

      
    public void actionPerformed(ActionEvent e)
    {	
        String buttonString = e.getActionCommand( );        
        if(click < 2){
        	for(i = 0;i<5;i++){
	        	if(buttonString.equals(choButton[i])){
	        		if(i==0){
	        			toComputer = 1;
	        		}
	        		playerPic[click] = pic[i];
	        		choseButton[i].setEnabled(false);
	        		click++;
	        	}	
	        }
        	choseButton[0].setEnabled(false);
        }
        if(click == 2){
        	this.setVisible(false);       	   		
        		GamePanel gui = new GamePanel( );
        		gui.setPic(playerPic[0], playerPic[1],toComputer);
        		gui.setVisible(true);
        }
        //System.out.println("Unexpected error.");
    }

}
