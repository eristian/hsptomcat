package com.hspedu.tomcat.http;

import java.io.*;
import java.util.HashMap;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  19:19
 *
 * @author Mamenchisat
 * @verison 1.0
 * 封装http请求的数据
 * 比如 method , uri , 参数列表
 * HspRequest作用就等价于原生的httpServletRequest
 */
public class HspRequest {
    private String method;
    private String uri;
    private InputStream inputStream = null;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HashMap<String, String> getParametersMapping() {
        return parametersMapping;
    }

    public void setParametersMapping(HashMap<String, String> parametersMapping) {
        this.parametersMapping = parametersMapping;
    }

    //参数列表
    private HashMap<String, String> parametersMapping = new HashMap<String, String>();

    @Override
    public String toString() {
        return "HspRequest{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", parametersMapping=" + parametersMapping +
                '}';
    }
    public HspRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        init();
    }
    //inputStream和http请求的socket关联;
    public void init() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String s = bufferedReader.readLine();
            String[] requestLine = s.split(" ");
            method = requestLine[0];
            //解析得到uri
            //1。先看看有没有参数列表
            int index = requestLine[1].indexOf("?");
            if (index == -1) {
                //说明没有参数列表
                uri = requestLine[1];
            } else {
                uri = requestLine[1].substring(0, index);
                //获取参数列表
                String parameters = requestLine[1].substring(index + 1);
                String[] parametersPair = parameters.split("&");
                if (parametersPair != null && !parametersPair.equals("")) {
                    //再次分割
                    for (String parameterPair:parametersPair){
                        String[] parameterVal = parameterPair.split("=");
                        if (parameterVal.length == 2) {
                            parametersMapping.put(parameterVal[0], parameterVal[1]);
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getParameter(String name){
        if(parametersMapping.containsKey(name)){
            return parametersMapping.get(name);
        }else {
            return "";
        }
    }
}
