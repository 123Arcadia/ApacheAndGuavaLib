package org.Test.MyClassLoaderTest.Context_ClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义classloader
 */
public class MyClassLoader1 extends ClassLoader {

    private String mLibPath;

    public MyClassLoader1(String path) {
//        path.replaceAll("\\.", "/");
        mLibPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String fileName = getFileName(name);
        String fileName = mLibPath+name+".class";
//        File file = new File(mLibPath,fileName);
        System.out.println("MyclassLocader1: " +"fileName="  +fileName);
        try {
            FileInputStream is = new FileInputStream(new File(fileName));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();

            return defineClass(name,data,0,data.length);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }

}