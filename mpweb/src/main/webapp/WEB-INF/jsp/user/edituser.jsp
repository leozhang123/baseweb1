<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>user</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/commons/js/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/commons/js/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body>


<form class="form-horizontal" role="form" id="form1" action="<c:url value="/api/user/save" />" method="post">
<input type="hidden" name="code" value="${code }" >
<input type="hidden" name="userid" value="${user.userid }" >
<div class="panel panel-info">
<div class="panel-heading">
    <h3>用户信息</h3>
  </div>
<div class="panel-body">
<div class="form-group">
    <label for="realname" class="col-sm-2 control-label">真实姓名</label>
    <div class="col-sm-10">
      <input type="text" id="realname" name="realname" class="form-control" required placeholder="真实姓名" autofocus value="${user.realname }">
    </div>
</div>
<div class="form-group">
    <label for="sex" class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
            <label>
              <input name="sex" type="radio" checked="checked" value="0">
				 男
            </label>
            <label>
              <input name="sex" type="radio" value="1">
				 女
            </label>
    </div>
</div>

<div class="form-group">
    <label for="birthday" class="col-sm-2 control-label">出生日期</label>
    <div class="col-sm-10">
      <input type="date" name="birthday" id="birthday" class="form-control" placeholder="出生日期" >
    </div>
</div>
<div class="form-group">
    <label for="email" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="email" name="email" id="email" class="form-control" placeholder="email">
    </div>
</div>
    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
     <!-- 
      <button id="btnAjaxSubmit" type="button" class="btn btn-primary btn-lg btn-block" onclick="saveUser();">提交</button>
       -->
      <button type="submit" class="btn btn-primary btn-lg btn-block" >提交</button>
     
    </div>
  </div>
 </div>
</div>
</form>

	<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js" ></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	
    <script src="${pageContext.request.contextPath }/commons/js/jquery-2.1.4.min.js"></script>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath }/commons/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath }/commons/js/jquery.form.js"></script>
    <script src="${pageContext.request.contextPath }/commons/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    
<script type="text/javascript">

wx.config({
    debug: false,
    appId: '${appId}',
    timestamp: ${timestamp},
    nonceStr: '${nonceStr}',
    signature: '${signature}',
    jsApiList: [
      'checkJsApi',
      'hideMenuItems',
      'showMenuItems',
      'closeWindow'
    ]
});
wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
    alert(res);
});

/*     $(document).ready(function () {
        $("#btnAjaxSubmit").click(function () {
            var options = {
                url: '<c:url value="/api/dispatch/saveDispatch" />',
                type: 'post',
                dataType: 'text',
                data: $("#form1").serialize(),
                success: function (data) {
                    if (data.length > 0)
                        $("#responseText").text(data);
                }
            };
            $.ajax(options);
            return false;
        });
    }); */
    function saveUser(){
    	$("#form1").ajaxSubmit({
    		dataType:'json',
	    	success:function(data,statusText){
	    		//提交表单
	    		if(0==data.returnCode){
	    			//window.opener.tableRefresh();
	    			//window.open('','_self','');
	    			//window.close();
	    			//alert('信息提交成功');
	    			alert(data.returnMessage);
	    			wx.closeWindow();
	    		}else{
	    			alert(data.returnMessage);
	    		}
	    		return false;
	    	},
	    	error:function(data,statusText){
	    		alert("error:"+statusText);
	    	}
    	});
    };
    
    $(document).ready(function() {
        $('#form1').bootstrapValidator({
        	message: '输入值不正确',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitHandler: function(validator, form, submitButton) {
            	saveUser();
            },
            fields: {
            	realname: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 18,
                            message: '用户名长度必须在2到18位之间'
                        }/* ,
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        } */
                    }
                },
                email: {
                    validators: {
/*                         notEmpty: {
                            message: '邮箱不能为空'
                        }, */
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                },
                birthday:{
                	validators: {
                		/* notEmpty: {
                            message: '出生日期不能为空'
                        }, */
                		date: {
                			message:'日期格式不正确',
                            format:'YYYY/MM/DD'
                		}
                	}
                }
            }
        });
    });

</script>
</body>
</html>