1.简单的命名规范
    对于类或者接口  
        一个单词的话首字母必须大写，多个单词的话每个单词的首字母必须大写
            比如：Student，常见的HelloWorld
    对于类的变量
    　　变量的名字必须用一个小写字母开头。后面的单词用大写字母开头。
    对于方法或者变量       
        一个单词的时候全部小写，多个单词的话除第一个单词首字母小写，其他单词首字母都大写
            比如：main方法，setStudentAge()等 
    对于包的命名
        所有单词都小写，如cn     
    一个单词的常量需要所有字母大写，多个单词的话所有字母都需要大写，单词之间用_隔开
        比如：PI , STUDENT_MAX_AGE;
        
2.注释规范
    1、   类注释
    
    在每个类前面必须加上类注释，注释模板如下：
    
    /**
    
    * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
    
    * FileName: Test.java
    
    * 类的详细说明
    
    *
    
    * @author 类创建者姓名
        * @Date    创建日期
    
    * @version 1.00
    
    */
    2、   属性注释
    
    在每个属性前面必须加上属性注释，注释模板如下：
    
    /** 提示信息 */
    
    private String strMsg = null;
    
     
    
    3、   方法注释
    
    在每个方法前面必须加上方法注释，注释模板如下：
    
    /**
    
    * 类方法的详细使用说明
    
    *
    
    * @param 参数1 参数1的使用说明
    
    * @return 返回结果的说明
    
    * @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
    
    */
    
    4、   构造方法注释
    
    在每个构造方法前面必须加上注释，注释模板如下：
    
    /**
    
    * 构造方法的详细使用说明
    
    *
    
    * @param 参数1 参数1的使用说明
    
    * @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
    
    */
    
     
    
    5、   方法内部注释
    
    在方法内部使用单行或者多行注释，该注释根据实际情况添加。
    
    如：//背景颜色
    
           Color bgColor = Color.RED 
        `参考:https://www.cnblogs.com/foreverly/articles/6581198.html 直接就贴过来了XD`        