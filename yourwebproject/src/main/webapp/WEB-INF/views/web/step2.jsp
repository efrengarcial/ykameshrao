<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Select one friend to be your best friend</h1>

<form action="${flowExecutionUrl}" method="post">
    <table border="0">
        <tr>
            <td>Select a best friend:</td>
            <td><input id="friendsAutocomplete" type="text" name="bestFriend" /> (try typing M, J, B or R)</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="_eventId_select" value="Make best friend" /></td>
        </tr>
    </table>
</form>

<a href="${flowExecutionUrl}&_eventId=back">&lt; Back</a>

<script type="text/javascript">
    $("#friendsAutocomplete").autocomplete({ source: 'listMyFiends?execution=${flowExecutionKey}' });
</script>

</body>
</html>