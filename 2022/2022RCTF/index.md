## LD_PRELOAD



进程在启动后，会按照一定顺序加载动态库：

加载环境变量LD_PRELOAD指定的动态库
加载文件/etc/ld.so.preload指定的动态库
搜索环境变量LD_LIBRARY_PATH指定的动态库搜索路径
搜索路径/lib64下的动态库文件
