package org.job.lite;

import com.dangdang.ddframe.job.lite.api.JobScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.job.lite.java.JavaConfigManager;

/**
 * Java API配置任务.
 *
 * @author thinking_fioa 2018/11/16
 */
public class JavaElasticJobStart {

  private static final Logger LOGGER = LogManager.getLogger(JavaElasticJobStart.class);

  public static void main(String[] args) {
    LOGGER.info("Java Elastic-Job Start");
    new JobScheduler(JavaConfigManager.createRegistryCenter(), JavaConfigManager.createJobConfiguration()).init();
  }
}
