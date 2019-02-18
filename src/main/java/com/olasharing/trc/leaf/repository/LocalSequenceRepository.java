package com.olasharing.trc.leaf.repository;

import com.olasharing.trc.leaf.SequenceRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * impl use atomic, just for test
 *
 * @author stone
 */
public class LocalSequenceRepository implements SequenceRepository {

    private ConcurrentMap<String, AtomicLong> INCREMENTS = new ConcurrentHashMap<>();

    @Override
    public long next(String bizCode, long timestamp, int delta) {
        String uniqueKey = bizCode + timestamp;
        AtomicLong index = INCREMENTS.get(uniqueKey);
        if (index == null) {
            index = new AtomicLong(0);
            AtomicLong previous = INCREMENTS.putIfAbsent(uniqueKey, index);
            if (previous != null) {
                index = previous;
            }
        }
        return index.addAndGet(delta);
    }
}
