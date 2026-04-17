package com.po4yka.industrialdesign.demo

import android.app.Activity
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView

/**
 * Host activity for the Compose Multiplatform gallery. Uses a plain
 * [Activity] + [ComposeView] so the demo module stays a pure `androidLibrary`
 * and does not depend on `androidx.activity.compose`.
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = ComposeView(this).apply {
            setContent { DemoApp() }
        }
        setContentView(root)
    }
}
