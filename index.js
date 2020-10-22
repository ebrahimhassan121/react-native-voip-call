'use strict';

import { NativeModules, DeviceEventEmitter, Platform } from 'react-native';

import RNVoipPushKitNativeModule from './lib/iosPushKit';
import RNVoipCallNativeModule from './lib/RNVoipCall';
import RNVoipCallLisnersNativeModule from "./lib/listner";

export const RNVoipPushKit = RNVoipPushKitNativeModule;
export const RNVoipListners=RNVoipCallLisnersNativeModule;
export default RNVoipCallNativeModule;