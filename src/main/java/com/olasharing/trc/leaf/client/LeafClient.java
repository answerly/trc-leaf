package com.olasharing.trc.leaf.client;

import com.olasharing.trc.leaf.BizEnums;
import com.olasharing.trc.leaf.SequenceRepository;

import java.util.concurrent.TimeUnit;

/**
 * leaf generator
 *
 * @author stone
 */
public class LeafClient {

    protected BizEnums bizEnum;

    protected SequenceRepository sequenceRepository;

    public LeafClient(BizEnums bizEnum, SequenceRepository sequenceRepository) {
        this.bizEnum = bizEnum;
        this.sequenceRepository = sequenceRepository;
    }

    static long getTimestampFromEpoch() {
        long timestamp = System.currentTimeMillis() - Constants.EPOCH_MILLIS;
        return TimeUnit.MILLISECONDS.toHours(timestamp);
    }

    /**
     * next leaf(id or no)
     *
     * @return
     */
    public long next() {
        // 21 bits for timestamp, 0-2097151, unit 2097151/24/365 year
        long timestamp = getTimestampFromEpoch();
        // 4 bits for version, 0-15
        long version = 0;
        // 6 bits for biz_no, 0-63
        long bizNo = this.bizEnum.getBizNo();
        // 32 bit for sequence, 0-4294967295, (4294967295/60/60)concurrent for seconds
        long sequence = nextSequence(this.bizEnum.getBizCode(), timestamp);
        sequence = (sequence + 1) & Constants.SEQUENCE_MASK;
        return timestamp << Constants.TIMESTAMP_SHIFT
                | version << Constants.VERSION_SHIFT
                | bizNo << Constants.BIZ_NO_SHIFT
                | sequence
                ;
    }

    protected long nextSequence(String bizCode, long timestamp) {
        int delta = 1;
        return this.sequenceRepository.next(bizCode, timestamp, delta);
    }

}
