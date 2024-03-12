package org.Test.Apache_compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipOutputStream;

public class Test01 {
    /**
     * 简单的工厂类用户动态的获取压缩流和归档流
     *
     * 压缩组件分为压缩器(compressors) 和 归档器(archivers)。压缩器 （解压缩）通常存储单个条目的流，而归档器处理包含由ArchiveEntry实例表示的结构化内容的归档，而这些实例通常对应于单个文件或目录。
     */
    @Test
    public void test01() {
        /**
         * 压缩
         */
        File output = null;
        File input = null;
        final OutputStream out;
        CompressorOutputStream cos = null;
        InputStream inStream = null;
        try {
            output = new File("D:\\javaProject\\ApacheAndGuavaLib\\src\\main\\java\\org\\Test\\Apache_compress\\CompressComponent");
            input = new File("D:\\javaProject\\ApacheAndGuavaLib\\src\\main\\java\\org\\Test\\Apache_compress\\CompressTo\\test.gz");
            OutputStream outStream = Files.newOutputStream(output.toPath());
            cos = new CompressorStreamFactory().createCompressorOutputStream(CompressorStreamFactory.BZIP2, outStream);
            IOUtils.copy(Files.newInputStream(input.toPath()), cos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CompressorException e) {
            e.printStackTrace();
        } finally {
            if (cos != null) {
                try {
                    cos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * 解压
         */
    }


    @Test
    public void compressTest() {
        File targetFile = new File("D:/malin/malin.zip");
        File sourceFile = new File("D:/malin/xxx");
        compressFile(targetFile, sourceFile);
    }

    public void compressFile(File targetFile, File sourceFile) {
        ZipOutputStream zipOutput = null;
        try {
            zipOutput = new ZipOutputStream(new FileOutputStream(targetFile));
            compress(zipOutput, sourceFile, sourceFile.getName());
            if (zipOutput != null) {
                try {
                    zipOutput.closeEntry();
                    zipOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void compress(ZipOutputStream zipOutput, File sourceFile, String base) throws IOException {
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            if (files.length == 0) {
                System.out.println(base + "/");
                zipOutput.putNextEntry(new ZipArchiveEntry(base + "/"));
            } else {
                for (File file : files) {
                    compress(zipOutput, file, base + "/" + file.getName());
                }
            }
        } else {
            zipOutput.putNextEntry(new ZipArchiveEntry(base));
            FileInputStream fis = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int tag;
            System.out.println(base);
            while ((tag = bis.read()) != -1) {
                zipOutput.write(tag);
            }
            fis.close();
            bis.close();
        }
    }
}
