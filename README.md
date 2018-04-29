# boot-websocket-log
spring boot系统中使用websocket技术实时输出系统日志到浏览器端，因为是实时输出，所有第一时间就想到了使用webSocket,而且在spring boot中，使用websocket超级方便，阅读本文，你会接触到以下关键词相关技术，WebSocket（stopmp服务端），stomp协议，sockjs.min.js，stomp.min.js（stomp客户端），本文使用到的其实就是使用spring boot自带的webSocket模块提供stomp的服务端，前端使用stomp.min.js做stomp的客户端，使用sockjs来链接，前端订阅后端日志端点的消息，后端实时推送，达到日志实时输出到web页面的目的

> 欢迎加入开源技术QQ群一起交流：613025121


# 此项目使用场景

1. 集成到已有的项目中，实现项目日志文件web端浏览

2. 单独使用，指定已有项目的日志文件位置，实现项目日志web端浏览 

3. 使用boot-websocket-log的stopmp服务推送日志其他的非web端消费

# web端日志效果

![输入图片说明](https://gitee.com/uploads/images/2018/0105/152317_a3ff7cf2_492218.png "屏幕截图.png")

# websocket原理

![输入图片说明](https://gitee.com/uploads/images/2018/0104/195640_8e997823_492218.png "屏幕截图.png")

# 相关技术栈

1. stomp.js客户端：http://jmesnil.net/stomp-websocket/doc/
1. scok.js客户端：https://github.com/sockjs/sockjs-client
1. spring webSocket：https://docs.spring.io/spring/docs/