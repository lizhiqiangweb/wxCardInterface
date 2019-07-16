微信小程序接口：
  java+mysql
层次接口：
  service+dao+util+model
过程：
  jdk+tomact+mysql+navicat+myeclipse安装
  1、jdk安装过程中最好为软件设定安装位置，方便进行环境配置。
  2、cmd命令下java -version和javac运行成功（环境配置成功）。
  3、mysql安装过程中注意勾选问题，新版本的默认勾选看清楚，不然后续的链接数据库会报错。
  4、开发过程中注意jdk的版本，tomact和jdk的版本一致
  （在开发过程中运用到servlet-api.jar，servlet-api.jar在jdk中的版本和tomact版本不一致，导致程序报错：javax.servlet.ServletRequest.getServletContext()Ljavax/servlet/ServletC,解决方案：将tomact安装lib目录下的servlet-api.jar文件复制到jdk安装目录下jre.lib文件夹下）

接口开发：
  1、采用三层架构
    实体类：model
    数据层：dao
    控制层：service（servlet执行数据返回和请求）
