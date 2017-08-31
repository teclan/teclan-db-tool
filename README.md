安装依赖的jdbc库：

  ```
  mvn initialize
  ```
  
 打包
 
 ```
 mvn package -Dmaven.test.skip assembly:single
 ```
 
 使用帮助
 
 ```
 java -jar xxx.jar -h
 ```