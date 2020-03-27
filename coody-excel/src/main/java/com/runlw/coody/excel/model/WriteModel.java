package com.runlw.coody.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname WriteModel
 * @Description 写入模型
 * @Date 2019/12/19 9:09
 * @Author lw
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel {

    @ExcelProperty(value = "序号", index = 0)
    private Integer serialNumber;

    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    @ExcelProperty(value = "密码", index = 2)
    private String password;

}
