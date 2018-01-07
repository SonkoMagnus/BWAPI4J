package org.openbw.bwapi4j.unit;

import org.openbw.bwapi4j.type.TechType;
import org.openbw.bwapi4j.type.UnitType;
import org.openbw.bwapi4j.type.UpgradeType;

public class HydraliskDen extends Building implements Organic, ResearchingFacility {

    private Researcher researcher;
    
    protected HydraliskDen(int id, int timeSpotted) {
        
        super(id, UnitType.Zerg_Hydralisk_Den, timeSpotted);
        this.researcher = new Researcher();
    }

    @Override
    public void update(int[] unitData, int index, int frame) {

        this.researcher.update(unitData, index);
        super.update(unitData, index, frame);
    }
    
    public boolean upgradeMuscularAugments() {
        
        return this.researcher.upgrade(UpgradeType.Muscular_Augments);
    }
    
    public boolean upgradeGroovedSpines() {
        
        return this.researcher.upgrade(UpgradeType.Grooved_Spines);
    }
    
    public boolean researchLurkerAspect() {
        
        return this.researcher.research(TechType.Lurker_Aspect);
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
