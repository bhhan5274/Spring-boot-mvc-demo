package com.bhhan.springbootmvc.web;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by hbh5274@gmail.com on 2020-01-25
 * Github : http://github.com/bhhan5274
 */

@Data
public class Event {
    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    private int limit;
}
