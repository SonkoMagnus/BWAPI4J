////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (C) 2017-2018 OpenBW Team
//
//    This file is part of BWAPI4J.
//
//    BWAPI4J is free software: you can redistribute it and/or modify
//    it under the terms of the Lesser GNU General Public License as published
//    by the Free Software Foundation, version 3 only.
//
//    BWAPI4J is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with BWAPI4J.  If not, see <http://www.gnu.org/licenses/>.
//
////////////////////////////////////////////////////////////////////////////////

package org.openbw.bwapi4j.unit;

import static org.openbw.bwapi4j.type.UnitCommandType.Cancel_Morph;

import org.openbw.bwapi4j.type.UnitType;

public class Egg extends PlayerUnitImpl implements Organic {
  protected Egg(int id) {
    super(id, UnitType.Zerg_Egg);
  }

  protected Egg(int id, UnitType unitType) {
    super(id, unitType);
  }

  public boolean cancelMorph() {
    return issueCommand(this.iD, Cancel_Morph, -1, -1, -1, -1);
  }

  public UnitType getBuildType() {
    return buildType;
  }

  public int getRemainingMorphTime() {
    return remainingBuildTime;
  }
}
