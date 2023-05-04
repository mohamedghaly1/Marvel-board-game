package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() + healAmount);

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
		s+="Type: Healing Ability\n";
		s+="Area Of Effect: "+area+"\n";
		s+="Cast Range: "+getCastRange()+"\n";
		s+="Mana Cost: "+getManaCost()+"\n";
		s+="Action Cost: "+getRequiredActionPoints()+"\n";
		s+="Base Cooldown: "+getBaseCooldown()+"\n";
		s+="Current Cooldown: "+getCurrentCooldown()+"\n";
		s+="Heal Amount: "+getHealAmount()+"\n";
		
		return s;
		
	}
	

}
