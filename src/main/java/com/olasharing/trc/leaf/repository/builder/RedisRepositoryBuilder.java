package com.olasharing.trc.leaf.repository.builder;

import com.olasharing.trc.leaf.SequenceRepository;
import com.olasharing.trc.leaf.repository.RedisSequenceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Map;

/**
 * Redis builder
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class RedisRepositoryBuilder {

    public SequenceRepository build(RepositoryProperties repositoryProperties) {
        RedisProperties redisProperties = new RedisProperties();
        if (CollectionUtils.isEmpty(repositoryProperties.getConfig())) {
            throw new NullPointerException("redis repository config is null");
        }
        Map<String, String> configs = repositoryProperties.getConfig();
        if (StringUtils.isNotBlank(configs.get("port"))) {
            redisProperties.setPort(new Integer(configs.get("port")));
        }
        if (StringUtils.isNotBlank(configs.get("database"))) {
            redisProperties.setDatabase(new Integer(configs.get("database")));
        }
        if (StringUtils.isNotBlank(configs.get("host"))) {
            redisProperties.setHost(configs.get("host"));
        }
        if (StringUtils.isNotBlank(configs.get("password"))) {
            redisProperties.setPassword(configs.get("password"));
        }
        if (StringUtils.isNotBlank(configs.get("timeout"))) {
            redisProperties.setTimeout(Duration.ofMillis(new Long(configs.get("timeout"))));
        }
        JedisConnectionFactory connectionFactory = createJedisConnectionFactory(redisProperties);
        return new RedisSequenceRepository(connectionFactory);
    }

    protected JedisConnectionFactory createJedisConnectionFactory(RedisProperties properties) {
        JedisClientConfiguration clientConfiguration = getJedisClientConfiguration(properties);
        RedisStandaloneConfiguration standaloneConfiguration = getStandaloneConfig(properties);
        return new JedisConnectionFactory(standaloneConfiguration, clientConfiguration);
    }

    private RedisStandaloneConfiguration getStandaloneConfig(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        config.setPassword(RedisPassword.of(properties.getPassword()));
        config.setDatabase(properties.getDatabase());
        return config;
    }

    private JedisClientConfiguration getJedisClientConfiguration(RedisProperties properties) {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = applyProperties(
                JedisClientConfiguration.builder(), properties);
        return builder.build();
    }

    private JedisClientConfiguration.JedisClientConfigurationBuilder applyProperties(
            JedisClientConfiguration.JedisClientConfigurationBuilder builder, RedisProperties properties) {
        if (properties.getTimeout() != null) {
            Duration timeout = properties.getTimeout();
            builder.readTimeout(timeout).connectTimeout(timeout);
        }
        return builder;
    }

    protected class RedisProperties {

        /**
         * Database index used by the connection factory.
         */
        private int database = 0;

        /**
         * Connection URL. Overrides host, port, and password. User is ignored. Example:
         * redis://user:password@example.com:6379
         */
        private String url;

        /**
         * Redis server host.
         */
        private String host = "localhost";

        /**
         * Login password of the redis server.
         */
        private String password;

        /**
         * Redis server port.
         */
        private int port = 6379;

        private Duration timeout;
        
        public int getDatabase() {
            return database;
        }

        public void setDatabase(int database) {
            this.database = database;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public Duration getTimeout() {
            return timeout;
        }

        public void setTimeout(Duration timeout) {
            this.timeout = timeout;
        }
    }

}
