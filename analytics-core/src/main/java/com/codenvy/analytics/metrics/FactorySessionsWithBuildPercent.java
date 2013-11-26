/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.analytics.metrics;

import com.codenvy.analytics.datamodel.DoubleValueData;
import com.codenvy.analytics.datamodel.LongValueData;
import com.codenvy.analytics.datamodel.ValueData;

import java.io.IOException;
import java.util.Map;

/** @author <a href="mailto:abazko@codenvy.com">Anatoliy Bazko</a> */
public class FactorySessionsWithBuildPercent extends CalculatedMetric {

    public FactorySessionsWithBuildPercent() {
        super(MetricType.FACTORY_SESSIONS_WITH_BUILD_PERCENT, new MetricType[]{MetricType.FACTORY_SESSIONS,
                                                                               MetricType.FACTORY_SESSIONS_WITH_BUILD});
    }

    @Override
    public ValueData getValue(Map<String, String> context) throws IOException {
        LongValueData total = (LongValueData)basedMetric[0].getValue(context);
        LongValueData number = (LongValueData)basedMetric[1].getValue(context);
        return new DoubleValueData(100D * number.getAsLong() / total.getAsLong());
    }

    @Override
    public Class<? extends ValueData> getValueDataClass() {
        return DoubleValueData.class;
    }

    @Override
    public String getDescription() {
        return "The percent of sessions where user built an application";
    }
}
