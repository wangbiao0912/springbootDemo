package com.after00.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


@Builder
//Builder 模式我不想再多解释了，读者可以看一下《Head First》(设计模式) 的建造者模式  Student student = Student.builder().name("zs").age(24).build();
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)   //bean链式风格    EnterpriseInfo student = new EnterpriseInfo().setId("22").setName("zs");
@Setter
@Getter
//@RequiredArgsConstructor(staticName = "ofName")   //生成静态调用类 EnterpriseInfo enterpriseInfo1=EnterpriseInfo.ofName().setAddress("222")
public class EnterpriseInfo implements Serializable {
    private String id;
    @NotEmpty(message = "企业名称不能为空")
    private String name;
    private String addRess;
    private Date createTime;
}
