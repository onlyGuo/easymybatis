package com.aiyi.core.annotation.po.vali.enums;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.annotation.po.vali.enums
 * @Description : 校验正则
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/14 下午4:55
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/14
 */
public enum ValidationType {
  // 不进行校验
  Not(null),
  // 进行电子邮箱校验
  Email("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"),
  // 进行电话号码校验
  Phone("(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"),
  // 进行收集号码校验
  Mobile("(\\+\\d+)?1[34578]\\d{9}$"),
  // 进行身份证号校验
  IDCard("[1-9]\\d{13,16}[a-zA-Z0-9]{1}"),
  // 进行URL校验
  URL("(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"),
  // 进行纯中文校验
  Chinese("^[\u4E00-\u9FA5]+$"),
  // 进行纯英文校验
  English("^[A-Za-z]+$"),
  // 进行纯数字校验
  Number("^-?(([1-9]\\d*$)|0)"),
  // 进行密码校验(数字&字母&特殊符号)
  Password("^.*[/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]+.*$"),
  // 中国邮政编码校验
  Postcode("[1-9]\\d{5}"),
  // IP地址校验
  IpAddress("[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))");

  private String regex;

  private ValidationType(String regex){
    this.regex = regex;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }
}
