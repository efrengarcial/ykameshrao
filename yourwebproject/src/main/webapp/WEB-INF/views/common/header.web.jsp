<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand active" href="">Your Web Project</a>
            <ul class="nav">
                <li class="active"><a href="#">and its motto!</a></li>
            </ul>
            <ul class="nav pull-right">
                <li class="dropdown">
                		<sec:authorize access="isFullyAuthenticated()">
                			<a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome,
                                    <sec:authentication property="principal.username" /> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="${contextPath}/j_spring_security_logout">Logout</a></li>
                            </ul>                        
						</sec:authorize>
						<sec:authorize access="!isAuthenticated()">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">User Sign
                                In<b class="caret"></b></a>

                            <div class="dropdown-menu signin-pad">
                                <form id="loginUserForm" method="post" action="j_spring_security_check" class="pull-right">
                                    <label for="username_lgn">Username</label>
                                    <input class="span3" type="text" id="j_username" name="j_username"
                                           placeholder="Username"/>
                                    <label for="password_lgn">Password</label>
                                    <input class="span3" type="password" id="j_password" name="j_password"
                                           placeholder="Password"/>
                                    <input type="submit" name="Login" class="btn small" value="Login"/>
                                    <br/><br/>
                                    <a href="forgotPassword" style="margin-left: -14px;">Forgot your password?</a>
                                </form>
                            </div>
						</sec:authorize>              
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        // Setup drop down menu
        $('.dropdown-toggle').dropdown();

        // Fix input element click problem
        $('.dropdown input, .dropdown label').click(function (e) {
            e.stopPropagation();
        });
    });

    $(document).ready(function () {
        $("#loginUserForm").validate({
            rules:{
            	j_username:{
                    required:true
                },
                j_password:{
                    required:true
                }
            },
            errorClass:"control-group error",
            validClass:"control-group success",
            errorElement:"span",
            highlight:function (element, errorClass, validClass) {
                if (element.type === 'radio') {
                    this.findByName(element.name).parent("div").parent("div").removeClass(validClass).addClass(errorClass);
                } else {
                    $(element).parent("div").parent("div").removeClass(validClass).addClass(errorClass);
                }
            },
            unhighlight:function (element, errorClass, validClass) {
                if (element.type === 'radio') {
                    this.findByName(element.name).parent("div").parent("div").removeClass(errorClass).addClass(validClass);
                } else {
                    $(element).parent("div").parent("div").removeClass(errorClass).addClass(validClass);
                }
            }, 
            submitHandler: function(form) {
                var $form = $(form);
                var data =$form.serializeObject();
             	
             	$.ajax({
             		url: "${pageContext.request.contextPath}/j_spring_security_check",
             	    dataType: 'json',
			        data: data ,
             	    type: "POST",
             	    success: function(json) {             	      
             	      window.location.replace('${contextPath}/secure/dashboard');    
             	      /*if (json.success) {
             	        $("#signinForm").hide();
             	        $("#container").show();
             	        initApp();
             	        return true;
             	      }else {
             	        displayAlert("warn", "Bad login or password!", TIME_LONG);
             	        return false;
             	      } */
             	    },
             	    error: function(XMLHttpRequest, textStatus, errorThrown){
             	      alert(errorThrown);
             	    }
             	  });
             	
            }
        });
    });
    
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
</script>