package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public abstract class Ability {
	private String name;
	private int manaCost;
	private int baseCooldown;
	private int currentCooldown;
	private int castRange;
	private AreaOfEffect castArea;
	private int requiredActionPoints;

	public Ability(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required) {
		this.name = name;
		this.manaCost = cost;
		this.baseCooldown = baseCoolDown;
		this.currentCooldown = 0;
		this.castRange = castRange;
		this.castArea = area;
		this.requiredActionPoints = required;
	}

	public String getType() {
		if (this instanceof HealingAbility) {
			return "Healing Ability";
		} else if (this instanceof DamagingAbility) {
			return "Damaging Ability";
		} else
			return"Effect";
	}
	
	public String getArea() {
		
		String areaOfEffect="";
		switch (getCastArea()) {
		case SINGLETARGET:
			areaOfEffect = "SingleTarget";
			break;
		case TEAMTARGET:
			areaOfEffect = "TeamTarget";
			break;
		case DIRECTIONAL:
			areaOfEffect = "Directional";
			break;
		case SELFTARGET:
			areaOfEffect = "SelfTarget";
			break;
		case SURROUND:
			areaOfEffect = "Surround";
			break;
		}
		
		return areaOfEffect;
	}
	
	public int getCurrentCooldown() {
		return currentCooldown;
	}
	public abstract void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;

	public void setCurrentCooldown(int currentCoolDown) {
		if (currentCoolDown < 0)
			currentCoolDown = 0;
		else if (currentCoolDown > baseCooldown)
			currentCoolDown = baseCooldown;
		this.currentCooldown = currentCoolDown;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public int getCastRange() {
		return castRange;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}

}
