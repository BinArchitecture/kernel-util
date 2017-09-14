package com.lppz.util.curator;

import java.net.InetAddress;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lppz.util.curator.listener.ZookeeperProcessListen;

public class CuratorFrameworkUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CuratorFrameworkUtils.class);
	
	public static CuratorFramework buildConnection(String url) {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(
				url, new ExponentialBackoffRetry(1000, 6));
		// start connection
		curatorFramework.start();
		return curatorFramework;
	}
	
	public static String createTempNode(String parent, String node,String index,
			final CuratorFramework zkConn) throws Exception{
		Stat stat = zkConn.checkExists().forPath(parent);
		boolean parentExists = true;

        if (null == stat) {
        	logger.debug("ZkMultLoader create path {}",parent);
            try {
                // 进行目录的创建操作
                ZKPaths.mkdirs(zkConn.getZookeeperClient().getZooKeeper(), parent);
            } catch (Exception e) {
                logger.error(" createPath error", e);
                parentExists = false;
            }
        }
        if(parentExists){
        	String path = ZKPaths.makePath(parent, node);
        	String ipAddr = InetAddress.getLocalHost().getHostAddress();
        	
        	try {
        		return zkConn.create()
        				.withMode(CreateMode.EPHEMERAL)
        				.forPath(path,
        						(node + ":" + ipAddr + ":" + index).getBytes());
        	} catch (Exception e) {
        		int i=0;
        		while(i++<20){
        			try {
        				logger.warn(node+" is exist,sleep 5s and then retry");
        				Thread.sleep(5000);
        				return zkConn.create()
        						.withMode(CreateMode.EPHEMERAL)
        						.forPath(path,
        								(node + ":" + ipAddr + ":" + index).getBytes());
        			} catch (Exception e1) {
        				logger.error(" createPath error", e1);
        			}
        		}
        		logger.error(e.getMessage(),e);
        		throw e;
        	}
        }
        return null;
	}
	
	public static void delTempNode(String path, CuratorFramework zkConn){
		try {
			Stat stat = zkConn.checkExists().forPath(path);
			if(stat != null){
				zkConn.delete().forPath(path);
			}
		} catch (Exception e) {
			logger.error("删除开关zk节点异常",e);
		}
	}
	
	
	public static void treeChildCache(CuratorFramework zkConn ,final String path,
			final ZookeeperProcessListen zkListen) throws Exception{
		logger.info("---------treeChildCache {}", path);
        TreeCache treeCache = new TreeCache(zkConn, path);  
        //设置监听器和处理过程  
        treeCache.getListenable().addListener(new TreeCacheListener() {  
            @Override  
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {  
                ChildData data = event.getData();  
                if(data !=null){
                	if(event.getType() == Type.NODE_ADDED){
                		zkListen.watchPath(path, data.getPath());
                	}
                	logger.debug("--------------- event type {} path {} data {}",event.getType(), data.getPath(), data.getData());
                	zkListen.notifly(data.getPath());
                }else{  
                	logger.debug( "data is null : "+ event.getType());  
                }  
            }
        });  
        //开始监听  
        treeCache.start();  
	}
	
	public static void pathChildCache(CuratorFramework zkConn ,final String path,
			final ZookeeperProcessListen zkListen) throws Exception{
		PathChildrenCache pathChildCache = new PathChildrenCache(zkConn, path, true);  
		//设置监听器和处理过程  
		pathChildCache.getListenable().addListener(new PathChildrenCacheListener() {
			
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
					throws Exception {
					ChildData data = event.getData();  
					if(data !=null){
						if(event.getType() == org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_ADDED){
							zkListen.watchPath(path, data.getPath());
						}else{
							zkListen.notifly(data.getPath());
						}
					}else{  
						logger.debug( "data is null : "+ event.getType());  
					}  
			}
		});
		//开始监听  
		pathChildCache.start();  
	}

	public static void createOfNotExist(CuratorFramework zkConn, String zkPath) throws Exception {
		// TODO Auto-generated method stub
		Stat stat = zkConn.checkExists().forPath(zkPath);

        if (null == stat) {
        	logger.debug("ZkMultLoader create path {}",zkPath);
            try {
                // 进行目录的创建操作
                ZKPaths.mkdirs(zkConn.getZookeeperClient().getZooKeeper(), zkPath);
            } catch (Exception e) {
                logger.error(" createPath error", e);
                throw e;
            }
        }
	}

}
