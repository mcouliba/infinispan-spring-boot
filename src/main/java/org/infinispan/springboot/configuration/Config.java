package org.infinispan.springboot.configuration;

import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.NearCacheConfiguration;
import org.infinispan.client.hotrod.configuration.NearCacheMode;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by mcouliba on 30/08/2018.
 */
@Configuration
public class Config {

    @Bean
    public org.infinispan.client.hotrod.configuration.Configuration customConfiguration() throws IOException {
        Properties hotrodProps = new Properties();
        hotrodProps.load(Thread.currentThread().getContextClassLoader().getResource("hotrod-client-custom.properties").openStream());

        NearCacheMode nearCacheMode = NearCacheMode.DISABLED; // NearCache disabled by default
        int nearCacheMaxEntries = 100; // By default

        // Get NearCache configuration
        if ("true".equals(hotrodProps.getProperty("infinispan.client.hotrod.nearcache"))) {
            nearCacheMode = NearCacheMode.INVALIDATED;
        }

        if (hotrodProps.getProperty("infinispan.client.hotrod.nearcache.maxentries") != null) {
            nearCacheMaxEntries = Integer.valueOf(hotrodProps.getProperty("infinispan.client.hotrod.nearcache.maxentries"));
        }

        NearCacheConfiguration ncc = new NearCacheConfiguration(nearCacheMode, nearCacheMaxEntries);

        // HotRod ConfigurationBuilder.
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.withProperties(hotrodProps);

        cb.nearCache().read(ncc);

        // Make sure to register the ProtoStreamMarshaller.
        cb.marshaller(new ProtoStreamMarshaller());

        return cb.build();
    }
}
