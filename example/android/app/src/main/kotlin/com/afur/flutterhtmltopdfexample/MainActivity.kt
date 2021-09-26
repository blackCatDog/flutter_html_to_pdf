package com.afur.flutterhtmltopdfexample

import android.os.Bundle

import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
  }
}
