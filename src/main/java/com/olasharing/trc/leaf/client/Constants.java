package com.olasharing.trc.leaf.client;

/**
 * all constants
 *
 * @author stone
 */
class Constants {

    /**
     * mills from 20181216
     */
    public static final long EPOCH_MILLIS = 1544889600000L;

    public static final long SEQUENCE_MASK = 4294967295L;

    public static final int SEQUENCE_BITS = 32;


    public static final int BIZ_NO_SHIFT = SEQUENCE_BITS;


    public static final int VERSION_SHIFT = BIZ_NO_SHIFT + 6;


    public static final int TIMESTAMP_SHIFT = VERSION_SHIFT + 4;
}
