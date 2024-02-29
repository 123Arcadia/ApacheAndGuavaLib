//package org.Test.Apache_commons_langs.httpClient;
//
//import org.junit.Test;
//
//import javax.swing.filechooser.FileSystemView;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class HttpGetTest {
//
//    /**
//     * http get 请求远程地址，获取返回数据，get 参数直接拼在了 url 地址上.
//     */
//    @Test
//    public void testHttpGet1() {
//        String url = "https://i.news.qq.com/trpc.qqnews_web.pc_base_srv.base_http_proxy/NinjaPageContentSync?pull_urls=today_topic_2018";
//        //String url = "https://news.qq.com/ninja/qqnews_tuiguang.htm";
//
//        CloseableHttpClient httpClient = null;
//        HttpGet httpGet = null;
//        CloseableHttpResponse httpResponse = null;
//        try {
//            //1、创建 CloseableHttpClient 同步请求对象
//            httpClient = HttpClientBuilder.create().build();
//
//            // 2、HttpGet(final String uri)：创建 http get 请求对象
//            httpGet = new HttpGet(url);
//
//            // 3、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
//            // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
//            httpResponse = httpClient.execute(httpGet);
//
//            // 4、获取响应结果：比如头信息、状态码、响应正文等等.
//            // Header[] getAllHeaders()：返回此请求的所有头信息，顺序与发送的顺序相同.
//            // StatusLine getStatusLine()：获取此响应的状态行，没有时，返回 null.
//            // HttpEntity getEntity()：获取此响应的消息实体，没有时，返回 null.
//            // String toString(final HttpEntity entity)：读取响应消息实体的内容并将其作为字符串返回，参数不能为 null.
//            // Header getContentEncoding()：获取响应内容的编码，未知时返回 null.
//            // long getContentLength():返回内容的字节数，如果未知，则返回负数.
//            Header[] headers = httpResponse.getAllHeaders();
//            StatusLine statusLine = httpResponse.getStatusLine();
//            String entity = EntityUtils.toString(httpResponse.getEntity());
//            for (Header header : headers) {
//                System.out.println("\t响应头信息：" + header.toString());
//            }
//            System.out.println("响应状态行：" + statusLine);//statusLine=HTTP/1.1 200 OK
//            System.out.println("响应正文内容：" + entity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            /**
//             * consume(final HttpEntity entity)：确保实体内容已完全使用，并且内容流（如果存在）已关闭。
//             *  httpResponse.close()：关闭此流并释放与之关联的所有系统资源。如果流已关闭，则调用此方法无效。
//             */
//            try {
//                if (httpResponse != null) {
//                    EntityUtils.consume(httpResponse.getEntity());
//                    httpResponse.close();
//                }
//                //无论成功与否,都要释放连接并终止
//                if (httpGet != null && !httpGet.isAborted()) {
//                    httpGet.releaseConnection();
//                    httpGet.abort();
//                }
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * http get 请求远程地址，获取返回数据，设置超时时间，默认如果连接不上会一直阻塞等待.
//     */
//    @Test
//    public void testHttpGet2() {
//        String url = "https://i.news.qq.com/trpc.qqnews_web.kv_srv.kv_srv_http_proxy/list?sub_srv_id=world&srv_id=pc";
//
//        CloseableHttpClient httpClient = null;
//        HttpGet httpGet = null;
//        CloseableHttpResponse httpResponse = null;
//        try {
//            // 0、构建 url 查询参数，有些包含特殊符合的参数直接放在 url的 ?后面会行不通，此时可以使用 URIBuilder 进行构建 url 实例.
//            URIBuilder uriBuilder = new URIBuilder(url);
//            uriBuilder.addParameter("offset", "0");
//            uriBuilder.addParameter("limit", "20");
//            uriBuilder.addParameter("strategy", "1");
//            uriBuilder.addParameter("ext", "{\"pool\":[\"top\"],\"is_filter\":2,\"check_type\":true}");
//            String urlPath = uriBuilder.toString();
//
//            //1、创建 CloseableHttpClient 同步请求对象
//            httpClient = HttpClientBuilder.create().build();
//
//            // 2、HttpGet(final String uri)：创建 http get 请求对象
//            httpGet = new HttpGet(urlPath);
//
//            // 3、设置超时时间、请求时间、socket 时间都为 15 秒，允许重定向
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000)
//                    .setConnectionRequestTimeout(15000)
//                    .setSocketTimeout(15000)
//                    .setRedirectsEnabled(true)
//                    .build();
//            httpGet.setConfig(requestConfig);
//
//            // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
//            // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
//            httpResponse = httpClient.execute(httpGet);
//
//            // 5、获取响应结果, 状态码 200 表示请求成功
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            System.out.println("响应状态码：" + statusCode);
//            if (statusCode == 200) {
//                HttpEntity httpEntity = httpResponse.getEntity();
//                // 使用指定的字符编码解析响应消息实体
//                String feedback = EntityUtils.toString(httpEntity, "UTF-8");
//                System.out.println("feedback：" + feedback);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } finally {
//            /**
//             * consume(final HttpEntity entity)：确保实体内容已完全使用，并且内容流（如果存在）已关闭。
//             *  httpResponse.close()：关闭此流并释放与之关联的所有系统资源。如果流已关闭，则调用此方法无效。
//             */
//            try {
//                if (httpResponse != null) {
//                    EntityUtils.consume(httpResponse.getEntity());
//                    httpResponse.close();
//                }
//                //无论成功与否,都要释放连接并终止
//                if (httpGet != null && !httpGet.isAborted()) {
//                    httpGet.releaseConnection();
//                    httpGet.abort();
//                }
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 文件下载
//     */
//    @Test
//    public void testHttpGet3() {
//        String url = "https://scpic.chinaz.net/files/default/imgs/2023-01-03/e0f209137275d9b1.jpg";
//        CloseableHttpClient httpClient = null;
//        HttpGet httpGet = null;
//        CloseableHttpResponse httpResponse = null;
//        try {
//            File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
//            Path targetPath = Paths.get(homeDirectory.getAbsolutePath(), url.substring(url.lastIndexOf("/")));
//
//            // 1、创建 CloseableHttpClient 同步请求对象
//            httpClient = HttpClientBuilder.create().build();
//            // 2、HttpGet(final String uri)：创建 http get 请求对象
//            httpGet = new HttpGet(url);
//            // 3、设置超时时间、请求时间、socket 时间都为 15 秒，允许重定向
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000)
//                    .setConnectionRequestTimeout(15000)
//                    .setSocketTimeout(15000)
//                    .setRedirectsEnabled(true)
//                    .build();
//            httpGet.setConfig(requestConfig);
//            // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
//            // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
//            httpResponse = httpClient.execute(httpGet);
//            // 5、获取响应结果, 状态码 200 表示请求成功
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            System.out.println("响应状态码：" + statusCode);
//            if (statusCode == 200) {
//                HttpEntity httpEntity = httpResponse.getEntity();
//                InputStream inputStream = httpEntity.getContent();
//                Files.deleteIfExists(targetPath);
//                Files.copy(inputStream, targetPath);
//                System.out.println(targetPath);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            /**
//             * consume(final HttpEntity entity)：确保实体内容已完全使用，并且内容流（如果存在）已关闭。
//             *  httpResponse.close()：关闭此流并释放与之关联的所有系统资源。如果流已关闭，则调用此方法无效。
//             */
//            try {
//                if (httpResponse != null) {
//                    EntityUtils.consume(httpResponse.getEntity());
//                    httpResponse.close();
//                }
//                //无论成功与否,都要释放连接并终止
//                if (httpGet != null && !httpGet.isAborted()) {
//                    httpGet.releaseConnection();
//                    httpGet.abort();
//                }
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
