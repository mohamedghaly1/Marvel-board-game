package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.Stun;

public class AntiHero extends Champion {

	public AntiHero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c: targets)
		{
			Stun s = new Stun(2);
			c.getAppliedEffects().add(s);
			s.apply(c);
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
		s+="Type: AntiHero\n";
		s+= "-------------------\n";
		s+="Applied Effects:\n";
		for(Effect e : getAppliedEffects()) {
			s+=e.toString();
			s+="--------\n";
		}
		
		return s;
		
	}
}
