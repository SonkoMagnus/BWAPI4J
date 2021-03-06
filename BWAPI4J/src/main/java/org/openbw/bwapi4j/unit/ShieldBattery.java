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

import static org.openbw.bwapi4j.type.UnitCommandType.Right_Click_Unit;

import org.openbw.bwapi4j.type.UnitType;

public class ShieldBattery extends BuildingImpl implements Mechanical {

  protected ShieldBattery(UnitType unitType, int timeSpotted) {
    super(unitType, timeSpotted);
  }

  public int getEnergy() {
    return this.energy;
  }

  @Override
  public int getMaxEnergy() {
    return super.getMaxEnergy();
  }

  /**
   * Workaround: the recharge command actually performs a right click command of the target unit on
   * the ShieldBattery. This is because BWAPI does not support a dedicated recharge command.
   *
   * @param target unit to be recharged
   * @param queued true if command is queued, false else
   * @return true if successful, false else
   */
  public boolean recharge(Unit target, boolean queued) {
    return issueCommand(target.getId(), Right_Click_Unit, this.getId(), -1, -1, queued ? 1 : 0);
  }
}
