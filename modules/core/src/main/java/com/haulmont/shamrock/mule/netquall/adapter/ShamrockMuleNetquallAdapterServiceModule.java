package com.haulmont.shamrock.mule.netquall.adapter;

import com.haulmont.monaco.annotations.Module;
import com.haulmont.monaco.container.ModuleLoader;

@Module(
        name = "shamrock-mule-netquall-adapter-service-module",
        depends = {
                "monaco-jetty",
                "monaco-core",
                "monaco-config",
                "monaco-graylog-reporter",
                "monaco-sentry-reporter",
                "monaco-ds", "monaco-ds-postgresql", "monaco-metrics", "monaco-model", "monaco-mq", "monaco-rabbit-mq", "monaco-redis", "monaco-rs", "monaco-unirest"
        }
)
public class ShamrockMuleNetquallAdapterServiceModule extends ModuleLoader {

    public ShamrockMuleNetquallAdapterServiceModule () {
        super();
        packages(ShamrockMuleNetquallAdapterServiceModule.class.getPackageName());
    }
}
