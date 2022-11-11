<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    <p>Upload file: </p>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <br>
        <input type="submit" value="Upload">
    </form>
</#macro>

</html>