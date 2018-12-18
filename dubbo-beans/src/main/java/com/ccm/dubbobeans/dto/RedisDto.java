package com.ccm.dubbobeans.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by chencm on 2018/12/5
 */
@Data
public class RedisDto implements Serializable {
    private static final long serialVersionUID = -1135248248255865336L;
    private String name;
}
