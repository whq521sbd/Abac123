package com.aotuo.vegetable.http;

import android.content.Context;
import android.widget.Toast;

public class ConnToast {
    private static String[] codeArray = { "操作成功",// 0
            "操作过于频繁",// 1
            "用户名不合法",// 2
            "密码错误",// 3
            "用户不存在",// 4
            "用户已存在",// 5
            "用户被封号",// 6
            "未登陆",// 7
            "两次输入不一致",// 8
            "分类不为空",// 9
            "必填字段为空",// 10
            "非法参数",// 11
            "不存在",// 12
            "权限不足",// 13
            "不能删除当前用户",// 14
            "已经存在",// 15
            "已经喜欢/收藏过了",// 16
            "非法数字",// 17
            "账户余额不足",// 18
            "现金账户不存在",// 19
            "任务重复领取",// 20
            "任务已领完",// 21
            "任务已结束",// 22
            "任务未领取",// 23
            "重复提交",// 24
            "验证码错误",// 25
            "用户已经认证过了",// 26
            "任务还没有开始",// 27
            "状态不能被修改",// 28
            "需要先解绑手机",// 29
            "代审核剩余数量不足",// 30
            "该手机号未关联任何账号",// 31
            "该任务没有问卷",// 32
            "非法调用(黑客恶意发送请求)",// 33
            "短信发送失败，短信服务商接口调用出错",// 34
            "密保答案不正确",// 35
            "密保已经存在",// 36
            "委托金额太少",// 37
            "卡券领取失败",// 38
            "卡券非法",// 39
            "卡券已经被使用过",// 40
            "卡券过期",// 41
            "还未通过学生身份认证",// 42
            "还未通过商家身份认证",// 43
            "相关账号还未设置",// 44
            "任务对象不匹配",// 45
            "还未通过个人身份认证" // 46
    };
    private static ConnToast instance;

    private ConnToast() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void toast(Context context, int code) {
        if (instance == null)
            instance = new ConnToast();
        if (code >= 0)
            Toast.makeText(context, codeArray[code], Toast.LENGTH_SHORT).show();
        else {
            if (code == -1)
                Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
            else if (code == -2)
                Toast.makeText(context, "服务器正在维护暂时无法提供服务", Toast.LENGTH_SHORT)
                        .show();
        }
    }
}
