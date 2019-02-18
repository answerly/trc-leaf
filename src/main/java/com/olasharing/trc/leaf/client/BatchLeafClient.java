package com.olasharing.trc.leaf.client;

import com.olasharing.trc.leaf.BizEnums;
import com.olasharing.trc.leaf.SequenceRepository;

/**
 * batch leaf client
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class BatchLeafClient extends LeafClient {

    private int delta;
    private volatile long lastTimestamp = 0;
    private volatile long use = 0;
    private volatile long sequence = 0;

    public BatchLeafClient(BizEnums bizEnum, SequenceRepository sequenceRepository, int delta) {
        super(bizEnum, sequenceRepository);
        this.delta = delta;
    }

    @Override
    protected synchronized long nextSequence(String bizCode, long timestamp) {
        long result = 0L;
        if (timestamp > this.lastTimestamp) {
            reset(bizCode, timestamp);
            this.lastTimestamp = timestamp;
        }
        if (this.use < this.delta) {
            result = this.sequence + this.use++;
        }
        if (this.use >= this.delta) {
            reset(bizCode, timestamp);
        }
        return result;
    }

    private void reset(String bizCode, long timestamp) {
        long tmpSequence = this.sequenceRepository.next(bizCode, timestamp, this.delta);
        this.use = 0;
        this.sequence = tmpSequence - delta;
    }
}
