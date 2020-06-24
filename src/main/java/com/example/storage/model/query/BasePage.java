package com.example.storage.model.query;

import lombok.Data;

/**
  * 分页的基类
  *@author: Allen Holger
 * @date: 2020/6/11 23:09
  */
@Data
public class BasePage {

    private Integer pageIndex = 1;

    private Integer pageSize = 50;
}
