package model.world;

import java.util.ArrayList;

import model.effects.Effect;

public class Villain extends Champion {

	public Villain(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {

			c.setCurrentHP(0);

		}

	}
	
	public String toString() {
		String s="";
		s+= "Name: " + getName() + "\n";
		s+= "Current HP: " + getCurrentHP() + "\n";
		s+= "Mana: " + getMana()+ "\n";
		s+= "Speed: " + getSpeed()+ "\n";
		s+= "ActionPoints: " + getMaxActionPointsPerTurn() + "\n";
		s+= "Attack Damage: "+ getAttackDamage() + "\n";
		s+= "Attack Range: " + getAttackRange() + "\n";
		s+="Type: Villain\n";
		s+= "-------------------\n";
		s+="Applied Effects:\n";
		for(Effect e : getAppliedEffects()) {
			s+=e.toString();
			s+="--------\n";
		}
		
		return s;
		
	}

}
