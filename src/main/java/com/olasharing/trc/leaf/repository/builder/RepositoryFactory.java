package com.olasharing.trc.leaf.repository.builder;

import com.olasharing.trc.leaf.SequenceRepository;

/**
 * RepositoryFactory
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class RepositoryFactory {

    public static SequenceRepository build(RepositoryProperties properties) {
        if ("local".equalsIgnoreCase(properties.getType())) {
            return new LocalRepositoryBuilder().build(properties);
        }
        if ("http".equalsIgnoreCase(properties.getType())) {
            return new HttpRepositoryBuilder().build(properties);
        }
        if ("redis".equalsIgnoreCase(properties.getType())) {
            return new RedisRepositoryBuilder().build(properties);
        }
        return null;
    }
}
