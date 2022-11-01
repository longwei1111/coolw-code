package com.coolw.code.thread.word2pdf.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class BaseResp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private T data;
    private String msg;

    private BaseResp(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> BaseResp<T> data(T data) {
        return new BaseResp(0, data, null);
    }

    public static <T> BaseResp<T> fail(String msg) {
        return new BaseResp(-1, null, msg);
    }

    public static <T> BaseResp<T> fail(int code, String msg) {
        return new BaseResp(code, null, msg);
    }

    public static <T> Boolean isSuccess(BaseResp<T> rb) {
        return rb != null && 0 == rb.code;
    }
}
