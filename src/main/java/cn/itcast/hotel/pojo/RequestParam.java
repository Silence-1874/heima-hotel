package cn.itcast.hotel.pojo;

import lombok.Data;

@Data
public class RequestParam {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
}
