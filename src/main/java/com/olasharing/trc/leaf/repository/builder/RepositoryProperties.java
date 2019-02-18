package com.olasharing.trc.leaf.repository.builder;

import java.util.Map;

/**
 * 仓库属性
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class RepositoryProperties {

    private String name;

    /**
     * http redis local
     */
    private String type;

    private Map<String, String> config;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
