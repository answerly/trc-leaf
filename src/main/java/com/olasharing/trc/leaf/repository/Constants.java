package com.olasharing.trc.leaf.repository;

/**
 * Constants for repository
 *
 * @author stone
 */
class Constants {

    /**
     * redis key prefix of leaf sequence
     */
    public static final String REDIS_KEY_PREFIX = "trc:leaf:duration:";

    /**
     * expire hours for leaf sequence,
     * one hour expire
     * one hour buffer
     */
    public static final long REDIS_KEY_EXPIRE_HOURS = 2L;
}
