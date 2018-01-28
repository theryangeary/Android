/*
 * Copyright (c) 2017 DuckDuckGo
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

package com.duckduckgo.app.privacymonitor.store


import android.arch.lifecycle.MutableLiveData
import com.duckduckgo.app.privacymonitor.PrivacyMonitor
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PrivacyMonitorRepository @Inject constructor() {

    private val privacyMonitors: LinkedHashMap<String, MutableLiveData<PrivacyMonitor>> = LinkedHashMap()

    fun add(key: String, monitor: MutableLiveData<PrivacyMonitor>) {
        privacyMonitors[key] = monitor
    }

    fun get(key: String): MutableLiveData<PrivacyMonitor> {
        return privacyMonitors[key]!!
    }

}
