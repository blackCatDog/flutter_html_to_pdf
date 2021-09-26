package com.afur.flutterhtmltopdf

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterHtmlToPdfPlugin(private val registrar: Registrar) : MethodCallHandler {

    @Override
    fun onAttachedToEngine(binding: FlutterPluginBinding) {
        onAttachedToEngine(binding.getApplicationContext(), binding.getBinaryMessenger())
    }

    private fun onAttachedToEngine(applicationContext: Context, messenger: BinaryMessenger) {
        context = applicationContext
        methodChannel = MethodChannel(messenger, "flutter_html_to_pdf")
        methodChannel.setMethodCallHandler(this)
    }

    @Override
    fun onDetachedFromEngine(binding: FlutterPluginBinding?) {
        context = null
        methodChannel.setMethodCallHandler(null)
        methodChannel = null
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val instance: com.example.fluttershare.FlutterSharePlugin = com.example.fluttershare.FlutterSharePlugin()
            instance.onAttachedToEngine(registrar.context(), registrar.messenger())
//            val channel = MethodChannel(registrar.messenger(), "flutter_share")
//            channel.setMethodCallHandler(FlutterSharePlugin(registrar))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "convertHtmlToPdf") {
            convertHtmlToPdf(call, result)
        } else {
            result.notImplemented()
        }
    }

    private fun convertHtmlToPdf(call: MethodCall, result: Result) {
        val htmlFilePath = call.argument<String>("htmlFilePath")

        HtmlToPdfConverter().convert(htmlFilePath!!, registrar.activity(), object : HtmlToPdfConverter.Callback {
            override fun onSuccess(filePath: String) {
                result.success(filePath)
            }

            override fun onFailure() {
                result.error("ERROR", "Unable to convert html to pdf document!", "")
            }
        })
    }
}

