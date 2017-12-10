<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>自定义菜单</title>

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


<form class="form-horizontal" role="form" id="form1" action="<c:url value="/api/customizemenu/save" />" method="post">
<div class="panel panel-info">
<div class="panel-heading">
    <h3>自定义菜单信息</h3>
  </div>
<div class="panel-body">

<div class="form-group">
    <label for="menucentect" class="col-sm-2 control-label">自定义菜单内容</label>
    <div class="col-sm-10">
    	<textarea name="menucentect" rows="12" class="form-control" >${menucentect }</textarea>
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

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	
    <script src="${pageContext.request.contextPath }/commons/js/jquery-2.1.4.min.js"></script>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath }/commons/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath }/commons/js/jquery.form.js"></script>
    <script src="${pageContext.request.contextPath }/commons/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    
<script type="text/javascript">

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
    
    $(function() {
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
            	menucentect: {
                    message: '自定义菜单内容验证失败',
                    validators: {
                        notEmpty: {
                            message: '自定义菜单内容不能为空'
                        }
                    }
                }
            }
        });
    });

</script>
</body>
</html>