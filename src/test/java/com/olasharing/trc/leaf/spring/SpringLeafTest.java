package com.olasharing.trc.leaf.spring;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO desc file
 *
 * @author liuyan
 * @date 2018-12-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class SpringLeafTest {

    @Autowired
    private LeafClientManager leafClientManager;

    @Test
    public void testLeafClient() {
        String bizCode = "DRIVER";
        long previous = 0L;
        for (int i = 0; i < 10; i++) {
            long current = leafClientManager.next(bizCode);
            TestCase.assertTrue("current:" + current + ", previous:" + previous, current > previous);
            previous = current;
        }
    }
}
