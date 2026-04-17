package com.po4yka.industrialdesign.demo

import androidx.compose.ui.window.ComposeUIViewController

/**
 * iOS entry point. Wrap this UIViewController in your Xcode app target:
 * ```swift
 * struct ContentView: UIViewControllerRepresentable {
 *     func makeUIViewController(context: Context) -> UIViewController {
 *         MainViewControllerKt.MainViewController()
 *     }
 * }
 * ```
 */
fun MainViewController() = ComposeUIViewController { DemoApp() }
