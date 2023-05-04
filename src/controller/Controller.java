package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.effects.EffectType;
import model.world.Champion;
import model.world.Direction;
import views.GameView;

public class Controller implements ActionListener, MouseListener {

	private GameView view;
	private Game model;

	private Player firstPlayer;
	private Player secondPlayer;
	private int firstChampCount;
	private int secondChampCount;
	private int firstLeaderCount;
	private int secondLeaderCount;
	private String firstPlayerName;
	private String secondPlayerName;

	private boolean select_2;
	private boolean select_4;

	private JButton selectedDirection;
	private JButton selectedAbility;
	private JButton selectedCell;
	private int selectedX;
	private int selectedY;

	public Controller() throws IOException {
		GameView.loadFonts();
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		view = new GameView();
		view.setListener(this);

		view.enterNames();
	}

	public static void main(String[] args) throws IOException {
		new Controller();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton) (e.getSource());

			// Name Page Buttons
			if (button.getName().equals("startGame_1")) {
				if (view.getFirstTextField().getText().equals("") || view.getSecondTextField().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Names", "Enter Names", JOptionPane.PLAIN_MESSAGE);
				} else {
					firstPlayerName = view.getFirstTextField().getText().substring(0, 1).toUpperCase()
							+ view.getFirstTextField().getText().substring(1);
					secondPlayerName = view.getSecondTextField().getText().substring(0, 1).toUpperCase()
							+ view.getSecondTextField().getText().substring(1);
					firstPlayer = new Player(firstPlayerName);
					secondPlayer = new Player(secondPlayerName);
					view.clearMainPanel();
					view.chooseFirstPlayer(firstPlayerName);
				}
			}

			// First Player Choosing Champion Page Buttons
			if (button.getName().length() > 16 && button.getName().substring(0, 16).equals("championChoose_2")) {
				String champName = button.getName().substring(16);
				if (select_2) {
					if (firstChampCount == 3)
						JOptionPane.showMessageDialog(null, "You Have Already Chosen 3 Champions", "Champions Chosen",
								JOptionPane.PLAIN_MESSAGE);
					else {
						for (int i = 0; i < Game.getAvailableChampions().size(); i++) {
							if (Game.getAvailableChampions().get(i).getName().equals(champName)) {
								firstPlayer.getTeam().add(Game.getAvailableChampions().get(i));
								Game.getAvailableChampions().remove(i);
								i--;
							}
						}
						firstChampCount++;
						button.setEnabled(false);
					}
				}
			}

			if (button.getName().equals("select_2")) {
				select_2 = !select_2;
				if (select_2) {
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				} else {
					button.setOpaque(false);
				}
				view.revalidate();
				view.repaint();
			}

			if (button.getName().equals("confirm_2")) {
				if (firstChampCount != 3)
					JOptionPane.showMessageDialog(null, "Choose 3 Champions", "Choose Champions",
							JOptionPane.PLAIN_MESSAGE);
				else {
					view.clearMainPanel();
					view.chooseFirstLeader(firstPlayerName, firstPlayer.getTeam());
				}
			}

			// First Player Choose Leader Buttons
			if (button.getName().length()>22 && button.getName().substring(0,22).equals("championChooseLeader_3")) {
				if (firstLeaderCount == 1)
					JOptionPane.showMessageDialog(null, "You have already chosen a leader", "Leader Already Chosen",
							JOptionPane.PLAIN_MESSAGE);
				else {
					firstLeaderCount++;
					String champName = button.getName().substring(22);
					Champion c = null;
					for (Champion champ : firstPlayer.getTeam()) {
						if (champ.getName().equals(champName))
							c = champ;
					}
					firstPlayer.setLeader(c);
					button.setEnabled(false);
				}
			}

			if (button.getName().equals("confirm_3")) {
				if (firstLeaderCount != 1)
					JOptionPane.showMessageDialog(null, "Choose a Leader !", "Choose Leader",
							JOptionPane.PLAIN_MESSAGE);
				else {

					view.clearMainPanel();
					view.chooseSecondPlayer(secondPlayerName);
				}
			}

			// Second Player Choose Champions Buttons

			if (button.getName().length() > 16 && button.getName().substring(0, 16).equals("championChoose_4")) {
				String champName = button.getName().substring(16);
				if (select_4) {
					if (secondChampCount == 3)
						JOptionPane.showMessageDialog(null, "You Have Already Chosen 3 Champions", "Champions Chosen",
								JOptionPane.PLAIN_MESSAGE);
					else {
						for (int i = 0; i < Game.getAvailableChampions().size(); i++) {
							if (Game.getAvailableChampions().get(i).getName().equals(champName)) {
								secondPlayer.getTeam().add(Game.getAvailableChampions().get(i));
								Game.getAvailableChampions().remove(i);
								i--;
							}
						}
						secondChampCount++;
						button.setEnabled(false);
					}
				}
			}

			if (button.getName().equals("select_4")) {
				select_4 = !select_4;
				if (select_4) {
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				} else {
					button.setOpaque(false);
				}
				view.revalidate();
				view.repaint();
			}

			if (button.getName().equals("confirm_4")) {
				if (secondChampCount != 3)
					JOptionPane.showMessageDialog(null, "Choose 3 Champions", "Choose Champions",
							JOptionPane.PLAIN_MESSAGE);
				else {
					view.clearMainPanel();
					view.chooseSecondLeader(secondPlayerName, secondPlayer.getTeam());
				}

			}

			// Second Player Choose Leader Buttons

			if (button.getName().length()>22 && button.getName().substring(0,22).equals("championChooseLeader_5")) {
				if (secondLeaderCount == 1)
					JOptionPane.showMessageDialog(null, "You have already chosen a leader", "Leader Already Chosen",
							JOptionPane.PLAIN_MESSAGE);
				else {
					secondLeaderCount++;
					String champName = button.getName().substring(22);
					Champion c = null;
					for (Champion champ : secondPlayer.getTeam()) {
						if (champ.getName().equals(champName))
							c = champ;
					}
					secondPlayer.setLeader(c);
					button.setEnabled(false);
				}
			}

			if (button.getName().equals("confirm_5")) {
				
				if (secondLeaderCount != 1)
					JOptionPane.showMessageDialog(null, "Choose a Leader !", "Choose Leader",
							JOptionPane.PLAIN_MESSAGE);
				else {
					model = new Game(firstPlayer, secondPlayer);
					model.setListener(this);
					view.clearMainPanel();
					view.gamePhase(model.getBoard(), model.getCurrentChampion(), model.getFirstPlayer(),
							model.getSecondPlayer(), model.isFirstLeaderAbilityUsed(),
							model.isSecondLeaderAbilityUsed(), model.getTurnOrder());
				}
			}

			// Game Phase

			if (button.getName().equals("endTurn_g")) {
				String x = model.getCurrentPlayerName() +" Ended his turn!";
				model.endTurn();
				view.getLastAction().setText(x);
				
				afterAction();
			}

			if (button.getName().equals("up_g") || button.getName().equals("down_g")
					|| button.getName().equals("right_g") || button.getName().equals("left_g")) {
				if (selectedDirection == null) {
					selectedDirection = button;
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				} else {
					selectedDirection.setOpaque(false);
					selectedDirection = button;
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				}
				view.revalidate();
				view.repaint();
			}

			if (button.getName().equals("ability1_g") || button.getName().equals("ability2_g")
					|| button.getName().equals("ability3_g") || button.getName().equals("ability4_g")) {
				if (selectedAbility == null) {
					selectedAbility = button;
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				} else {
					selectedAbility.setOpaque(false);
					selectedAbility = button;
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				}
				view.revalidate();
				view.repaint();
			}

			if (button.getName().substring(2).equals("cell_g")) {
				selectedX = Integer.parseInt(button.getName().substring(0, 1));
				selectedY = Integer.parseInt(button.getName().substring(1, 2));
				if (selectedCell == null) {
					selectedCell = button;
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				} else {
					selectedCell.setOpaque(false);
					selectedCell = button;
					button.setBackground(new Color(0, 0, 100));
					button.setOpaque(true);
				}
				view.revalidate();
				view.repaint();
			}

			if (button.getName().equals("move_g")) {
				try {
					if (selectedDirection == null) {
						JOptionPane.showMessageDialog(null, "Select Direction First", "Select Direction",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						Direction d = null;
						switch (selectedDirection.getName()) {
						case "up_g":
							d = Direction.UP;
							break;
						case "down_g":
							d = Direction.DOWN;
							break;
						case "right_g":
							d = Direction.RIGHT;
							break;
						case "left_g":
							d = Direction.LEFT;
							break;
						}
						model.move(d);
						view.getLastAction().setText(model.getCurrentPlayerName() +" Moved!");
						selectedDirection = null;
						selectedAbility = null;
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Invalid Action", JOptionPane.PLAIN_MESSAGE);
				}
			}

			if (button.getName().equals("attack_g")) {
				try {
					if (selectedDirection == null) {
						JOptionPane.showMessageDialog(null, "Select Direction First", "Select Direction",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						Direction d = null;
						switch (selectedDirection.getName()) {
						case "up_g":
							d = Direction.UP;
							break;
						case "down_g":
							d = Direction.DOWN;
							break;
						case "right_g":
							d = Direction.RIGHT;
							break;
						case "left_g":
							d = Direction.LEFT;
							break;
						}
						model.attack(d);
						view.getLastAction().setText(model.getCurrentPlayerName() +" Attacked!");
						afterAction();
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Invalid Action", JOptionPane.PLAIN_MESSAGE);
				}
			}

			if (button.getName().equals("castAbility_g")) {
				if (selectedAbility == null) {
					JOptionPane.showMessageDialog(null, "Select Ability First", "Select Ability",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					Ability a = null;
					for (Ability g : model.getCurrentChampion().getAbilities()) {
						if (selectedAbility.getText().equals(g.getName())) {
							a = g;
						}
					}
					if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
						if (selectedDirection == null)
							JOptionPane.showMessageDialog(null, "Select Direction First", "Select Direction",
									JOptionPane.PLAIN_MESSAGE);
						else {
							try {
								Direction d = null;
								switch (selectedDirection.getName()) {
								case "up_g":
									d = Direction.UP;
									break;
								case "down_g":
									d = Direction.DOWN;
									break;
								case "right_g":
									d = Direction.RIGHT;
									break;
								case "left_g":
									d = Direction.LEFT;
									break;
								}
								model.castAbility(a, d);
								view.getLastAction().setText(model.getCurrentPlayerName() +" Casted an ability!");
								afterAction();
							} catch (Exception f) {
								JOptionPane.showMessageDialog(null, f.getMessage(), "Invalid Action",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					} else if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
						if (selectedCell == null) {
							JOptionPane.showMessageDialog(null, "Select A Cell First", "Invalid Action",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							try {
								model.castAbility(a, selectedX, selectedY);
								view.getLastAction().setText(model.getCurrentPlayerName() +" Casted an ability!");
								afterAction();

							} catch (Exception f) {
								JOptionPane.showMessageDialog(null, f.getMessage(), "Invalid Action",
										JOptionPane.PLAIN_MESSAGE);
							}
						}

					} else {
						try {
							model.castAbility(a);
							view.getLastAction().setText(model.getCurrentPlayerName() +" Casted an ability!");
							afterAction();
						} catch (Exception f) {
							JOptionPane.showMessageDialog(null, f.getMessage(), "Invalid Action",
									JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
			}

			if (button.getName().equals("useLeaderAbility_g")) {
				try {
					model.useLeaderAbility();
					view.getLastAction().setText(model.getCurrentPlayerName() +" Used his leader ability!");
					afterAction();
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Invalid Action", JOptionPane.PLAIN_MESSAGE);
				}

			}

			if (button.getName().equals("abilityCurrentChampInfo_g")) {
				Champion c = model.getCurrentChampion();
				Ability a = null;
				// a null error here
				for (Ability g : c.getAbilities()) {
					if (g.getName().equals(button.getText()))
						a = g;
				}
				JOptionPane.showMessageDialog(null, a.toString(), "Ability Info", JOptionPane.PLAIN_MESSAGE);
			}

			if (button.getName().equals("effectCurrentChampInfo_g")) {
				Champion c = model.getCurrentChampion();
				Effect ee = null;
				for (Effect g : c.getAppliedEffects()) {
					if (g.getName().equals(button.getText()))
						ee = g;
				}
				JOptionPane.showMessageDialog(null, ee.toString(), "Effect Info", JOptionPane.PLAIN_MESSAGE);
			}

			if (button.getName().equals("firstChampInfo_g")) {
				Champion c = null;
				for (Champion d : model.getFirstPlayer().getTeam()) {
					if (d.getName().equals(button.getText()))
						c = d;
				}
				String l = "";
				if (c == model.getFirstPlayer().getLeader())
					l = "Status: Leader\n";
				else
					l = "Status: Not A Leader\n";
				JOptionPane.showMessageDialog(null, l + c.toString(), "Champion Info", JOptionPane.PLAIN_MESSAGE);
			}

			if (button.getName().equals("secondChampInfo_g")) {
				Champion c = null;
				for (Champion d : model.getSecondPlayer().getTeam()) {
					if (d.getName().equals(button.getText()))
						c = d;
				}
				String l = "";
				if (c == model.getSecondPlayer().getLeader())
					l = "Status: Leader\n";
				else
					l = "Status: Not A Leader\n";
				JOptionPane.showMessageDialog(null, l + c.toString(), "Champion Info", JOptionPane.PLAIN_MESSAGE);
			}

		}
	}

	public void updateAll(Object[][] board, Champion c, Player firstPlayer, Player secondPlayer, boolean fLA,
			boolean sLA, PriorityQueue turnOrder) {
		view.gamePhase(board, c, firstPlayer, secondPlayer, fLA, sLA, turnOrder);
	}

	private void afterAction() {
		selectedDirection = null;
		selectedAbility = null;
		selectedCell = null;
		if (model.checkGameOver() != null) {
			view.clearMainPanel();
			view.winScreen(model.checkGameOver());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JButton) {

			JButton button = (JButton) e.getSource();

			if (button.getName().length() > 16 && button.getName().substring(0, 14).equals("championChoose")) {
				String champName = button.getName().substring(16);
				Champion c = null;
				for (Champion d : Game.getAvailableChampions()) {
					if (d.getName().equals(champName)) {
						c = d;
					}
				}
				if(c==null)return;
				String s = "Name: " + c.getName() + "\n" + "HP: " + c.getMaxHP() + "\n" + "Mana: " + c.getMana() + "\n"
						+ "ActionPoints: " + c.getMaxActionPointsPerTurn() + "\n" + "Speed: " + c.getSpeed() + "\n"
						+ "Attack Range: " + c.getAttackRange() + "\n" + "Attack Damage: " + c.getAttackDamage() + "\n"
						+ "---------------------------" + "\n";
				for (int i = 0; i < 3; i++) {
					Ability a = c.getAbilities().get(i);
					String type = a.getType();
					String areaOfEffect = a.getArea();
					;
					s += "Ability " + (i + 1) + ":\n";
					s += "Name: " + a.getName() + "\n";
					s += "Type: " + type + "\n";
					s += "AreaOfEffect: " + areaOfEffect + "\n";
					s += "Cast Range: " + a.getCastRange() + "\n";
					s += "Mana Cost: " + a.getManaCost() + "\n";
					s += "Required Action Points: " + a.getRequiredActionPoints() + "\n";
					s += "Cooldown: " + a.getBaseCooldown() + "\n";
					if (a instanceof HealingAbility) {
						s += "Healing Amount: " + ((HealingAbility) a).getHealAmount() + "\n";
					} else if (a instanceof DamagingAbility) {
						s += "Damaging Amount: " + ((DamagingAbility) a).getDamageAmount() + "\n";
					} else if (((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF) {
						s += "Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n";
						s += "Effect Type: BUFF\n";
						s += "Effect Duration: " + ((CrowdControlAbility) a).getEffect().getDuration() + "\n";
					} else {
						s += "Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n";
						s += "Effect Type: DEBUFF\n";
						s += "Effect Duration: " + ((CrowdControlAbility) a).getEffect().getDuration() + "\n";
					}
					s += "---------------------------" + "\n";
				}
				view.updateChooseChampInfo(s);
				view.updateChooseChampPic("resources/images/figures/"+champName+"_FIGURE.png");
			}

		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
