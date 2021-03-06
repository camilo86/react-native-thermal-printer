package com.reactnativethermalprinter

import android.bluetooth.BluetoothAdapter
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import java.nio.charset.Charset
import java.util.*

class ThermalPrinterModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    val PrinterUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    override fun getName(): String {
        return "ThermalPrinter"
    }


    @ReactMethod
    fun print(macAddress: String, text: String, promise: Promise) {
      if (bluetoothAdapter == null) {
        promise.reject(Error("Bluetooth not supported"))
        return
      }

      val printer = bluetoothAdapter.bondedDevices.find { x -> x.address == macAddress }

      if (printer == null) {
        promise.reject(Error("Printer/Services not found "))
        return
      }

      val socket = printer.createRfcommSocketToServiceRecord(PrinterUUID)
      socket.connect()

      socket.outputStream.write(byteArrayOf(0x1B, 0x40))
      socket.outputStream.write(byteArrayOf(0x1B, 0x21, 0x0))
      socket.outputStream.write(byteArrayOf(0x1B, 0x61, 0x0))
      socket.outputStream.write(text.toByteArray())
      socket.outputStream.write(0x0A)
      socket.outputStream.write(byteArrayOf(0x1B, 0x21, 0x20))
      socket.outputStream.write(byteArrayOf(0x1B, 0x61, 0x1))
      socket.outputStream.write(text.toByteArray())
      socket.outputStream.write(0x0A)

      socket.close()

      promise.resolve("Printed: \"${text}\"")
    }
}
