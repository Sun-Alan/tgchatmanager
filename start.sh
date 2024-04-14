#!/bin/bash

#编译镜像
docker build -t tgchatmanager:v1  .


# 定义路径
DIR="/home/tgchatmanagerconf/"



# 检查目录是否存在，如果存在则删除并重新创建
if [ -d "$DIR" ]; then
    rm -rf "$DIR"
fi
mkdir -p "$DIR"

# 拷贝文件
cp config/bot.sql $DIR
cp config/config.json $DIR
cp docker-compose.yml $DIR

# 拷贝文件完成后的提示
echo -e "\e[31m请在/home/tgchatmanagerconf/config.json中填写你的bot配置信息\e[0m" # 使用红色字体显示提示信息
