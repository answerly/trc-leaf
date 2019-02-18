package com.olasharing.trc.leaf.spring;

import com.olasharing.trc.leaf.LeafProperties;
import com.olasharing.trc.leaf.repository.builder.RepositoryProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * leaf client auto config props
 *
 * @author liuyan
 * @date 2018-12-19
 */
@ConfigurationProperties(prefix = "spring.leaf")
public class LeafClientProperties {

    private List<LeafProperties> leafs;

    private List<RepositoryProperties> repositories;

    public List<LeafProperties> getLeafs() {
        return leafs;
    }

    public void setLeafs(List<LeafProperties> leafs) {
        this.leafs = leafs;
    }

    public List<RepositoryProperties> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepositoryProperties> repositories) {
        this.repositories = repositories;
    }
}
