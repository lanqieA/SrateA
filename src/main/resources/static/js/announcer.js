/**
 * Created by Administrator on 2016/8/4.
 */

var name, cls, loca,currentID, flag = true;
function Announcerload() {
    $('#table').bootstrapTable({
        method: "get",
        striped: true,
        singleSelect: false,
        dataType: "json",
        pagination: true, //分页
        pageSize: 10,
        pageNumber: 1,
        search: false, //显示搜索框
        contentType: "application/x-www-form-urlencoded",
        queryParams: null,
        columns: [

            {
                checkbox:"true",
                field: 'ID',
                align: 'center',
                valign: 'middle'
            },
            {
                title: "工作证号",
                field: 'class',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '姓名',
                field: 'sex',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '性别',
                field: 'type',
                align: 'center'
            },
            {
                title: '出生年月',
                field: 'name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '入职时间',
                field: 'work',
                align: 'center'
            },
            {
                title: '级别',
                field: 'work',
                align: 'center'
            },
            {
                title: '管理区域',
                field: 'work',
                align: 'center'
            },
           
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="delNotice(\'' + row.WORKRECORDID + '\')">删除</button> '
                    var d = '<button button="#" mce_href="#" onclick="editNotice(\'' + row.WORKRECORDID + '\')">编辑</button> ';
                    var a = '<a href="notic_tail.html" target="right">查看工作履历</a> ';
                    var b = '<a href="notic_tail.html" target="right">查看管理区域</a> ';
                    var c = '<a href="notic_tail.html" target="right">查看奖惩履历</a> ';
                    return e + d+a+b+c;
                }
            }
        ]
    });
    getData();
    getCls();
    getLocation();
}
function getData() {
    if (flag) {
        name = "";
        cls = "";
        loca="";

        flag = false;
    } else {
        name = $("#name").val();
        cls = $("#cls").val();
        loca = $("#loca").val();

    }
    $.ajax({
        type: "GET",
        url: "../WorkRecord/SearchWork?dtStart=" + name + "&dtEnd=" + cls+ "&dtEnd=" + location ,
        dataType: "json",
        success: function (result) {
            if (result.data) {
                var TableData = result.data;
                $('#table').bootstrapTable("load", TableData);
            }
        }
    })
}
//初始化级别下拉菜单
function getCls() {
    $.ajax({
        url: '../Common/GetTaskTypeList',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var TYPEValue = data.data;
            var TYPEItem = "<Option value = " + "-1" + ">" + "全部" + "</Option>";
            $("#cls").append(TYPEItem);
            for (var i = 0; i < TYPEValue.length; i++) {
                var html = "<Option value = '" + TYPEValue[i].ID + "'>" + TYPEValue[i].NAME + "</Option>";
                $("#cls").append(html);
            }
        },
        error: function (err) {
        }

    })
}
//初始化区域下拉菜单
function getLocation() {
    $.ajax({
        url: '../Common/GetTaskTypeList',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var TYPEValue = data.data;
            var TYPEItem = "<Option value = " + "-1" + ">" + "全部" + "</Option>";
            $("#location").append(TYPEItem);
            for (var i = 0; i < TYPEValue.length; i++) {
                var html = "<Option value = '" + TYPEValue[i].ID + "'>" + TYPEValue[i].NAME + "</Option>";
                $("#location").append(html);
            }
        },
        error: function (err) {
        }

    })
}
function add() {
    openlayer()
    currentID = "";
}
function edit(id) {
    openlayer()
    currentID = id;
}
function del(id) {
    alert(id)
    var NoticeId = id;
    $.ajax({
        url: '../WorkRecord/DeleteWork?workId=' + NoticeId,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.data) {
                alert("删除成功！")
                getData();
            } else {
                alert("删除失败")
            }
        },
        error: function (err) {
        }
    });
}
function getCurrentID() {
    return currentID;
}
function openlayer(id){
    layer.open({
        type: 2,
        title: '添加信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'announcer_tail01.html'
        //iframe的url
    });
}





