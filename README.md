# snow-job

## Elastic-job by thinkingfioa
Elastic-job是ddframe中dd-job的作业模块中分离出来的分布式弹性作业框架。该项目基于成熟的开源产品Quartz和Zookeeper
及其客户端Curator进行二次开发。

### 主要功能
- 定时任务： 基于成熟的定时任务作业框架Quartz cron表达式执行定时任务。
- 作业注册中心： 基于Zookeeper和其客户端Curator实现的全局作业注册控制中心。用于注册，控制和协调分布式作业执行。
- 作业分片： 将一个任务分片成为多个小任务项在多服务器上同时执行。
- 弹性扩容缩容： 运行中的作业服务器崩溃，或新增加n台作业服务器，作业框架将在下次作业执行前重新分片，不影响当前作业执行。
- 支持多种作业执行模式： 支持OneOff，Perpetual和SequencePerpetual三种作业模式。
- 失效转移： 运行中的作业服务器崩溃不会导致重新分片，只会在下次作业启动时分片。启用失效转移功能可以在本次作业执行过程中，监测其他作业服务器空闲，抓取未完成的孤儿分片项执行。
- 运行时状态收集： 监控作业运行时状态，统计最近一段时间处理的数据成功和失败数量，记录作业上次运行开始时间，结束时间和下次运行时间。
- 作业停止，恢复和禁用：用于操作作业启停，并可以禁止某作业运行（上线时常用）。
- 被错过执行的作业重触发：自动记录错过执行的作业，并在上次作业完成后自动触发。可参考Quartz的misfire。
- 多线程快速处理数据：使用多线程处理抓取到的数据，提升吞吐量。
- 幂等性：重复作业任务项判定，不重复执行已运行的作业任务项。由于开启幂等性需要监听作业运行状态，对瞬时反复运行的作业对性能有较大影响。
- 容错处理：作业服务器与Zookeeper服务器通信失败则立即停止作业运行，防止作业注册中心将失效的分片分项配给其他作业服务器，而当前作业服务器仍在执行任务，导致重复执行。
- Spring支持：支持spring容器，自定义命名空间，支持占位符。
- 运维平台：提供运维界面，可以管理作业和注册中心。

### 目录介绍
1. elastic-job-core ----- 核心模块，只通过Quartz和Curator就可执行分布式作业
2. elastic-job-spring ----- 对spring支持的模块，包括命名空间、依赖注入、占位符等
3. elastic-job-console ----- web控制台
4. elastic-job-example 
5. elastic-job-test

### 重要参考链接
1. [Elastic-job分布式定时任务框架](https://www.cnblogs.com/wyb628/p/7682580.html)

### TODO 

1. xxl-job - 重要
2. elastic-job   - 重要
3. celery [源码](https://github.com/celery/celery) 注：Python
4. saturn [源码](https://github.com/vipshop/Saturn) 注：太大了，包很多
5. SchedulerX -无源码
6. openCron [源码](https://github.com/ckxlovezqq/opencron) 文档不多
7. LTS - light-task-scheduler [源码](https://github.com/ltsopensource/light-task-scheduler) [博客](https://blog.csdn.net/qq_26562641/article/details/59108624) 注：重要，文档详细 
8. Uncode-Scheduler[源码](https://github.com/uncodecn/uncode-schedule) 太小了
9. Antares [源码](https://github.com/ihaolin/antares) 注：文档较详细
10. TBScheduler -无源码
11. SkySchedule [源码](https://github.com/gantianxing/skySchedule)，[文档](http://moon-walker.iteye.com/blog/2386504) 注：基于netty
12. brave-dis-job [源码](https://github.com/zhangjun075/brave-dis-job) [文档](https://www.jianshu.com/p/72658c73bc77) 注：很小，有基本设计框架

### 参考链接 
1. 分布式定时任务调度系统技术选型: https://www.cnblogs.com/davidwang456/p/9057839.html
2. 这些优秀的国产分布式任务调度系统，你用过几个 : https://blog.csdn.net/qq_16216221/article/details/70314337
3. 我所理解的分布式任务调度: https://www.jianshu.com/p/9bf9ddaac438
4. 分布式任务调度架构原理和设计介绍 ： https://cloud.tencent.com/developer/news/201225
5. LTS轻量级分布式任务调度框架：https://blog.csdn.net/qq_26562641/article/details/59108624
6. 分布式任务调度框架—SkySchedule : http://moon-walker.iteye.com/blog/2386504
7. 关于分布式任务调度平台，数人云的经验都在这里：https://www.sohu.com/a/192957718_332175 [不开源]
8. 分布式任务调度框架-brave-dis-job: https://www.jianshu.com/p/72658c73bc77
9. 分布式调度引擎-JobKeepper:  http://www.cstor.cn/proTextdetail_3613.html。[公司产品，无源代码]


