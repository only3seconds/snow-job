package org.job.lite.spring;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 自定义的Job.
 *
 * @author thinking_fioa 2018/11/17
 */

public class SpringElasticJob implements SimpleJob {

  @Override
  public void execute(ShardingContext context) {
    switch (context.getShardingItem()) {
      case 3:
        System.out.println("3333");
        break;
      case 4:
        System.out.println("4444");
        break;
      case 5:
        System.out.println("5555");
        break;
      default:
        System.out.println("default");
        break;
    }
  }
}
