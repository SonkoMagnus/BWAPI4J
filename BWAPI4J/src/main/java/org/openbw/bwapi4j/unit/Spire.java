package org.openbw.bwapi4j.unit;

import org.openbw.bwapi4j.type.TechType;
import org.openbw.bwapi4j.type.UnitCommandType;
import org.openbw.bwapi4j.type.UnitType;
import org.openbw.bwapi4j.type.UpgradeType;

public class Spire extends Building implements Organic, ResearchingFacility {

    private Researcher researcher;
    
    protected Spire(int id, int timeSpotted) {
        
        super(id, UnitType.Zerg_Spire, timeSpotted);
        this.researcher = new Researcher();
    }

    @Override
    public void update(int[] unitData, int index, int frame) {

        super.update(unitData, index, frame);
        this.researcher.update(unitData, index);
    }
    
    public boolean upgradeFlyerAttacks() {
        
        return this.researcher.upgrade(UpgradeType.Zerg_Flyer_Attacks);
    }
    
    public boolean upgradeFlyerCarapace() {
        
        return this.researcher.upgrade(UpgradeType.Zerg_Flyer_Carapace);
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
    
    public boolean morph() {
        
        return issueCommand(this.id, UnitCommandType.Morph.ordinal(), -1, -1, -1, UnitType.Zerg_Greater_Spire.getId());
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
