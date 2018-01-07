package org.openbw.bwapi4j.unit;

import org.openbw.bwapi4j.type.TechType;
import org.openbw.bwapi4j.type.UnitType;
import org.openbw.bwapi4j.type.UpgradeType;

public class MachineShop extends Addon implements Mechanical, ResearchingFacility {

    private Researcher researcher;

    protected MachineShop(int id, int timeSpotted) {
        
        super(id, UnitType.Terran_Machine_Shop, timeSpotted);
        this.researcher = new Researcher();
    }

    @Override
    public void update(int[] unitData, int index, int frame) {

        this.researcher.update(unitData, index);
        super.update(unitData, index, frame);
    }

    public boolean researchSiegeMode() {
        
        return this.researcher.research(TechType.Tank_Siege_Mode);
    }

    public boolean researchSpiderMines() {
        
        return this.researcher.research(TechType.Spider_Mines);
    }

    public boolean upgradeIonThrusters() {
        
        return this.researcher.upgrade(UpgradeType.Ion_Thrusters);
    }

    public boolean upgradeCharonBoosters() {
        return this.researcher.upgrade(UpgradeType.Charon_Boosters);
    }

    @Override
    public boolean isUpgrading() {
        
        return this.researcher.isUpgrading();
    }

    @Override
    public boolean isResearching() {
        
        return this.researcher.isResearching();
    }

    @Override
    public boolean cancelResearch() {
        
        return this.researcher.cancelResearch();
    }

    @Override
    public boolean cancelUpgrade() {
        
        return this.researcher.cancelUpgrade();
    }

    @Override
    public boolean canResearch(TechType techType) {
        return this.researcher.canResearch(techType);
    }

    @Override
    public boolean canUpgrade(UpgradeType upgradeType) {
        return this.researcher.canUpgrade(upgradeType);
    }

    @Override
    public boolean research(TechType techType) {
        return this.researcher.research(techType);
    }

    @Override
    public boolean upgrade(UpgradeType upgradeType) {
        return this.researcher.upgrade(upgradeType);
    }
}
