package com.olasharing.trc.leaf.repository.builder;

import com.olasharing.trc.leaf.SequenceRepository;
import com.olasharing.trc.leaf.repository.HttpSequenceRepository;

/**
 * http builder
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class HttpRepositoryBuilder {

    public SequenceRepository build(RepositoryProperties repositoryProperties) {
        return new HttpSequenceRepository();
    }
}
