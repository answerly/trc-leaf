package com.olasharing.trc.leaf.client;

import com.olasharing.trc.leaf.BizEnums;
import com.olasharing.trc.leaf.LeafProperties;
import com.olasharing.trc.leaf.SequenceRepository;
import com.olasharing.trc.leaf.repository.builder.RepositoryFactory;
import com.olasharing.trc.leaf.repository.builder.RepositoryProperties;
import junit.framework.TestCase;

/**
 * LeafClientTest
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class BatchLeafClientTest extends TestCase {

    public void testNext() {
        RepositoryProperties properties = new RepositoryProperties();
        properties.setName("local_test");
        properties.setType("local");
        SequenceRepository sequenceRepository = RepositoryFactory.build(properties);

        LeafProperties leafProperties = new LeafProperties();
        leafProperties.setCode(BizEnums.DRIVER.getBizCode());
        leafProperties.setRepository("local_test");


        LeafClient leafClient = new BatchLeafClient(BizEnums.DRIVER, sequenceRepository, 2);
        long previous = 0;
        for (int i = 0; i < 10000; i++) {
            long current = leafClient.next();
            assertTrue("current:" + current + ", previous:" + previous, current > previous);
            previous = current;
        }
    }
}
