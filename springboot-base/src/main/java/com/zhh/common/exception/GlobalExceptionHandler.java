package com.zhh.common.exception;

import com.zhh.common.enums.BizExceptionEnum;
import com.zhh.common.utils.ResponsePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常捕捉
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/1/8 16:47
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获Controller层所有未捕获（往外抛）的异常报错，统一处理
     * 注意：对于拦截器中和Spring框架层的异常，无法捕获
     * 若不想被该捕获器捕捉异常，可手动try catch即可
     * */

    /**自定义异常
     *@param bizException
     *@return ResponsePacket
     */
    @ExceptionHandler(value = BizException.class)
    public ResponsePacket bizExceptionHandler(BizException bizException){
        logger.error(bizException.toString());
        return ResponsePacket.onError(bizException.getBizExceptionEnum());
    }

     /**默认异常
     * @param exception
     * @return ResponsePacket
     */
    @ExceptionHandler(value = Exception.class)
    public ResponsePacket defaultExceptionHandler(Exception exception){
        logger.error(exception.toString());
        return ResponsePacket.onError(BizExceptionEnum.THROW_EXCEPTION);
    }
}
