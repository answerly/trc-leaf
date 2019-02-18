package com.olasharing.trc.leaf;

/**
 * sequence manager
 *
 * @author stone
 */
public interface SequenceRepository {

    /**
     * next batch sequence
     *
     * @param bizCode
     * @param timestamp
     * @param delta
     * @return
     */
    long next(String bizCode, long timestamp, int delta);
}
