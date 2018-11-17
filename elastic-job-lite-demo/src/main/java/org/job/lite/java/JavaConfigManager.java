package org.job.lite.java;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * Java启动方式配置.
 *
 * @author thinking_fioa 2018/11/16
 */

public class JavaConfigManager {

  public static CoordinatorRegistryCenter createRegistryCenter() {
    CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("zk_host:2181",
        "elastic-job-demo"));
    regCenter.init();
    return regCenter;
  }

  public static LiteJobConfiguration createJobConfiguration() {
    // 定义作业核心配置
    JobCoreConfiguration simpleCoreConfig
        = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 10).build();
    SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig,
        JavaElasticJob.class.getCanonicalName());
    // 定义Lite作业根配置
    return LiteJobConfiguration.newBuilder(simpleJobConfig).build();
  }
}
