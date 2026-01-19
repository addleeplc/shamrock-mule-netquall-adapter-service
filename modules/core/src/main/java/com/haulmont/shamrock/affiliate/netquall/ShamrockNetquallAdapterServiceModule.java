package com.haulmont.shamrock.affiliate.netquall;

import com.haulmont.monaco.annotations.Module;
import com.haulmont.shamrock.affiliate.integrations.core.AbstractIntegrationModule;

@Module(
        name = "shamrock-netquall-adapter-service-module",
        depends = {
                "monaco-jetty",
                "monaco-core",
                "monaco-config",
                "monaco-graylog-reporter",
                "monaco-sentry-reporter",
                "monaco-ds", "monaco-ds-postgresql", "monaco-metrics", "monaco-model", "monaco-mq", "monaco-rabbit-mq", "monaco-redis", "monaco-rs", "monaco-unirest"
        }
)
public class ShamrockNetquallAdapterServiceModule extends AbstractIntegrationModule {

    public ShamrockNetquallAdapterServiceModule() {
        super();
        packages(ShamrockNetquallAdapterServiceModule.class.getPackageName());
        //packages("com.haulmont.shamrock.affiliate");
    }
}
