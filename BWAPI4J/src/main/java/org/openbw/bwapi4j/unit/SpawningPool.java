package org.openbw.bwapi4j.unit;

import org.openbw.bwapi4j.type.UnitType;
import org.openbw.bwapi4j.type.UpgradeType;

public class SpawningPool extends Building implements Organic, ResearchingFacility {

    private Researcher researcher;
    
    protected SpawningPool(int id, int timeSpotted) {
        
        super(id, UnitType.Zerg_Spawning_Pool, timeSpotted);
        this.researcher = new Researcher();
    }

    @Override
    public void update(int[] unitData, int index) {

        super.update(unitData, index);
        this.researcher.update(unitData, index);
    }
    
    public boolean upgradeMetabolicBoost() {
        
        return this.researcher.upgrade(UpgradeType.Metabolic_Boost);
    }
    
    public boolean upgradeAdrenalGlands() {
        
        return this.researcher.upgrade(UpgradeType.Adrenal_Glands);
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
}