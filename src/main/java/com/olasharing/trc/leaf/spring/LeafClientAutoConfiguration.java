package com.olasharing.trc.leaf.spring;

import com.google.common.collect.Maps;
import com.olasharing.trc.leaf.BizEnums;
import com.olasharing.trc.leaf.LeafProperties;
import com.olasharing.trc.leaf.SequenceRepository;
import com.olasharing.trc.leaf.client.BatchLeafClient;
import com.olasharing.trc.leaf.client.LeafClient;
import com.olasharing.trc.leaf.repository.builder.RepositoryFactory;
import com.olasharing.trc.leaf.repository.builder.RepositoryProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * leaf 自动启动
 *
 * @author liuyan
 * @date 2018-12-19
 */

@Configuration
@EnableConfigurationProperties({LeafClientProperties.class})
public class LeafClientAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeafClientAutoConfiguration.class);

    @Bean
    public LeafClientManager leafClientManager(LeafClientProperties properties) {
        if (CollectionUtils.isEmpty(properties.getLeafs())) {
            throw new NullPointerException("leaf.leafs is empty");
        }
        if (CollectionUtils.isEmpty(properties.getRepositories())) {
            throw new NullPointerException("leaf.repositories is empty");
        }
        Map<String, LeafClient> leafClients = Maps.newHashMap();
        Map<String, SequenceRepository> repositories = createSequenceRepositories(properties.getRepositories());

        for (LeafProperties leafProperties : properties.getLeafs()) {
            String bizCode = leafProperties.getCode();
            String repositoryName = leafProperties.getRepository();
            BizEnums bizEnum = BizEnums.getBizByCode(bizCode);
            SequenceRepository repository = repositories.get(repositoryName);
            if (bizEnum == null) {
                LOGGER.warn("not found bizEnum:{}", leafProperties);
                continue;
            }
            if (repository == null) {
                LOGGER.warn("not found repository:{}", leafProperties);
                continue;
            }
            LOGGER.info("build leaf:{}", leafProperties);
            LeafClient leafClient = createLeafClient(leafProperties, repository);
            leafClients.put(bizCode, leafClient);
        }
        return new LeafClientManager(leafClients);
    }

    private Map<String, SequenceRepository> createSequenceRepositories(List<RepositoryProperties> propertiesList) {
        Map<String, SequenceRepository> repositories = Maps.newHashMap();
        for (RepositoryProperties properties : propertiesList) {
            SequenceRepository repository = RepositoryFactory.build(properties);
            if (repository != null) {
                repositories.put(properties.getName(), repository);
            }
            if (repository == null) {
                LOGGER.warn("not found repository:{}", properties);
            }
        }
        return repositories;
    }

    private LeafClient createLeafClient(LeafProperties leafProperties, SequenceRepository repository) {
        String bizCode = leafProperties.getCode();
        BizEnums bizEnum = BizEnums.getBizByCode(bizCode);
        if (leafProperties.getBatch() <= 1) {
            return new LeafClient(bizEnum, repository);
        }
        return new BatchLeafClient(bizEnum, repository, leafProperties.getBatch());
    }
}
