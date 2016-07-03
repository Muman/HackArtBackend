package com.hack.art.util;

import org.apache.log4j.Logger;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by ROLO on 22.02.2016.
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    Logger logger = Logger.getLogger(RequestWrapper.class);

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public RequestWrapper(HttpServletRequest request) throws IOException {

        super(request);
        StringBuilder sb = new StringBuilder();
        BufferedReader buff = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (null != inputStream) {
                buff = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuff = new char[128];
                int bytesToRead = -1;
                while ((bytesToRead = buff.read(charBuff)) > 0) {
                    sb.append(charBuff, 0, bytesToRead);
                }
            } else {
                sb.append("");
            }
        } catch (IOException e) {
            logger.error(e);
            throw e;
        } finally {
            if (null != buff) {
                try {
                    buff.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = sb.toString();
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody(){
        return this.body;
    }
}
