package org.job.lite.java;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 自定义的Job.
 *
 * @author thinking_fioa 2018/11/16
 */

public class JavaElasticJob implements SimpleJob {

  @Override
  public void execute(ShardingContext context) {
    switch (context.getShardingItem()) {
      case 0:
        System.out.println("0000");
        break;
      case 1:
        System.out.println("1111");
        break;
      case 2:
        System.out.println("2222");
        break;
      default:
        System.out.println("default");
        break;
    }
  }
}
