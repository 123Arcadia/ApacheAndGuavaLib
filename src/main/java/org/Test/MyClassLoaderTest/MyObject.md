测试过程：

MyObject.java在F:\classLoader中，
可以看到java文件在外部编译而成的class文件，在该项目目录下可以引入加载成功

---

> 1. https://blog.csdn.net/fwt336/article/details/61922468
> 2. https://blog.csdn.net/wal1314520/article/details/93975308

## Context ClassLoader 线程上下文类加载器

每个Thread都有一个相关联的ClassLoader，**默认是`AppClassLoader`**。并且子线程默认使用父线程的ClassLoader除非子线程特别设置。

DiskClassLoader1.java这个ClassLoader的代码和DiskClassLoader.java代码一致.
DiskClassLoader1加载位置于D:\\lib\\test中的SpeakTest.class文件

结果：
2. 子线程的ContextClassLoader是AppClassLoader。
3. AppClassLoader加载不了父线程当中已经加载的SpeakTest.class内容

