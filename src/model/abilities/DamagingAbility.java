package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
	
	public String toString() {
		String s ="";
		String area = "";
		switch(getCastArea()) {
		case SINGLETARGET: area="Single Target";break;
		case TEAMTARGET: area ="Team Target";break;
		case SURROUND: area = "Surround";break;
		case SELFTARGET: area="Self Target";break;
		case DIRECTIONAL: area="Directional";break;
		}
		s+="Name: "+getName()+"\n";
		s+="Type: Damaging Ability\n";
		s+="Area Of Effect: "+area+"\n";
		s+="Cast Range: "+getCastRange()+"\n";
		s+="Mana Cost: "+getManaCost()+"\n";
		s+="Action Cost: "+getRequiredActionPoints()+"\n";
		s+="Base Cooldown: "+getBaseCooldown()+"\n";
		s+="Current Cooldown: "+getCurrentCooldown()+"\n";
		s+="Damage Amount: "+getDamageAmount()+"\n";
		
		return s;
	}
}
