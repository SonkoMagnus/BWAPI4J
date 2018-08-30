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

#include "Callbacks.h"
#include "Logger.h"

void Callbacks::initialize(JNIEnv *env, jclass bwClass) {
  LOGGER("Initializing callbacks...");

  preFrameCallback = env->GetMethodID(bwClass, "preFrame", "()V");
  onStartCallback = env->GetMethodID(bwClass, "onStart", "()V");
  onEndCallback = env->GetMethodID(bwClass, "onEnd", "(Z)V");
  onFrameCallback = env->GetMethodID(bwClass, "onFrame", "()V");
  onSendTextCallback = env->GetMethodID(bwClass, "onSendText", "(Ljava/lang/String;)V");
  onReceiveTextCallback = env->GetMethodID(bwClass, "onReceiveText", "(ILjava/lang/String;)V");
  onPlayerLeftCallback = env->GetMethodID(bwClass, "onPlayerLeft", "(I)V");
  onNukeDetectCallback = env->GetMethodID(bwClass, "onNukeDetect", "(II)V");
  onUnitDiscoverCallback = env->GetMethodID(bwClass, "onUnitDiscover", "(I)V");
  onUnitEvadeCallback = env->GetMethodID(bwClass, "onUnitEvade", "(I)V");
  onUnitShowCallback = env->GetMethodID(bwClass, "onUnitShow", "(I)V");
  onUnitHideCallback = env->GetMethodID(bwClass, "onUnitHide", "(I)V");
  onUnitCreateCallback = env->GetMethodID(bwClass, "onUnitCreate", "(I)V");
  onUnitDestroyCallback = env->GetMethodID(bwClass, "onUnitDestroy", "(I)V");
  onUnitMorphCallback = env->GetMethodID(bwClass, "onUnitMorph", "(I)V");
  onUnitRenegadeCallback = env->GetMethodID(bwClass, "onUnitRenegade", "(I)V");
  onUnitCompleteCallback = env->GetMethodID(bwClass, "onUnitComplete", "(I)V");
  onSaveGameCallback = env->GetMethodID(bwClass, "onSaveGame", "(Ljava/lang/String;)V");

  LOGGER("Initializing callbacks... done");
}

void Callbacks::processEvents(JNIEnv *env, jobject bw, const std::list<BWAPI::Event> &events) {
  env->CallObjectMethod(bw, preFrameCallback);

  for (const auto &event : events) {
    switch (event.getType()) {
#ifdef OPENBW
      case BWAPI::EventType::MatchStart: {
        env->CallObjectMethod(bw, onStartCallback);
      } break;
#endif
      case BWAPI::EventType::MatchEnd: {
        env->CallObjectMethod(bw, onEndCallback, event.isWinner() ? JNI_TRUE : JNI_FALSE);
      } break;
#ifndef OPENBW
      case BWAPI::EventType::MatchFrame: {
        env->CallObjectMethod(bw, onFrameCallback);
      } break;
#endif
      case BWAPI::EventType::SendText: {
        jstring string = env->NewStringUTF(event.getText().c_str());
        env->CallObjectMethod(bw, onSendTextCallback, string);
        env->DeleteLocalRef(string);
      } break;
      case BWAPI::EventType::ReceiveText: {
        jstring string = env->NewStringUTF(event.getText().c_str());
        env->CallObjectMethod(bw, onReceiveTextCallback, event.getPlayer()->getID(), string);
        env->DeleteLocalRef(string);
      } break;
      case BWAPI::EventType::PlayerLeft: {
        env->CallObjectMethod(bw, onPlayerLeftCallback, event.getPlayer()->getID());
      } break;
      case BWAPI::EventType::NukeDetect: {
        env->CallObjectMethod(bw, onNukeDetectCallback, event.getPosition().x, event.getPosition().y);
      } break;
      case BWAPI::EventType::UnitDiscover: {
        env->CallObjectMethod(bw, onUnitDiscoverCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitEvade: {
        env->CallObjectMethod(bw, onUnitEvadeCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitShow: {
        env->CallObjectMethod(bw, onUnitShowCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitHide: {
        env->CallObjectMethod(bw, onUnitHideCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitCreate: {
        env->CallObjectMethod(bw, onUnitCreateCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitDestroy: {
        env->CallObjectMethod(bw, onUnitDestroyCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitMorph: {
        env->CallObjectMethod(bw, onUnitMorphCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::UnitRenegade: {
        env->CallObjectMethod(bw, onUnitRenegadeCallback, event.getUnit()->getID());
      } break;
      case BWAPI::EventType::SaveGame: {
        jstring string = env->NewStringUTF(event.getText().c_str());
        env->CallObjectMethod(bw, onSaveGameCallback, string);
        env->DeleteLocalRef(string);
      } break;
      case BWAPI::EventType::UnitComplete: {
        env->CallObjectMethod(bw, onUnitCompleteCallback, event.getUnit()->getID());
      } break;
    }
  }
}
