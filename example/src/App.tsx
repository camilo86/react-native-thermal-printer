import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import ThermalPrinter from 'react-native-thermal-printer';

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();

  const handlePrintHello = () => {
    ThermalPrinter.print('00:11:22:33:44:55', 'Hello World!')
      .then((x) => {
        setResult(x);
        console.log(x);
      })
      .catch(console.error);
  };

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Button title="Print: Hello World" onPress={handlePrintHello} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
