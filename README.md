# snow-job

## Elastic-job by thinkingfioa
Elastic-job是ddframe中dd-job的作业模块中分离出来的分布式弹性作业框架。该项目基于成熟的开源产品Quartz和Zookeeper
及其客户端Curator进行二次开发。elastic-job主要的设计理念是无中心化的分布式定时调度框架。

### 主要功能
- 定时任务： 基于成熟的定时任务作业框架Quartz cron表达式执行定时任务。
- 作业注册中心： 基于Zookeeper和其客户端Curator实现的全局作业注册控制中心。用于注册，控制和协调分布式作业执行。
- 作业分片： 将一个任务分片成为多个小任务项在多服务器上同时执行。
- 弹性扩容缩容： 运行中的作业服务器崩溃，或新增加n台作业服务器，作业框架将在下次作业执行前重新分片，不影响当前作业执行。
- 支持多种作业执行模式： 支持Single，DataFlow和Script三种作业模式。
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
1. elastic-job-platform ----- Maven版本依赖，编译elastic-job主项目前需要先编译本项目
1. elastic-job-core ----- 核心模块，只通过Quartz和Curator就可执行分布式作业
2. elastic-job-spring ----- 对spring支持的模块，包括命名空间、依赖注入、占位符等
3. elastic-job-console ----- web控制台
4. elastic-job-example 
5. elastic-job-test

### 核心理念
1. 分布式调度 ----- Elastic-Job-Lite并无作业调度中心节点，而是基于部署框架程序在到达响应时间点时各自触发调度。注册中心(zookeeper?)仅用于作业注册和监控信息存储。而主作业节点仅用于处理分片和清理功能

### 作业类型
1. Single类型作业 ----- 周期性执行普通的定时任务，但提供弹性扩缩容和分片
2. Dataflow类型作业 ----- 先fetchData, 然后调用processData处理数据。适用于流式不间歇的数据处理
3. Script类型作业  ----- Script类型作业为脚本类型作业，支持shell、python和perl等所有类型脚本。
4. ELK ----- elasticSearch + logstash + kibana 

### 重要参考链接
1. [Elastic-job分布式定时任务框架](https://www.cnblogs.com/wyb628/p/7682580.html)  -- 老版本
2. [官网-elasticjob-lite](http://elasticjob.io/docs/elastic-job-lite/00-overview/)
3. [Elastic-Job——分布式定时任务框架](https://blog.csdn.net/u014401141/article/details/78676248)

## LTS by only3seconds
LTS(Light Task Scheduler) 是一个轻量级分布式任务调度框架，主要有以下三种角色：

- JobClient: 	主要负责提交任务和接收任务执行反馈结果
- JobTracker: 任务调度中心，负责接收并分配任务
- TaskTracker: 负责执行任务，执行完反馈给 JobClient

各个节点都是无状态的，可以部署多个，来实现负载均衡，实现更大的负载量, 并且框架具有很好的容错能力。 采用Zookeeper暴露节点信息，master选举。Mongo存储任务队列和任务执行日志, netty做底层通信。



## 研究内容
1.	基于Zookeeper分布式框架，采用无中心化设计。支持集群动态扩容和失效转移。
2.	采用松耦合架构设计模式，任务调度和任务具体业务逻辑解耦，具体的业务逻辑对于任务调度框架透明化。支持多种语言编写的脚本任务。
3.	研究任务负载均衡算法。考虑到部分任务是采用定时机制执行，未来某时间点或时间段内将周期性执行任务具体业务逻辑，任务负载是可知或可预测的。另一方面，根据每个工作节点的CPU负载、内存负载以及进程或线程的调度延迟来预估工作节点执行能力预估。为了充分利用集群资源，提出适用于分布式定时任务调度的负载均衡算法。
4.	支持多种类型任务处理，包括周期性执行任务、依赖于流式数据任务执行。
5.	支持任务分片，提高任务执行速度。考虑到部分任务处理依赖于大量数据计算，耗时过长，提出将任务分片，再组装，充分利用分布式集群计算能力，提高任务执行速度。


## TODO 

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

## 参考链接 
1. 分布式定时任务调度系统技术选型: https://www.cnblogs.com/davidwang456/p/9057839.html
2. 这些优秀的国产分布式任务调度系统，你用过几个 : https://blog.csdn.net/qq_16216221/article/details/70314337
3. 我所理解的分布式任务调度: https://www.jianshu.com/p/9bf9ddaac438
4. 分布式任务调度架构原理和设计介绍 ： https://cloud.tencent.com/developer/news/201225
5. LTS轻量级分布式任务调度框架：https://blog.csdn.net/qq_26562641/article/details/59108624
6. 分布式任务调度框架—SkySchedule : http://moon-walker.iteye.com/blog/2386504
7. 关于分布式任务调度平台，数人云的经验都在这里：https://www.sohu.com/a/192957718_332175 [不开源]
8. 分布式任务调度框架-brave-dis-job: https://www.jianshu.com/p/72658c73bc77
9. 分布式调度引擎-JobKeepper:  http://www.cstor.cn/proTextdetail_3613.html。[公司产品，无源代码]


