package com.olasharing.trc.leaf;

import com.olasharing.trc.leaf.client.BatchLeafClient;

/**
 * leaf props
 *
 * @author liuyan
 * @date 2018-12-19
 */
public class LeafProperties {

    /**
     * @see com.olasharing.trc.leaf.BizEnums
     */
    private String code;

    /**
     * @see com.olasharing.trc.leaf.SequenceRepository
     */
    private String repository;

    /**
     * @see BatchLeafClient
     */
    private Integer batch = 1;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }
}
