package cn.odboy.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页对象
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/7/8 20:31
 */
@Getter
@Setter
public class PeachPage<T, D> {
    private int page = 1;
    private long size = 10;
    private long total = 0;
    private T params;
    private List<D> data;
}
