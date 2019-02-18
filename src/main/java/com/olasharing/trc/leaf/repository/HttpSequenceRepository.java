package com.olasharing.trc.leaf.repository;

import com.olasharing.trc.leaf.SequenceRepository;

/**
 * http invoke server
 *
 * @author stone
 */
public class HttpSequenceRepository implements SequenceRepository {

    @Override
    public long next(String bizCode, long timestamp, int delta) {
        throw new UnsupportedOperationException();
    }
}
