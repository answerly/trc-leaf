package com.olasharing.trc.leaf.spring;

import com.olasharing.trc.leaf.client.LeafClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * leaf client manager
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class LeafClientManager implements InitializingBean, DisposableBean {

    private Map<String, LeafClient> leafClients;


    public LeafClientManager(Map<String, LeafClient> leafClients) {
        this.leafClients = leafClients;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public long next(String bizCode) {
        return getClient(bizCode).next();
    }

    public String nextToString(String bizCode) {
        return Long.toString(next(bizCode));
    }

    public LeafClient getClient(String bizCode) {
        return this.leafClients.get(bizCode);
    }
}
