package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
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
		s+="Type: Crowd Control Ability\n";
		s+="Area Of Effect: "+area+"\n";
		s+="Cast Range: "+getCastRange()+"\n";
		s+="Mana Cost: "+getManaCost()+"\n";
		s+="Action Cost: "+getRequiredActionPoints()+"\n";
		s+="Base Cooldown: "+getBaseCooldown()+"\n";
		s+="Current Cooldown: "+getCurrentCooldown()+"\n";
		s+=effect.toString();
		
		return s;
	}

}
