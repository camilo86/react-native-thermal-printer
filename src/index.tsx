import { NativeModules } from 'react-native';

type ThermalPrinterType = {
  print(macAddress: string, text: string): Promise<string>;
};

const { ThermalPrinter } = NativeModules;

console.log(ThermalPrinter);

export default ThermalPrinter as ThermalPrinterType;
