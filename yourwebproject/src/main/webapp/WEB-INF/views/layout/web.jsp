<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
        
    <link href="${pageContext.request.contextPath}/res/datatables/media/css/demo_table.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/res/datatables/media/css/demo_table_jui.css" rel="stylesheet" type="text/css" />    
    <!--
    <link href="${pageContext.request.contextPath}/res/datatables/media/css/demo_page.css" rel="stylesheet" type="text/css" />        
    -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/bootstrap/css/united.bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/app.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/sticky.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/datatables/media/css/DT_bootstrap.css" type="text/css" />
    
     
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/blitzer/jquery-ui-1.9.0.custom.min.css" media="all"  />
    
     
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery/additional-methods.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery/jquery.validate.bootstrapfix.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery/jquery-ui-1.9.0.custom.min.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/datatables/media/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/datatables/media/js/DT_bootstrap.js"></script>
    
     
</head>
<body>
<!-- page -->
<div class="wrapper">
    <div class="container">
        <!-- header -->
        <header id="header">
            <tiles:insertAttribute name="header"/>
        </header>
        <!-- /header -->

        <!-- message -->
        <div id="message">
            <tiles:insertAttribute name="message"/>
        </div>
        <!-- /header -->

        <!-- content -->
        <div id="content">
            <tiles:insertAttribute name="content"/>
        </div>
        <!-- /content -->
    </div>
    <div class="push"><!--//--></div>
</div>
<!-- /page -->
<!-- footer -->
<footer>
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>
</footer>
<!-- /footer -->
</body>
</html>
