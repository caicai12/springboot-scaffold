package com.zhh.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description 响应处理
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/2/21 9:44
 */
@Component
public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;

    private ServletOutputStream out;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
    }

    public byte[] getContent() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    class WrapperOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        public WrapperOutputStream(ByteArrayOutputStream bos) {
            this.bos = bos;
        }

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener arg0) {

        }
    }
}
