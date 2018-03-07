/*
 * Copyright (c) 2018 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.browser

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.KeyEvent


class KeyboardAwareLayout : ConstraintLayout {

    var listener: OnKeyboardStateChangeListener? = null

    private var keyboardShown = false

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun dispatchKeyEventPreIme(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (keyboardShown) {
                keyboardShown = false
                listener?.onKeyboardHidden()
            }
        }

        return super.dispatchKeyEventPreIme(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val proposedHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (height > proposedHeight) {
            // keyboard is showing
            if (!keyboardShown) {
                keyboardShown = true
                listener?.onKeyboardShown()
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    interface OnKeyboardStateChangeListener {
        fun onKeyboardShown()
        fun onKeyboardHidden()
    }
}