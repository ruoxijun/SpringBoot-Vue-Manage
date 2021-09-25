# SpringBoot and Vue 管理系统

## 1. Vue 项目创建：

>   *   Nodejs 版本建议 **14** 以上， `node -v` 命令查看版本。
>
>   *   vue版本3 vue-cil 如果安装了老版本vue-cli先卸载 `npm uninstall -g vue-cli` 再安装最新稳定版 `npm install -g @vue/cli` 。

### 1. vue 项目搭建：

1.   使用 `vue create vue-manage` 命令创建项目。
2.   选择 `Manually select features` 选项回车表示手动创建项目。

![image-20210912101649454](\ty_imgs\image-20210912101649454.png)

3.   通过上下箭头移动光标，使用空格选择或取消选项。选择如下图个选项：

![image-20210912102335024](\ty_imgs\image-20210912102335024-16314134185741.png)

4.   在 `Choose Vue version` 选项回车选择vue版本 **3.x** 
5.   出现 `Use history mode for router?` 选择路由Y表示使用history模式，N表示使用hash模式(即URL地址里面带有#)：这里我们输入 **y**
6.   之后选择 `In package.json` 配置文件集成到 package.json 中
7.   最后询问你是否保存这次的配置，可以保存我这里暂时不保存输入 **n**

**配置总览：**

![image-20210912103433643](\ty_imgs\image-20210912103433643-16314140760192.png)

项目创建成功：命令窗口移动到项目文件夹中使用 `npm run serve` 启动项目， 浏览器中输入 `http://localhost:8080/` 查看。命令窗口中 `ctrl+c` 并输入 **y** 退出项目。

安装 element-plus： `npm install element-plus --save`

安装 Axios ： `npm i axios -S`

