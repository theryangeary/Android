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

package com.duckduckgo.app.privacymonitor

import android.net.Uri
import com.duckduckgo.app.privacymonitor.model.HttpsStatus
import com.duckduckgo.app.privacymonitor.model.TermsOfService
import com.duckduckgo.app.trackerdetection.model.TrackerNetwork
import com.duckduckgo.app.trackerdetection.model.TrackingEvent

class HomeMonitor : PrivacyMonitor {

    override val url = "http://duckduckgo.com"
    override val uri: Uri? = null
    override var title: String? = "DuckDuckGo"
    override val https = HttpsStatus.SECURE
    override val termsOfService: TermsOfService = TermsOfService(classification = "A")
    override val memberNetwork: TrackerNetwork? = null
    override val trackingEvents = ArrayList<TrackingEvent>()
    override val trackerCount = 0
    override val distinctTrackersByNetwork = HashMap<String, List<TrackingEvent>>()
    override val networkCount = 0
    override val majorNetworkCount = 0
    override val hasTrackerFromMajorNetwork = false
    override val allTrackersBlocked = true
    override val hasObscureTracker = false
    override var hasHttpResources = false
    override fun trackerDetected(event: TrackingEvent) {}

}
