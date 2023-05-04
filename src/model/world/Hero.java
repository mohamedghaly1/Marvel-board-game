package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {
			int i = 0;
			while (i < c.getAppliedEffects().size()) {
				Effect e = c.getAppliedEffects().get(i);
				if (e.getType() == EffectType.DEBUFF) {
					e.remove(c);
					c.getAppliedEffects().remove(e);

				} else
					i++;
			}
				Embrace em = new Embrace(2);
				
				em.apply(c);
				c.getAppliedEffects().add(em);
				
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
		s+="Type: Hero\n";
		s+= "-------------------\n";
		s+="Applied Effects:\n";
		for(Effect e : getAppliedEffects()) {
			s+=e.toString();
			s+="--------\n";
		}
		
		return s;
		
	}

	}

