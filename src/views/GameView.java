package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;
import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import model.abilities.Ability;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Hero;

public class GameView extends JFrame {

	private Controller listener;
	private JPanel mainPanel;

	// enterNames components
	private JTextField firstTextField;
	private JTextField secondTextField;
	
	// chooseChamps 
	private JTextArea info;
	private JLabel pic;
	
	// Game Phase 
	private JPanel boardPanel;
	private JPanel playersInfoPanel;
	private JPanel currentChampPanel;
	private JPanel actionsPanel;
	private JPanel selectPanel;
	
	private JTextField lastAction;
	
	private Object[][] board;
	
	static Font giants;
	static Font motion;
	static Font excluded;

	public GameView() {
		setBounds(0, 0, 1440, 800);
		setTitle("Marvel - Ultimate War");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		mainPanel = new JPanel();
		add(mainPanel);
		

	}
	
	public void enterNames() throws IOException {
		
		mainPanel.setLayout(null);
		
		BackgroundPanel bg = new BackgroundPanel("resources/images/namesBG.jpg");
		bg.setLayout(null);
		bg.setBounds(0,0,1440,800);

		// Game Title
		JLabel gameTitle = new JLabel();
		gameTitle.setIcon(new ImageIcon("resources/images/gameStartTitle.png"));
		gameTitle.setBounds(0,30,1400,60);
		gameTitle.setHorizontalAlignment(JLabel.CENTER);
		

		JPanel namesPanel = new JPanel();
		namesPanel.setLayout(null);
		namesPanel.setBounds(481,105,482,521);
		namesPanel. setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
//		namesPanel.setBackground(new Color(38,111,223));

		JLabel firstTextFieldLabel = new JLabel("First Player Name");
		firstTextFieldLabel.setForeground(Color.WHITE);
		firstTextFieldLabel.setHorizontalAlignment(JLabel.CENTER);
		firstTextFieldLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		firstTextFieldLabel.setBounds(0, 0, 475, 340);

		firstTextField = new JTextField();
		firstTextField.setBounds(140, 190, 200, 50);

		JLabel secondTextFieldLabel = new JLabel("Second Player Name");
		secondTextFieldLabel.setForeground(Color.WHITE);
		secondTextFieldLabel.setHorizontalAlignment(JLabel.CENTER);
		secondTextFieldLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		secondTextFieldLabel.setBounds(0, 0, 475, 580);

		secondTextField = new JTextField();
		secondTextField.setBounds(140, 310, 200, 50);

		namesPanel.add(firstTextFieldLabel);
		namesPanel.add(firstTextField);
		namesPanel.add(secondTextFieldLabel);
		namesPanel.add(secondTextField);


		JButton startGame_1 = new JButton();
		startGame_1.setBounds(642,648,148,70);
		startGame_1.setName("startGame_1");
		startGame_1.addActionListener(listener);
		startGame_1.addMouseListener(listener);
		startGame_1.setText("START");
		startGame_1.setFont(new Font("Arial", Font.PLAIN, 20));
		startGame_1.setPreferredSize(new Dimension(150, 75));
		


		// Adding everything to main panel
		bg.add(gameTitle);
		bg.add(namesPanel);
		bg.add(startGame_1);
		
		mainPanel.add(bg);
		
		
		revalidate();
		repaint();
	}
	
	public void chooseFirstPlayer(String name) {

		BackgroundPanel bg = new BackgroundPanel("resources/images/chooseBG.jpg");
		bg.setBounds(0,0,1440,800);
		bg.setLayout(new BorderLayout());
		
		JLabel chooseTitle = new JLabel();
		
		chooseTitle.setFont(excluded.deriveFont(65f));
		chooseTitle.setForeground(new Color(240,250,146));
		chooseTitle.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + ", Choose Your Champions !");
		chooseTitle.setHorizontalAlignment(JLabel.CENTER);

		info = new JTextArea();
		info.setPreferredSize(new Dimension(290,532));
		info.setForeground(Color.WHITE);
		info.setFont(new Font("Arial",Font.PLAIN,12));
		info.setEditable(false);
		info.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		JPanel champions = new JPanel();
		champions.setLayout(new GridLayout(3, 5));
		champions.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		for (int i = 0; i < 15; i++) {
			JButton b = new JButton();
			b.setName("championChoose_2"+Game.getAvailableChampions().get(i).getName());
			b.setOpaque(false);
			b.addActionListener(listener);
			b.addMouseListener(listener);
			//b.setText(Game.getAvailableChampions().get(i).getName());
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/images/icons/"+Game.getAvailableChampions().get(i).getName()+"_ICON.jpg").getImage().getScaledInstance(170, 186, Image.SCALE_DEFAULT));
			b.setIcon(imageIcon);
//			ImageIcon img = new ImageIcon("resources/images/"+Game.getAvailableChampions().get(i).getName()+"_ICON.jpg");
//			b.setIcon(img);
			champions.add(b);
		}
		
		pic = new JLabel();
		pic.setPreferredSize(new Dimension(290,532));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 150));
		buttonPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

		JButton confirm_2 = new JButton();
		confirm_2.setName("confirm_2");
		confirm_2.addActionListener(listener);
		confirm_2.addMouseListener(listener);
		confirm_2.setText("CONFIRM");
		confirm_2.setFont(new Font("Arial", Font.PLAIN, 20));
		confirm_2.setPreferredSize(new Dimension(150, 75));

		JButton select_2 = new JButton();
		select_2.setName("select_2");
		select_2.addActionListener(listener);
		select_2.addMouseListener(listener);
		select_2.setText("SELECT");
		select_2.setFont(new Font("Arial", Font.PLAIN, 20));
		select_2.setPreferredSize(new Dimension(150, 75));

		buttonPanel.add(select_2);
		buttonPanel.add(confirm_2);

		bg.add(info,BorderLayout.WEST);
		bg.add(pic,BorderLayout.EAST);
		bg.add(chooseTitle, BorderLayout.NORTH);
		bg.add(champions, BorderLayout.CENTER);
		bg.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(bg);

		revalidate();
		repaint();
	}

	public void chooseFirstLeader(String name, ArrayList<Champion> arr) {
		mainPanel.setLayout(null);
		
		BackgroundPanel bg = new BackgroundPanel("resources/images/chooseBG.jpg");
		bg.setLayout(new BorderLayout());
		bg.setBounds(0,0,1440,800);
		
		JLabel chooseTitle = new JLabel();
		chooseTitle.setFont(excluded.deriveFont(65f));
		chooseTitle.setForeground(new Color(240,250,146));
		chooseTitle.setPreferredSize(new Dimension(50,120));
		chooseTitle.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + ", Choose Your Leader !");
		chooseTitle.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel empty1 = new JPanel();
		empty1.setPreferredSize(new Dimension(640,532));
		empty1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

		JPanel champions = new JPanel();
		champions.setLayout(new GridLayout(3, 5));
		for (int i = 0; i < 3; i++) {
			JButton b = new JButton();
			
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			
			b.setName("championChooseLeader_3"+arr.get(i).getName());
			b.addActionListener(listener);
			b.addMouseListener(listener);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/images/icons/"+arr.get(i).getName()+"_ICON.jpg").getImage().getScaledInstance(170, 186, Image.SCALE_DEFAULT));
			b.setIcon(imageIcon);
			champions.add(b);
		}

		JPanel empty2 = new JPanel();
		empty2.setPreferredSize(new Dimension(640,532));
		empty2.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		buttonPanel.setPreferredSize(new Dimension(0, 170));

		JButton confirm = new JButton();
		confirm.setName("confirm_3");
		confirm.addActionListener(listener);
		confirm.addMouseListener(listener);
		confirm.setText("CONFIRM");
		confirm.setFont(new Font("Arial", Font.PLAIN, 20));
		confirm.setPreferredSize(new Dimension(150, 75));

		buttonPanel.add(confirm);

		bg.add(empty1,BorderLayout.WEST);
		bg.add(empty2,BorderLayout.EAST);
		bg.add(chooseTitle, BorderLayout.NORTH);
		bg.add(champions, BorderLayout.CENTER);
		bg.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(bg);

		revalidate();
		repaint();
	}
	
	public void chooseSecondPlayer(String name) {

		BackgroundPanel bg = new BackgroundPanel("resources/images/chooseBG.jpg");
		bg.setBounds(0,0,1440,800);
		bg.setLayout(new BorderLayout());
		
		JLabel chooseTitle = new JLabel();
		
		chooseTitle.setFont(excluded.deriveFont(65f));
		chooseTitle.setForeground(new Color(240,250,146));
		chooseTitle.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + ", Choose Your Champions !");
		chooseTitle.setPreferredSize(new Dimension(50,120));
		chooseTitle.setHorizontalAlignment(JLabel.CENTER);
		
		info = new JTextArea();
		info.setPreferredSize(new Dimension(350,532));
		info.setForeground(Color.WHITE);
		info.setFont(new Font("Arial",Font.PLAIN,12));
		info.setEditable(false);
		info.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));

		JPanel champions = new JPanel();
		champions.setMaximumSize(new Dimension(680,540));
		champions.setLayout(new GridLayout(3, 5));
		champions.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		for (int i = 0; i < 12; i++) {
			JButton b = new JButton();
			b.setName("championChoose_4"+Game.getAvailableChampions().get(i).getName());
			b.setOpaque(false);
			b.addActionListener(listener);
			b.addMouseListener(listener);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/images/icons/"+Game.getAvailableChampions().get(i).getName()+"_ICON.jpg").getImage().getScaledInstance(220, 170, Image.SCALE_DEFAULT));
			b.setIcon(imageIcon);
			champions.add(b);
		}

		pic = new JLabel();
		pic.setPreferredSize(new Dimension(380,532));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 150));
		buttonPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

		JButton confirm_4 = new JButton();
		confirm_4.setName("confirm_4");
		confirm_4.addActionListener(listener);
		confirm_4.addMouseListener(listener);
		confirm_4.setText("CONFIRM");
		confirm_4.setFont(new Font("Arial", Font.PLAIN, 20));
		confirm_4.setPreferredSize(new Dimension(150, 75));

		JButton select_4 = new JButton();
		select_4.setName("select_4");
		select_4.addActionListener(listener);
		select_4.addMouseListener(listener);
		select_4.setText("SELECT");
		select_4.setFont(new Font("Arial", Font.PLAIN, 20));
		select_4.setPreferredSize(new Dimension(150, 75));

		buttonPanel.add(select_4);
		buttonPanel.add(confirm_4);

		bg.add(info,BorderLayout.WEST);
		bg.add(pic,BorderLayout.EAST);
		bg.add(chooseTitle, BorderLayout.NORTH);
		bg.add(champions, BorderLayout.CENTER);
		bg.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(bg);
		
		revalidate();
		repaint();
	}
	
	public void chooseSecondLeader(String name, ArrayList<Champion> arr) {

		mainPanel.setLayout(null);
		
		BackgroundPanel bg = new BackgroundPanel("resources/images/chooseBG.jpg");
		bg.setLayout(new BorderLayout());
		bg.setBounds(0,0,1440,800);
		
		JLabel chooseTitle = new JLabel();
		chooseTitle.setFont(excluded.deriveFont(65f));
		chooseTitle.setForeground(new Color(240,250,146));
		chooseTitle.setPreferredSize(new Dimension(50,120));
		chooseTitle.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + ", Choose Your Leader !");
		chooseTitle.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel empty1 = new JPanel();
		empty1.setPreferredSize(new Dimension(640,532));
		empty1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

		JPanel champions = new JPanel();
		champions.setLayout(new GridLayout(3, 5));
		for (int i = 0; i < 3; i++) {
			JButton b = new JButton();
			b.setName("championChooseLeader_5"+arr.get(i).getName());
			b.addActionListener(listener);
			b.addMouseListener(listener);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/images/icons/"+arr.get(i).getName()+"_ICON.jpg").getImage().getScaledInstance(170, 186, Image.SCALE_DEFAULT));
			b.setIcon(imageIcon);
			champions.add(b);
		}

		JPanel empty2 = new JPanel();
		empty2.setPreferredSize(new Dimension(640,532));
		empty2.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		buttonPanel.setPreferredSize(new Dimension(0, 170));

		JButton confirm = new JButton();
		confirm.setName("confirm_5");
		confirm.addActionListener(listener);
		confirm.addMouseListener(listener);
		confirm.setText("CONFIRM");
		confirm.setFont(new Font("Arial", Font.PLAIN, 20));
		confirm.setPreferredSize(new Dimension(150, 75));

		buttonPanel.add(confirm);

		bg.add(empty1,BorderLayout.WEST);
		bg.add(empty2,BorderLayout.EAST);
		bg.add(chooseTitle, BorderLayout.NORTH);
		bg.add(champions, BorderLayout.CENTER);
		bg.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(bg);

		revalidate();
		repaint();
	}
	
	public void gamePhase(Object[][]b,Champion c,Player firstPlayer,Player secondPlayer,boolean fLA,boolean sLA,PriorityQueue turnOrder) {
	board=b;
	mainPanel.removeAll();
	mainPanel.setLayout(null);
	
	BackgroundPanel bg = new BackgroundPanel("resources/images/chooseBG.jpg");
	bg.setLayout(null);
	bg.setBounds(0,0,1440,800);
	
	updateBoardPanel(b,firstPlayer,secondPlayer);
	setUpActionsPanel();
	updateSelectPanel(c);
	updatePlayersInfoPanel(firstPlayer,secondPlayer,fLA,sLA);
	updateCurrentChampPanel(c,turnOrder);
	
	JLabel paris = new JLabel();
	paris.setBounds(0,770,1440,130);
	paris.setIcon(new ImageIcon("resources/images/marvelBannerjpg.jpg"));
	
	lastAction = new JTextField();
	lastAction.setEditable(false);
	lastAction.setBounds(110,670,200,50);
	lastAction.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
	selectPanel.add(lastAction);
	
	
	bg.add(boardPanel);
	bg.add(actionsPanel);
	bg.add(selectPanel);
	bg.add(playersInfoPanel);
	bg.add(currentChampPanel);
	bg.add(paris);
	
	mainPanel.add(bg);
	
	revalidate();
	repaint();
		
	}

	public void updateBoardPanel(Object[][]b,Player one, Player two) {
		board = b;
		boardPanel = new JPanel(new GridLayout(5,5));
		boardPanel.setBounds(395,0,650,650);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		boardPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		for(int i=4;i>=0;i--) {
			for(int j=0;j<5;j++) {
				JButton button = new JButton();
				button.addActionListener(listener);
				button.setName(i+""+j+"cell_g");
				String text = "";
				if(board[i][j] instanceof Champion) {
					if(one.getTeam().contains(board[i][j]))button.setForeground(Color.RED);
					else button.setForeground(Color.BLUE);
					ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/images/icons/"+((Champion)board[i][j]).getName()+"_ICON.jpg").getImage().getScaledInstance(53, 80, Image.SCALE_DEFAULT));
					button.setIcon(imageIcon);
					text = "<html>"+((Champion)board[i][j]).getName()+"<P>HP: "+((Champion)board[i][j]).getCurrentHP()+"<html>";
				}
				else if(board[i][j] instanceof Cover) text = "<html>"+"Cover"+"<P>HP: "+((Cover)board[i][j]).getCurrentHP()+"<html>";
				button.setText(text);
				button.setHorizontalAlignment(SwingConstants.CENTER);
				boardPanel.add(button);
			}
		}
		revalidate();
		repaint();
	}
	
	private void setUpActionsPanel() {
		actionsPanel = new JPanel(new GridLayout(1,5));
		actionsPanel.setBounds(395,650,650,120);
		actionsPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
//		actionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		
		JButton move = new JButton();
		move.addActionListener(listener);
		move.setName("move_g");
		move.setText("Move");
		actionsPanel.add(move);
		
		JButton attack = new JButton();
		attack.addActionListener(listener);
		attack.setName("attack_g");
		attack.setText("Attack");
		actionsPanel.add(attack);
		
		JButton castAbility = new JButton();
		castAbility.addActionListener(listener);
		castAbility.setName("castAbility_g");
		castAbility.setText("Cast Ability");
		actionsPanel.add(castAbility);
		
		JButton useLeaderAbility = new JButton();
		useLeaderAbility.addActionListener(listener);
		useLeaderAbility.setName("useLeaderAbility_g");
		useLeaderAbility.setText("Use Leader Ability");
		actionsPanel.add(useLeaderAbility);
		
		JButton endTurn = new JButton();
		endTurn.setName("endTurn_g");
		endTurn.setText("End Turn");
		endTurn.addActionListener(listener);
		actionsPanel.add(endTurn);
		
		revalidate();
		repaint();
		
	}
	
	public void updateSelectPanel(Champion c) {
		selectPanel = new JPanel(null);
		selectPanel.setBounds(1045,0,395,770);
		selectPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		JLabel direction = new JLabel();
		direction.setText("Select Direction");
		direction.setFont(excluded.deriveFont(15f));
		direction.setForeground(Color.WHITE);
		direction.setHorizontalAlignment(SwingConstants.CENTER);
		direction.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		direction.setBounds(100,50,190,50);
		
		JButton up = new JButton();
		up.setName("up_g");
		up.setText("UP");
		up.addActionListener(listener);
		up.setBounds(155,112,70,70);
		
		JButton down = new JButton();
		down.addActionListener(listener);
		down.setName("down_g");
		down.setText("DOWN");
		down.setBounds(155,195,70,70);
		
		JButton right = new JButton();
		right.addActionListener(listener);
		right.setName("right_g");
		right.setText("RIGHT");
		right.setBounds(230,152,70,70);
		
		JButton left = new JButton();
		left.addActionListener(listener);
		left.setName("left_g");
		left.setText("LEFT");
		left.setBounds(80,152,70,70);
		
		JLabel ability = new JLabel();
		ability.setText("Select Ability");
		ability.setFont(excluded.deriveFont(15f));
		ability.setForeground(Color.WHITE);
		ability.setHorizontalAlignment(SwingConstants.CENTER);
		ability.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		ability.setBounds(100,400,190,50);
		
		JPanel abilitiesPanel = new JPanel(new GridLayout(1,4));
		abilitiesPanel.setBounds(20,465,350,150);
		
		int i=1;
		for(Ability a : c.getAbilities()) {
			JButton ability1 = new JButton();
			ability1.addActionListener(listener);
			ability1.setName("ability"+i+"_g");
			ability1.setText(a.getName());
			abilitiesPanel.add(ability1);
			i++;
		}
		
		selectPanel.add(right);
		selectPanel.add(left);
		selectPanel.add(up);
		selectPanel.add(down);
		selectPanel.add(direction);
		selectPanel.add(ability);
		selectPanel.add(abilitiesPanel);
		
		revalidate();
		repaint();
		
	}
	
	public void updatePlayersInfoPanel(Player firstPlayer, Player secondPlayer,boolean fLA, boolean sLA) {
		playersInfoPanel = new JPanel(null);
		playersInfoPanel.setBounds(0,0,395,385);
		playersInfoPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		playersInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		
		JLabel header = new JLabel();
		header.setText("Players Remaining Champions and Info:");
		header.setFont(excluded.deriveFont(10f));
		header.setForeground(Color.WHITE);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		header.setBounds(63,0,270,25);
		
		JLabel firstPlayerName = new JLabel();
		firstPlayerName.setText(firstPlayer.getName());
		firstPlayerName.setFont(excluded.deriveFont(17f));
		firstPlayerName.setForeground(Color.WHITE);
		firstPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		firstPlayerName.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		firstPlayerName.setBounds(100,30,190,50);
		
		JPanel firstPlayerChamps = new JPanel(new GridLayout(1,3));
		firstPlayerChamps.setBounds(43,85,300,70);
		for(Champion c : firstPlayer.getTeam()) {
			JButton firstChampInfo = new JButton();
			firstChampInfo.addActionListener(listener);
			firstChampInfo.setName("firstChampInfo_g");
			firstChampInfo.setText(c.getName());
			firstPlayerChamps.add(firstChampInfo);
			
		}
		
		JLabel firstPlayerLA = new JLabel();
		if(fLA)firstPlayerLA.setText("Leader Ability Used");
		else firstPlayerLA.setText("Leader Ability Not Used");
		firstPlayerLA.setFont(excluded.deriveFont(16f));
		firstPlayerLA.setHorizontalAlignment(SwingConstants.CENTER);
		firstPlayerLA.setForeground(Color.WHITE);
		firstPlayerLA.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
		firstPlayerLA.setBounds(59,155,270,40);
		
		JLabel secondPlayerName = new JLabel();
		secondPlayerName.setText(secondPlayer.getName());
		secondPlayerName.setFont(excluded.deriveFont(17f));
		secondPlayerName.setForeground(Color.WHITE);
		secondPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		secondPlayerName.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		secondPlayerName.setBounds(100,215,190,50);
		
		JPanel secondPlayerChamps = new JPanel(new GridLayout(1,3));
		secondPlayerChamps.setBounds(43,270,300,70);
		for(Champion c : secondPlayer.getTeam()) {
			JButton secondChampInfo = new JButton();
			secondChampInfo.addActionListener(listener);
			secondChampInfo.setName("secondChampInfo_g");
			secondChampInfo.setText(c.getName());
			secondPlayerChamps.add(secondChampInfo);
			
		}
		
		JLabel secondPlayerLA = new JLabel();
		if(sLA)secondPlayerLA.setText("Leader Ability Used");
		else secondPlayerLA.setText("Leader Ability Not Used");
		secondPlayerLA.setFont(excluded.deriveFont(16f));
		secondPlayerLA.setForeground(Color.WHITE);
		secondPlayerLA.setHorizontalAlignment(SwingConstants.CENTER);
		secondPlayerLA.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
		secondPlayerLA.setBounds(59,340,270,40);
		
		
		playersInfoPanel.add(firstPlayerLA);
		playersInfoPanel.add(secondPlayerLA);
		playersInfoPanel.add(header);
		playersInfoPanel.add(firstPlayerChamps);
		playersInfoPanel.add(secondPlayerChamps);
		playersInfoPanel.add(firstPlayerName);
		playersInfoPanel.add(secondPlayerName);
		
		
		revalidate();
		repaint();
	}
	
	public void updateCurrentChampPanel(Champion c,PriorityQueue turnOrder) {
		
		currentChampPanel = new JPanel(null);
		currentChampPanel.setBounds(0,385,395,385);
		currentChampPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		currentChampPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		
		JLabel header = new JLabel();
		header.setBounds(64,8,270,25);
		header.setText(c.getName());
		header.setFont(excluded.deriveFont(13f));
		header.setForeground(Color.WHITE);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		
//		JLabel name = new JLabel();
//		name.setBounds(6,38,62,72);
//		name.setText(c.getName());
//		name.setFont(new Font("TimesRoman", Font.PLAIN, 16));
//		name.setHorizontalAlignment(SwingConstants.CENTER);
//		name.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		
		JLabel abilities = new JLabel();
		abilities.setBounds(6,120,62,72);
		abilities.setText("Abilities");
		abilities.setFont(excluded.deriveFont(9f));
		abilities.setForeground(Color.WHITE);
		abilities.setHorizontalAlignment(SwingConstants.CENTER);
		abilities.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		
		JLabel effects = new JLabel();
		effects.setBounds(6,202,62,72);
		effects.setText("<html>Applied<P>Effects<html>");
		effects.setFont(excluded.deriveFont(11f));
		effects.setForeground(Color.WHITE);
		effects.setHorizontalAlignment(SwingConstants.CENTER);
		effects.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		
		JTextArea description = new JTextArea();
		description.setBounds(55,38,140,54);
		description.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		description.setLineWrap(true);
		String s = "";
		String type = "";
		if(c instanceof Hero)type = "Hero";
		else if(c instanceof AntiHero)type="AntiHero";
		else type = "Villain";
		s+="Type: "+type+"\n";
		s+="Current HP: "+c.getCurrentHP()+"\n";
		s+="Mana: "+c.getMana()+"\n";
		description.append(s);
		description.setEditable(false);
		
		JTextArea description2 = new JTextArea();
		description2.setBounds(200,38,140,54);
		description2.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		description2.setLineWrap(true);
		String s1 = "";
		s1+="Action Points: "+c.getCurrentActionPoints()+"\n";
		s1+="Attack Damage: "+c.getAttackDamage()+"\n";
		s1+="Attack Range: "+c.getAttackRange()+"\n";
		description2.append(s1);
		description2.setEditable(false);
		
		JPanel abilitiesPanel = new JPanel(new GridLayout(1,3));
		abilitiesPanel.setBounds(73,120,300,72);
		for(Ability a:c.getAbilities()) {
			JButton ab = new JButton();
			ab.addActionListener(listener);
			ab.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			ab.setText(a.getName());
			ab.setName("abilityCurrentChampInfo_g");
			abilitiesPanel.add(ab);
		}
		
		JPanel effectsPanel = new JPanel(new GridLayout(0,3));
		effectsPanel.setBounds(73,202,300,72);
		for(Effect e: c.getAppliedEffects()) {
			JButton eb = new JButton();
			eb.addActionListener(listener);
			eb.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			eb.setText(e.getName());
			eb.setName("effectCurrentChampInfo_g");
			effectsPanel.add(eb);
		}
		
		JLabel nextUp = new JLabel();
		nextUp.setBounds(6,290,62,72);
		nextUp.setText("<html>Next<P>Up<html>");
		nextUp.setFont(excluded.deriveFont(13f));
		nextUp.setForeground(Color.WHITE);
		nextUp.setHorizontalAlignment(SwingConstants.CENTER);
		nextUp.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		
		JPanel turns = new JPanel(new GridLayout(1,5));
		turns.setBounds(73,290,300,72);
		PriorityQueue temp = new PriorityQueue(6);
		temp.insert(turnOrder.remove());
		while(!turnOrder.isEmpty()) {
			Champion cur = (Champion)(turnOrder.remove());
			temp.insert((Comparable)cur);
			JTextArea tt = new JTextArea();
			tt.append(cur.getName());
			tt.setLineWrap(true);
			tt.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			tt.setFont(new Font("TimesRoman", Font.PLAIN, 12));
			turns.add(tt);
		}
		while(!temp.isEmpty()) {
			turnOrder.insert(temp.remove());
		}
		
		
		currentChampPanel.add(nextUp);
		currentChampPanel.add(turns);
		currentChampPanel.add(abilitiesPanel);
		currentChampPanel.add(effectsPanel);
		currentChampPanel.add(abilities);
		currentChampPanel.add(effects);
		currentChampPanel.add(header);
		currentChampPanel.add(description);
		currentChampPanel.add(description2);
		
		revalidate();
		repaint();
		
	}
	
	public void winScreen(Player player) {
		mainPanel.setLayout(null);
		BackgroundPanel bg = new BackgroundPanel("resources/images/winBG.jpg");
		bg.setLayout(new GridLayout(1,1));
		bg.setBounds(0,0,1440,800);
		
		mainPanel.setLayout(new GridLayout(1,1));
		JLabel l = new JLabel();
		l.setFont(excluded.deriveFont(175f));
		l.setForeground(Color.WHITE);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setText(player.getName()+" Wins !");
		
		bg.add(l);
		mainPanel.add(bg);
		
		revalidate();
		repaint();
	}
	
	public static void main(String [] args) {
		
		
	}
	
	



	public void setListener(Controller c) {
		listener = c;
	}

	public JTextField getFirstTextField() {
		return firstTextField;
	}
	
	public JTextField getLastAction() {
		return lastAction;
	}

	public JTextField getSecondTextField() {
		return secondTextField;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void updateChooseChampInfo(String s) {
		info.setText(s);
		revalidate();
		repaint();
	}
	
	public void clearMainPanel() {
		mainPanel.removeAll();
		revalidate();
		repaint();
	}

	public static void loadFonts() {
		try {
		    //create the font to use. Specify the size!
		     giants = Font.createFont(Font.TRUETYPE_FONT, new File("resources/GiantsItalicPersonalUseBoldItalic-x3q6m.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    motion = Font.createFont(Font.TRUETYPE_FONT, new File("resources/MotionPersonalUseBold-2O0od.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    excluded = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Excludeditalic-jEr99.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(giants);
		    ge.registerFont(motion);
		    ge.registerFont(excluded);
		} catch (Exception e) {
		    e.printStackTrace();
		} 
	}

	public void updateChooseChampPic(String string) {
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(string).getImage().getScaledInstance(290, 532, Image.SCALE_DEFAULT));
		pic.setIcon(imageIcon);
		revalidate();
		repaint();
	}
	

}
