package com.ts.clerk.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "clerk-common.enabled", havingValue = "true", matchIfMissing = false)
@ComponentScan
public class ClerkCommonAutoConfiguration {
}
