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

package com.duckduckgo.app.tabs

import android.app.ActivityManager
import android.content.Context
import com.duckduckgo.app.home.HomeActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TabManager @Inject constructor(context: Context, private val repository: TabDataRepository) {

    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    val tabs: Tabs
        get() {
            val current: Int? = null
            val tabs = ArrayList<Tab>()
            for (task in tabTasks) {
                val data = repository.get(task.tabId).value
                tabs.add(Tab(data?.url, data?.title))
            }
            return Tabs(current, tabs)
        }

    private val tabTasks
        get() = activityManager.appTasks
            .filter {
                it.baseIntentActivity == HomeActivity::class.java.name
            }.sortedBy {
                it.taskInfo.persistentId
            }

    fun selectTab(position: Int) {
        tabTasks[position].moveToFront()
    }

    val ActivityManager.AppTask.baseIntentActivity: String get() = taskInfo.baseIntent.component.className

    val ActivityManager.AppTask.tabId: String get() = taskInfo.baseIntent.tabId

    data class Tabs(var current: Int?, var list: List<Tab>)

    data class Tab(val url: String?, val title: String?)
}
