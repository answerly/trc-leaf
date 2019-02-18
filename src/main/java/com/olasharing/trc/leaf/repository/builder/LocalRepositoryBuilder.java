package com.olasharing.trc.leaf.repository.builder;

import com.olasharing.trc.leaf.SequenceRepository;
import com.olasharing.trc.leaf.repository.LocalSequenceRepository;

/**
 * local builder
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class LocalRepositoryBuilder {

    public SequenceRepository build(RepositoryProperties repositoryProperties) {
        return new LocalSequenceRepository();
    }
}
