<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Select account</h1>

<form action="${flowExecutionUrl}" method="post">
    <table border="0">
        <tr>
            <td>User name:</td>
            <td><input type="text" name="userName" value="${account.userName}" /> (try Alice or Bob)</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="_eventId_next" class="btn btn-large btn-primary" value="Select best friend" /></td>
        </tr>
    </table>  
    
 <legend>Legend</legend>
  
<table id="table_id" width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Rendering engine</th>
            <th>Browser</th>
            <th>Platform(s)</th>
            <th>Engine version</th>
            <th>CSS grade</th>
        </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="5" class="dataTables_empty">Loading data from server</td>
    </tr>
    </tbody>
    <!-- 
    <tfoot>
        <tr>
        <th>Rendering engine</th>
        <th>Browser</th>
        <th>Platform(s)</th>
        <th>Engine version</th>
        <th>CSS grade</th>
    </tr>
    </tfoot>
     -->
</table>
   <script type="text/javascript">
	   $(document).ready(function() {
		      $('#table_id').dataTable( {
			   	  "sAjaxSource": "get?execution=${flowExecutionKey}",
		    	  "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>", 
		          "bProcessing": true,
		          "bServerSide": true,
		          "sPaginationType": "bootstrap",
		          "oLanguage": {"sLengthMenu": "_MENU_ records per page",
    			  "sInfo": "Displaying _START_ to _END_ of _TOTAL_ records"},
    			  "aoColumns": [
    				                { "mData": "id" },
    				                { "mData": "name" }    				                
    				            ]
		      } );
	    } );       
        </script>
        
</form>