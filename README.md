
🤖 Telegram 群管机器人
===========================================

基于 **SpringBoot** 和 **Telegrambot-Api** 打造的多功能 Telegram 群管理机器人，有效查杀18+违规视频、贴图、图片，AI识别各种博彩，违规图片、文字，可设置正则自定义关键字回复、违规词汇删除等功能，支持每日词云统计，进群欢迎等多种实用群管功能，**Powered By OpenAI And Google Cloud Vision**。


 ▶️ 配置机器人
----------------

- 前往 `/home/tgchatmanagerconf/` 目录，编辑 `conf.json` 文件：
  1. 填入你的 `username` 和 `token` 到 `botConfig` 的 `name` 和 `token` 字段。
  2. 保存更改。

▶️ 运行机器人
----------------

- 确保你的网络可以连接到 Telegram 服务器。如果使用软路由，请使用增强代理。
- 在 `/home/tgchatmanagerconf/` 目录下执行：
```bash
docker-compose up -d
```

⏸️ 暂停容器
------------

- 在 `/home/tgchatmanagerconf/` 目录下执行：
```bash
docker-compose stop
```

🔥 重启容器
------------

- 在 `/home/tgchatmanagerconf/` 目录下执行：
```bash
docker-compose restart
```

🔍 查看日志
------------

在 `/home/tgchatmanagerconf/` 目录下执行：
```bash
docker-compose logs -f 
```

🔄 更新
--------

在 `/home/tgchatmanagerconf/` 目录下进行以下操作：
1.停止并移除卷：
```bash
docker-compose down
```
2.删除数据库持久卷（❗️危险操作，你需要对比本仓库里的数据库文件是否更新过，否则不要执行，删除之前请备份各种关键词文档）：
- 首先备份数据库至`/home/`目录下
```bash
docker exec -it tgchatmanagerconf-mysql-1 mysqldump -uroot -ppassword bot  > /home/bot.sql
```
- 删除数据库持久卷
```bash
docker volume rm tgchatmanagerconf_mysql-data
```
3.拉取最新镜像：
```bash
docker-compose pull  
```
4.使用新镜像重新启动容器：
```bash
docker-compose up -d
```
5.在宿主机的`/etc/sysctl.conf`文件中添加或修改以下行并重启：
```bash
vm.overcommit_memory = 1
```
