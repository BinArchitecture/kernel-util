package com.lppz.util.curator.enums;

/**
 * 当前zk的配制参数信息
* 源文件名：ZkParamCfg.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年9月17日
* 修改作者：liujun
* 修改日期：2016年9月17日
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public enum ZkParamCfg {

    /**
     * zk是否启用标识
    * @字段说明 ZK_CFG_OPEN
    */
    ZK_CFG_FLAG("loadZk"),

    /**
     * zk配制的url地址信息
    * @字段说明 ZK_CFG_URL
    */
    ZK_CFG_URL("dubbo.registry.address"),

    /**
     * 集群的id
    * @字段说明 ZK_CFG_CLUSTERID
    */
    ZK_CFG_CLUSTERID("clusterId"),

    /**
     * 集群nodeid
    * @字段说明 ZK_CFG_NODEID
    */
    ZK_CFG_NODEID("nodeId"),

    /**
     * 集群中所有节点的名称信息
    * @字段说明 ZK_CFG_CLUSTER_NODES
    */
    ZK_CFG_CLUSTER_NODES("clusterNodes"),

    /**
     * 集群中所有节点的名称信息的分隔符
    * @字段说明 ZK_CFG_CLUSTER_NODES
    */
    ZK_CFG_CLUSTER_NODES_SEPARATE(","),

    ;

    ZkParamCfg(String key) {
        this.key = key;
    }

    private String key;

    public String getKey() {
        return key;
    }
}
